package com.ray.pojo.message.req;  

/**
 *  图片消息 
 * @author shirayner
 *
 */
public class ImageMessage extends BaseMessage {  
	// 图片链接  
	private String PicUrl;  
	// 图片媒体文件id，可以调用获取媒体文件接口拉取
	private String MediaId;  

	public String getPicUrl() {  
		return PicUrl;  
	}  

	public void setPicUrl(String picUrl) {  
		PicUrl = picUrl;  
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}  

}  