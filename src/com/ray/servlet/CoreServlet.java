package com.ray.servlet;
  
  

import java.io.IOException;  
import java.io.PrintWriter;   
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  


import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.ray.service.MessageService;
import com.ray.util.WeiXinParamesUtil;

  

  
/**
 * 核心请求处理类 
 * @author shirayner
 *
 */
public class CoreServlet extends HttpServlet {  
    private static final long serialVersionUID = 4440739483644821986L;  
  
    /** 
     * 确认请求来自微信服务器 
     */  
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  	
        
    	    // 微信加密签名  
            String msg_signature = request.getParameter("msg_signature");  
            // 时间戳  
            String timestamp = request.getParameter("timestamp");  
            // 随机数  
            String nonce = request.getParameter("nonce");  
            // 随机字符串   
            String echostr = request.getParameter("echostr");  
      
            System.out.println("request=" + request.getRequestURL());  
      
            PrintWriter out = response.getWriter();  
            // 通过检验msg_signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败  
            String result = null;  
            try {
                WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(WeiXinParamesUtil.token, WeiXinParamesUtil.encodingAESKey, WeiXinParamesUtil.corpId);  
                result = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);  
            } catch (AesException e) {  
                e.printStackTrace();  
            }  
            if (result == null) {  
                result = WeiXinParamesUtil.token;  
            }  
            out.print(result);  
            out.close();  
            out = null;  
    }  
  
    /** 
     * 处理微信服务器发来的消息 
     */  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        //1.将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
     
        //2.调用消息业务类接收消息、处理消息  
        MessageService msgsv=new MessageService();
        String respMessage = msgsv.getEncryptRespMessage(request);  
     
        //处理表情
        // String respMessage = CoreService.processRequest_emoj(request);
        //处理图文消息
        //String respMessage = Test_NewsService.processRequest(request);

        
        //3.响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close(); 
    }  
  
}  