package com.ray.pojo.message.req;  
  
/**
 * 语音消息 
 * @author shirayner
 *
 */
public class VoiceMessage extends BaseMessage {  
    // 语音媒体文件id，可以调用获取媒体文件接口拉取数据 
    private String MediaId;  
    // 语音格式，如amr，speex等
    private String Format;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
    public String getFormat() {  
        return Format;  
    }  
  
    public void setFormat(String format) {  
        Format = format;  
    }  
}  