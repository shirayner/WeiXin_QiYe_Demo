package com.ray.test;

import com.google.gson.Gson;
import com.ray.pojo.User;

import net.sf.json.JSONObject;

public class TestJson {
	
  
    public static void main(String[] args) {   
    	 Gson gson = new Gson(); 
    	 User user = new User("3", "ray", 1,"13886086292","1766211120@qq.com","技术顾问","1");  
         String text1 = JSONObject.fromObject(user).toString(); 
        
         String text2 =gson.toJson(user);
         System.out.println("text2=" + text1); 
         System.out.println("text2=" + text2); 
        
    }  

}
