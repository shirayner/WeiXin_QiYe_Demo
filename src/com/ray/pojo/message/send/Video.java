package com.ray.pojo.message.send;

/**
 * 视频
 * @author shirayner
 *
 */
public class Video {
	//是	 视频媒体文件id，可以调用上传临时素材接口获取
	private String media_id;	
	//否	 视频消息的标题，不超过128个字节，超过会自动截断
	private String title;
	//否	 视频消息的描述，不超过512个字节，超过会自动截断
	private String description;
	
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	
	
}
