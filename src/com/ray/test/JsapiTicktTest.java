package com.ray.test;

import java.io.UnsupportedEncodingException;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import org.junit.Test;

import com.ray.util.WeiXinParamesUtil;
import com.ray.util.WeiXinUtil;


public class JsapiTicktTest {

    @Test
	public void testGetJsapiTicktTest(){
		 //1.获取access_token:根据企业id和应用密钥获取access_token
		  String accessToken=WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
		  System.out.println("accessToken:"+accessToken);
		  String jsapiTicktTest=WeiXinUtil.getJsapiTicket(accessToken);
		
		  System.out.println("jsapiTicktTest:"+jsapiTicktTest);
		
	}
    

    @Test
	public void testGetJsapiTicktTest2(){

    	String sign = "kgt8ON7yVITDhtdwc0qebCLEfC_BfeygIq8cFPLbdbr_yiyH5CdxD37u__D58ifUEY6tg_NFK1_7Vg0wzPI8Q&noncestr=7d39360f-e2d1-4f01-8687-42ddf19d768d&timestamp=1504598344&url=http://localhost:8080/WeiXin_QiYe_Demo/jsapiTickt.jsp";
    	
    	//3.sha1签名
    	String signature = "";
    	try {
    		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
    		crypt.reset();
    		crypt.update(sign.getBytes("UTF-8"));
    		signature = byteToHex(crypt.digest());
    		System.out.println("signature:"+signature);
    	} catch (NoSuchAlgorithmException e) {
    		e.printStackTrace();
    	} catch (UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}
	}
    
    @Test
	public void testGetJsapiTicktTest3() throws DigestException{

    	String sign = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com";
    	
    	//3.sha1签名
    	String signature = SHA1(sign);
    	System.out.println("signature:"+signature);
	}


	/**
	 * 方法名：byteToHex</br>
	 * 详述：字符串加密辅助方法 </br>
	 * 开发人员：souvc  </br>
	 * 创建时间：2016-1-5  </br>
	 * @param hash
	 * @return 说明返回值含义
	 * @throws 说明发生此异常的条件
	 */
	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;

	}
	
	/** 
     * SHA1 安全加密算法 
     * @param decrypt 信息摘要 - 参数字典排序后字符串  
     * @return 
     * @throws DigestException  
     */  
    public static String SHA1(String decrypt) throws DigestException {  

        try {  
            //指定sha1算法  
            MessageDigest digest = MessageDigest.getInstance("SHA-1");  
            digest.update(decrypt.getBytes());  
            //获取字节数组  
            byte messageDigest[] = digest.digest();  
            // Create Hex String  
            StringBuffer hexString = new StringBuffer();  
            // 字节数组转换为 十六进制 数  
            for (int i = 0; i < messageDigest.length; i++) {  
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);  
                if (shaHex.length() < 2) {  
                    hexString.append(0);  
                }  
                hexString.append(shaHex);  
            }  
            return hexString.toString().toUpperCase();  
  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            throw new DigestException("签名错误！");  
        }  
    }  
    
    
}
