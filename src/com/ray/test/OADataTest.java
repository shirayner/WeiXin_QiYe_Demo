package com.ray.test;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import org.junit.Test;

import com.ray.pojo.Approve;
import com.ray.pojo.CheckIn;

import com.ray.service.OADataService;
import com.ray.util.WeiXinParamesUtil;
import com.ray.util.WeiXinUtil;

public class OADataTest {

	/**
	 * 1.获取打卡数据
	 */
	@Test
	public void testGetCheckInData(){
		//1.创建checkIn对象，并将对象转换成json字符串  
		long starttime=1504195200;  //2017年9月1日
		
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		long endtime=cal.getTimeInMillis()/1000;

		
		List<String> useridlist=new ArrayList<String>();
		useridlist.add("ShiRui");
		
		CheckIn checkIn = new CheckIn(3,starttime,endtime,useridlist);  
		System.out.println("checkIn:"+checkIn);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.checkInSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.调用service,获取打卡数据
		OADataService oads= new OADataService();
		oads.getCheckInData(accessToken, checkIn);


	}
	
	
	/**
	 * 1.获取打卡数据
	 */
	@Test
	public void testGetApproveData(){
		//1.创建checkIn对象，并将对象转换成json字符串  
		long starttime=1504195200;  //2017年9月1日
		
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		long endtime=cal.getTimeInMillis()/1000;

		
		
		Approve approve = new Approve(starttime,endtime);  
		System.out.println("approve:"+approve);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.approveSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.调用service,获取打卡数据
		OADataService oads= new OADataService();
		oads.getApproveData(accessToken, approve);


	}
	/**
	 * 1.获取打卡数据
	 */
	@Test
	public void testGetCheckInData2(){
		//1.创建checkIn对象，并将对象转换成json字符串  
		long starttime=1502617600;
		long endtime=1504819712;

		
		List<String> useridlist=new ArrayList<String>();
		useridlist.add("ShiRui");
		
		CheckIn checkIn = new CheckIn(3,starttime,endtime,useridlist);  
		System.out.println("checkIn:"+checkIn);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.checkInSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.调用service,获取打卡数据
		OADataService oads= new OADataService();
		oads.getCheckInData(accessToken, checkIn);


	}

	
	
	@Test
	public void testUTCDate(){
		
		//方法一：
		
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		long endtime1=cal.getTimeInMillis()/1000;
		
		long endtime2=System.currentTimeMillis()/1000;
		
		
		System.out.println("endtime1"+endtime1);
		System.out.println("endtime2"+endtime2);
		
	}


}
