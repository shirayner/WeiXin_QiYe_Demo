package com.ray.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;


import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.ray.pojo.message.resp.TextMessage;
import com.ray.util.MessageUtil;
import com.ray.util.WeiXinParamesUtil;

/**
 * @desc  : 被动回复消息
 * 
 * @author: shirayner
 * @date  : 2017-8-17 下午3:37:17
 */
public class MessageService {

	private String msg_signature ; // 微信加密签名  
	private String timestamp ;    // 时间戳    
	private String nonce ;       // 随机数  


	/**
	 * @desc ：获取加密后的回复消息
	 *  
	 * @param request
	 * @return String  返回加密后的回复消息
	 */
	public String getEncryptRespMessage(HttpServletRequest request){
		String respMessage = null; 

		try {
			//1.解密微信发过来的消息
			String xmlMsg=this.getDecryptMsg(request);

			//2.解析微信发来的请求,解析xml字符串
			Map<String, String> requestMap= MessageUtil.parseXml(xmlMsg);	

			//3.获取请求参数
			//3.1 企业微信CorpID  
			String fromUserName = requestMap.get("FromUserName");  
			//3.2 成员UserID
			String toUserName = requestMap.get("ToUserName");  
			//3.3 消息类型与事件 
			String msgType = requestMap.get("MsgType"); 
			String eventType = requestMap.get("Event");  
			String eventKey = requestMap.get("EventKey"); 
            System.out.println("msgType:"+msgType);
            System.out.println("Event:"+eventType+"  eventKey:"+eventKey);
            

			//4.组装 回复文本消息  
			TextMessage textMessage = new TextMessage();  
			textMessage.setToUserName(fromUserName);  
			textMessage.setFromUserName(toUserName);  
			textMessage.setCreateTime(new Date().getTime());  
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
			//4.1.获取回复消息的内容 ：消息的分类处理
			String respContent=this.getRespContentByMsgType(msgType, eventType, eventKey);
			textMessage.setContent(respContent);  
			System.out.println("respContent："+respContent);

			//5.获取xml字符串： 将（被动回复消息型的）文本消息对象 转成  xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage); 
			

			//6.加密
			WXBizMsgCrypt wxcpt=new WXBizMsgCrypt(WeiXinParamesUtil.token,WeiXinParamesUtil.encodingAESKey,WeiXinParamesUtil.corpId);
			respMessage = wxcpt.EncryptMsg(respMessage, timestamp, msgType);
			

		} catch (Exception e) {
			e.printStackTrace();
		}  

		return respMessage;
	}


	/**
	 * @desc ：2.从request中获取消息明文
	 *  
	 * @param request
	 * @return String   消息明文
	 */
	public String getDecryptMsg(HttpServletRequest request) {

		String postData="";   // 密文，对应POST请求的数据
		String result="";     // 明文，解密之后的结果

		this.msg_signature = request.getParameter("msg_signature"); // 微信加密签名  
		this.timestamp = request.getParameter("timestamp");   // 时间戳    
		this.nonce = request.getParameter("nonce");          // 随机数  

		try {
			//1.获取加密的请求消息：使用输入流获得加密请求消息postData
			ServletInputStream in = request.getInputStream();
			BufferedReader reader =new BufferedReader(new InputStreamReader(in));  

			String tempStr="";   //作为输出字符串的临时串，用于判断是否读取完毕  
			while(null!=(tempStr=reader.readLine())){  
				postData+=tempStr;  
			}  

			//2.获取消息明文：对加密的请求消息进行解密获得明文  
			WXBizMsgCrypt wxcpt=new WXBizMsgCrypt(WeiXinParamesUtil.token,WeiXinParamesUtil.encodingAESKey,WeiXinParamesUtil.corpId);
			result = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (AesException e) {
			e.printStackTrace();
		}  

		return result;
	}

	/**
	 * @desc ：3.处理消息：根据消息类型获取回复内容
	 *  
	 * @param msgType
	 * @return String
	 */
	public String getRespContentByMsgType(String msgType,String eventType,String eventKey){
		String respContent="";
		//1.文本消息  
		if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
			respContent = "您发送的是文本消息！";  

		}  
		//2.图片消息  
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
			respContent = "您发送的是图片消息！";  
		}  
		//3.地理位置消息  
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { 
			
			respContent = "您发送的是地理位置消息 ！";  
		}  
		//4.链接消息  
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
			respContent = "您发送的是链接消息！";  
		}  
		//5.音频消息  
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
			respContent = "您发送的是音频消息！";  
		}
		//6.事件推送  
		else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { 
			respContent=this.processEevent(eventType, eventKey);
		}
		//7.请求异常
		else {
			respContent="请求处理异常，请稍候尝试！";
		}  

		return respContent;
	}

	public String processEevent(String eventType,String eventKey){
		
		String respContent="";
		// 订阅  
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
			respContent = "欢迎关注！";  
		}  
		// 取消订阅  
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
			// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
		} 
		//上报地理位置事件
		else if(eventType.equals("LOCATION")){
			
		}
		// 自定义菜单点击事件  
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  

			if (eventKey.equals("12")) {  
				// TODO  help
			} else if (eventKey.equals("13")) {  
				respContent = "周边搜索菜单项被点击！";  
			} else if (eventKey.equals("14")) {  
				respContent = "历史上的今天菜单项被点击！";  
			} else if (eventKey.equals("21")) {  
				respContent = "歌曲点播菜单项被点击！";  
			} else if (eventKey.equals("22")) {  

				respContent = "经典游戏菜单项被点击！";  
			} else if (eventKey.equals("23")) {  
				respContent = "美女电台菜单项被点击！";  
			} else if (eventKey.equals("24")) {  
				respContent = "人脸识别菜单项被点击！";  
			} else if (eventKey.equals("25")) {  
				respContent = "聊天唠嗑菜单项被点击！";  
			} else if (eventKey.equals("31")) {  
				respContent = "Q友圈菜单项被点击！";  
			} else if (eventKey.equals("32")) {  
				respContent = "电影排行榜菜单项被点击！";  
			} else if (eventKey.equals("33")) {  
				respContent = "幽默笑话菜单项被点击！";  
			}  
		} 
		return respContent;
	}  
  
	
	
	
	
}
