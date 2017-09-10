package com.ray.pojo.message.resp;

/**
 * @desc  : 语音消息
 * 
 * @author: shirayner
 * @date  : 2017-8-17 下午1:57:42
 */
public class VoiceMessage extends BaseMessage {
    // 语音
    private Media Voice;

	public Media getVoice() {
		return Voice;
	}

	public void setVoice(Media voice) {
		Voice = voice;
	}

   
}