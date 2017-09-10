package com.ray.pojo.message.send;  
  
/**
 * 文件消息
 * @author shirayner
 *
 */
public class FileMessage extends BaseMessage {  
    //文件
    private Media file ;
    //否	 表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
   
	public Media getFile() {
		return file;
	}
	public void setFile(Media file) {
		this.file = file;
	}
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
   
    

   
}  