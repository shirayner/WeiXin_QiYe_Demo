package com.ray.pojo.message.send;  
  
/**
 * 文本消息
 * @author shirayner
 *
 */
public class TextMessage extends BaseMessage {  
    //文本
    private Text text;
    //否	 表示是否是保密消息，0表示否，1表示是，默认0
    private int safe;
    
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public int getSafe() {
		return safe;
	}
	public void setSafe(int safe) {
		this.safe = safe;
	}
	
    
  
   
}  