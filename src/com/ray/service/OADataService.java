package com.ray.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ray.pojo.Approve;
import com.ray.pojo.CheckIn;
import com.ray.util.WeiXinUtil;

import net.sf.json.JSONObject;


public class OADataService {
	private static Logger log = LoggerFactory.getLogger(OADataService.class);  
	private  static  String getCheckInData_url="https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token=ACCESS_TOKEN";  
	private  static  String getApproveData_url="https://qyapi.weixin.qq.com/cgi-bin/corp/getapprovaldata?access_token=ACCESS_TOKEN";  

	//1.获取打卡数据
	public void  getCheckInData(String accessToken,CheckIn checkIn){
		//1.获取json字符串：将CheckIn对象转换为json字符串	
		Gson gson = new Gson(); 
		String jsonCheckIn =gson.toJson(checkIn);      //使用gson.toJson(checkIn)即可将checkIn对象顺序转成json
		System.out.println("jsonCheckIn:"+jsonCheckIn);

		//2.获取请求的url  
		getCheckInData_url=getCheckInData_url.replace("ACCESS_TOKEN", accessToken);

		//3.调用接口，发送请求，获取打卡数据
		JSONObject jsonObject = WeiXinUtil.httpRequest(getCheckInData_url, "POST", jsonCheckIn);  
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取打卡数据 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  

	}

	//1.获取审批数据
	public void  getApproveData(String accessToken,Approve approve){
		//1.获取json字符串：将CheckIn对象转换为json字符串	
		Gson gson = new Gson(); 
		String jsonApprove =gson.toJson(approve);      //使用gson.toJson(checkIn)即可将checkIn对象顺序转成json
		System.out.println("jsonApprove:"+jsonApprove);

		//2.获取请求的url  
		getApproveData_url=getApproveData_url.replace("ACCESS_TOKEN", accessToken);

		//3.调用接口，发送请求，获取打卡数据
		JSONObject jsonObject = WeiXinUtil.httpRequest(getApproveData_url, "POST", jsonApprove);  
		System.out.println("jsonObject:"+jsonObject.toString());

		//4.错误消息处理
		if (null != jsonObject) {  
			if (0 != jsonObject.getInt("errcode")) {  
				log.error("获取审批数据 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
			}  
		}  

	}

}
