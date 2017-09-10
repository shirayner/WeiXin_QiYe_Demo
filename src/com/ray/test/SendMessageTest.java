package com.ray.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


import com.ray.pojo.message.send.Article;
import com.ray.pojo.message.send.FileMessage;
import com.ray.pojo.message.send.ImgMessage;
import com.ray.pojo.message.send.Media;
import com.ray.pojo.message.send.News;
import com.ray.pojo.message.send.NewsMessage;
import com.ray.pojo.message.send.Text;
import com.ray.pojo.message.send.TextMessage;
import com.ray.pojo.message.send.Textcard;
import com.ray.pojo.message.send.TextcardMessage;
import com.ray.pojo.message.send.Video;
import com.ray.pojo.message.send.VideoMessage;
import com.ray.pojo.message.send.VoiceMessage;
import com.ray.service.SendMessageService;
import com.ray.util.WeiXinParamesUtil;
import com.ray.util.WeiXinUtil;

/**@desc  : 消息推送之发送消息
 * 
 * @author: shirayner
 * @date  : 2017-8-18 上午10:04:55
 */
public class SendMessageTest {

	//1.发送文本消息
	@Test
	public void testSendTextMessage(){
		//0.设置消息内容
		String content="你好！你的快递已到，请携带工卡前往邮件中心领取。\n出发前可查看" +
				"<a href=\"http://work.weixin.qq.com\">邮件中心视频实况" +
				"</a>，聪明避开排队。";

		//1.创建文本消息对象
		TextMessage message=new TextMessage();
		//1.1非必需
		message.setTouser("SHIRUI");  //不区分大小写
		//textMessage.setToparty("1");
		//txtMsg.setTotag(totag);
		//txtMsg.setSafe(0);

		//1.2必需
		message.setMsgtype("text");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Text text=new Text();
		text.setContent(content);
		message.setText(text);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}

	//2.发送文本卡片消息
	@Test
	public void testSendTextcardMessage(){
		//0.设置消息内容
		String title="代办事宜";
		String description="<div class=\"gray\">2017年8月18日</div> <div class=\"normal\">" +
				"恭喜你抽中iPhone 7一台，领奖码：xxxx</div><div class=\"highlight\">" +
				"请于2017年10月10日前联系行政同事领取</div>";
		String url="http://www.cnblogs.com/shirui/p/7297872.html";

		//1.创建文本卡片消息对象
		TextcardMessage message=new TextcardMessage();
		//1.1非必需
		message.setTouser("shirui");  //不区分大小写
		//message.setToparty("1");
		//message.setTotag(totag);
		//message.setSafe(0);

		//1.2必需
		message.setMsgtype("textcard");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Textcard textcard=new Textcard();
		textcard.setTitle(title);
		textcard.setDescription(description);
		textcard.setUrl(url);
		message.setTextcard(textcard);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}

	//3.发送图片消息---无效的media_id
	@Test
	public void testSendImgMessage(){
		//0.设置消息内容
		//String media_id="MEDIA_ID";
		String media_id="7n_ysJvJbAhd0FhCWXWlkb1p3J8a3EA7KsZyLFu1ufBvnttrr-Qw2dmXPyiArfnu";
		//1.创建图片消息对象
		ImgMessage message=new ImgMessage();
		//1.1非必需
		message.setTouser("ShiRui");  //不区分大小写
		//textMessage.setToparty("1");
		//txtMsg.setTotag(totag);
		//txtMsg.setSafe(0);

		//1.2必需
		message.setMsgtype("image");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Media image=new Media();
		image.setMedia_id(media_id);
		message.setImage(image);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}


	//4.发送语音消息---无效的media_id
	@Test
	public void testSendVoiceMessage(){
		//0.设置消息内容
		String media_id="MEDIA_ID";
		//1.创建语音消息对象
		VoiceMessage message=new VoiceMessage();
		//1.1非必需
		message.setTouser("@all");  //不区分大小写
		//textMessage.setToparty("1");
		//txtMsg.setTotag(totag);
		//txtMsg.setSafe(0);

		//1.2必需
		message.setMsgtype("image");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Media voice=new Media();
		voice.setMedia_id(media_id);
		message.setVoice(voice);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}

	//5.发送视频消息
	@Test
	public void testSendVideoMessage(){
		//0.设置消息内容
		String media_id="MEDIA_ID";
		String title="视频示例";
		String description="好看的视频";


		//1.创建视频消息对象
		VideoMessage message=new VideoMessage();
		//1.1非必需
		message.setTouser("@all");  //不区分大小写
		//message.setToparty("1");
		//message.setTotag(totag);
		//message.setSafe(0);

		//1.2必需
		message.setMsgtype("video");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Video video=new Video();
		video.setMedia_id(media_id);
		video.setTitle(title);
		video.setDescription(description);
		message.setVideo(video);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}

	//6.发送文件消息
	@Test
	public void testSendFileMessage(){
		//0.设置消息内容
		String media_id="MEDIA_ID";

		//1.创建文件对象
		FileMessage message=new FileMessage();
		//1.1非必需
		message.setTouser("@all");  //不区分大小写
		//textMessage.setToparty("1");
		//txtMsg.setTotag(totag);
		//txtMsg.setSafe(0);

		//1.2必需
		message.setMsgtype("file");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Media file=new Media();
		file.setMedia_id(media_id);
		message.setFile(file);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}

	//7.发送图文消息
	@Test
	public void testSendNewsMessage(){

		//1.创建图文消息对象
		NewsMessage message=new NewsMessage();
		//1.1非必需
		message.setTouser("@all");  //不区分大小写
		//textMessage.setToparty("1");
		//txtMsg.setTotag(totag);
		//txtMsg.setSafe(0);

		//1.2必需
		message.setMsgtype("news");
		message.setAgentid(WeiXinParamesUtil.agentId);
        //设置图文消息
		Article article1=new  Article();
		article1.setTitle("青年文摘");
		article1.setDescription("这是一个很特别的描述");
		article1.setPicurl("http://img.hb.aicdn.com/41db5196f17008a1994e978603c18dfaaa4b703f465a9-DcgZvI_fw658");
		article1.setUrl("http://www.cnblogs.com/shirui/p/7297872.html");
		
		List<Article>  articles=new ArrayList<Article>();
		articles.add(article1);
		
		News news=new News();
		news.setArticles(articles);
		message.setNews(news);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		sms.sendMessage(accessToken, message);

	}




}
