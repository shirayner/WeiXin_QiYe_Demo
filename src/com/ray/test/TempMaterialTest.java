package com.ray.test;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

import com.ray.service.TempMaterialService;
import com.ray.util.WeiXinParamesUtil;
import com.ray.util.WeiXinUtil;

/**@desc  : 临时素材
 * 
 * @author: shirayner
 * @date  : 2017-8-18 下午2:06:10
 */
public class TempMaterialTest {
     /**
      * 1.上传临时素材
      */
	@Test
	public void testUploadTempMaterial(){
		//1.初始化参数
		String fileUrl="D:/renwu1.jpg";
		String type="image";
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.contactsSecret).getToken();
		//2.调用业务类，上传临时素材
		TempMaterialService tms=new TempMaterialService();
		tms.uploadTempMaterial(accessToken, type, fileUrl);
		
		
		
	}
	
	/**
	 * 2.获取临时素材
	 * @throws UnsupportedEncodingException 
	 */
	@Test
	public void testGetTempMaterial() throws UnsupportedEncodingException{
		//1.初始化参数
		String mediaId="3EzYbhraB9OEKIa0DSxTCFbSKgK7Qec0_OzzwGSMTCp8";
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.contactsSecret).getToken();
		//1.3    G:/WorkSpace/me_weixin/WeiXin_QiYe_Demo/WebContent/img/2.png
		String savePath=System.getProperty("user.dir").replaceAll("\\\\", "/")+"/WebContent/img/"+mediaId+".png";
        System.out.println("savePath:"+savePath);
		
		//2.调用业务类，上传临时素材
		TempMaterialService tms=new TempMaterialService();
		tms.getTempMaterial(accessToken, mediaId,savePath);
		
		
		
	}
	
	
	@Test
	public void testpath() throws UnsupportedEncodingException{
		System.out.println(this.getClass().getResource(""));
		System.out.println(ClassLoader.getSystemResource(""));
		System.out.println(System.getProperty("user.dir"));
		System.out.println(this.getClass().getResource(""));
		System.out.println(this.getClass().getResource(""));
		
		
		
	}
		
	
	
}
