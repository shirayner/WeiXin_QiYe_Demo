package com.ray.pojo.message.req;  

/**
 * 链接消息 
 * @author shirayner
 *
 */
public class LinkMessage extends BaseMessage {  
	// 消息标题  
	private String Title;  
	// 消息描述  
	private String Description;  
	// 封面缩略图的url
	private String PicUrl;  

	public String getTitle() {  
		return Title;  
	}  

	public void setTitle(String title) {  
		Title = title;  
	}  

	public String getDescription() {  
		return Description;  
	}  

	public void setDescription(String description) {  
		Description = description;  
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}  


}  