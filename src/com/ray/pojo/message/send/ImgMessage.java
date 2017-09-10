package com.ray.pojo.message.send;  
  
/**
 * 图片消息
 * @author shirayner
 *
 */
public class ImgMessage extends BaseMessage {  
    //图片
    private Media image ;
    //否	 表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
    
	public Media getImage() {
		return image;
	}
	public void setImage(Media image) {
		this.image = image;
	}
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}

   
}  