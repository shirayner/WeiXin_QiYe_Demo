package com.ray.pojo.message.send;  
  
/**
 * 视频消息
 * @author shirayner
 *
 */
public class VideoMessage extends BaseMessage {  
    //视频
    private Video video ;
    //否	 表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
    
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}

}  