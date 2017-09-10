package com.ray.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import com.ray.util.WeiXinParamesUtil;
import com.ray.util.WeiXinUtil;

import net.sf.json.JSONObject;

/**@desc  : 
 * 
 * @author: shirayner
 * @date  : 2017-8-21 下午2:11:20
 */
public class TestUpload {
	/**
	 * 上传其他永久素材(图片素材的上限为5000，其他类型为1000)
	 *
	 * @return
	 * @throws Exception
	 */
	public static JSONObject addMaterialEver(String fileurl, String type, String token) {
	    try {
	        File file = new File(fileurl);
	        //上传素材
	        String path =  "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type="+type;
	        String result = connectHttpsByPost(path, null, file);
	        result = result.replaceAll("[\\\\]", "");
	        System.out.println("result:" + result);
	        JSONObject resultJSON = JSONObject.fromObject(result);
	        if (resultJSON != null) {
	            if (resultJSON.get("media_id") != null) {
	                System.out.println("上传" + type + "永久素材成功");
	                return resultJSON;
	            } else {
	                System.out.println("上传" + type + "永久素材失败");
	            }
	        }
	        return null;
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (NoSuchProviderException e) {
	        e.printStackTrace();
	    } catch (KeyManagementException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static  String connectHttpsByPost(String path, String KK, File file) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
	    URL urlObj = new URL(path);
	    //连接
	    HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	    String result = null;
	    con.setDoInput(true);

	    con.setDoOutput(true);

	    con.setUseCaches(false); // post方式不能使用缓存

	    // 设置请求头信息
	    con.setRequestProperty("Connection", "Keep-Alive");
	    con.setRequestProperty("Charset", "UTF-8");
	    // 设置边界
	    String BOUNDARY = "----------" + System.currentTimeMillis();
	    con.setRequestProperty("Content-Type",
	            "multipart/form-data; boundary="
	                    + BOUNDARY);

	    // 请求正文信息
	    // 第一部分：
	    StringBuilder sb = new StringBuilder();
	    sb.append("--"); // 必须多两道线
	    sb.append(BOUNDARY);
	    sb.append("\r\n");
	    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""

	            + file.getName() + "\"\r\n");
	    sb.append("Content-Type:application/octet-stream\r\n\r\n");
	    byte[] head = sb.toString().getBytes("utf-8");
	    // 获得输出流
	    OutputStream out = new DataOutputStream(con.getOutputStream());
	    // 输出表头
	    out.write(head);

	    // 文件正文部分
	    // 把文件已流文件的方式 推入到url中
	    DataInputStream in = new DataInputStream(new FileInputStream(file));
	    int bytes = 0;
	    byte[] bufferOut = new byte[1024];
	    while ((bytes = in.read(bufferOut)) != -1) {
	        out.write(bufferOut, 0, bytes);
	    }
	    in.close();
	    // 结尾部分
	    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
	    out.write(foot);
	    out.flush();
	    out.close();
	    StringBuffer buffer = new StringBuffer();
	    BufferedReader reader = null;
	    try {
	        // 定义BufferedReader输入流来读取URL的响应
	        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            buffer.append(line);
	        }
	        if (result == null) {
	            result = buffer.toString();
	        }
	    } catch (IOException e) {
	        System.out.println("发送POST请求出现异常！" + e);
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            reader.close();
	        }
	    }
	    return result;
	}
	
	public static void main(String[] args) {
	    try {
	    	 //1.获取access_token:根据企业id和应用密钥获取access_token
	  	    String accessToken=WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
	        String path = "D:/renwu1.jpg";
	        JSONObject object = addMaterialEver(path,"image",accessToken);
	        System.out.println(object.toString());
	    } catch (Exception e) {
	        System.out.println("---");
	    }
	}
	
	
	
	
	
}
