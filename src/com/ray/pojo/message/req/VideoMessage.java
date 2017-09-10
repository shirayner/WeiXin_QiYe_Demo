package com.ray.pojo.message.req;

/**
 * 视频消息
 * @author shirayner
 *
 */
public class VideoMessage {
	//视频媒体文件id，可以调用获取媒体文件接口拉取数据
	private String MediaId;	
	//视频消息缩略图的媒体id，可以调用获取媒体文件接口拉取数据
	private String ThumbMediaId;
	
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}	

	
}
