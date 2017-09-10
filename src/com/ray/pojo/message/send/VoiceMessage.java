package com.ray.pojo.message.send;  
  
/**
 * 语音消息
 * @author shirayner
 *
 */
public class VoiceMessage extends BaseMessage {  
    //语音
    private Media voice ;
    //否	 表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
    
	public Media getVoice() {
		return voice;
	}
	public void setVoice(Media voice) {
		this.voice = voice;
	}
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
   
    

   
}  