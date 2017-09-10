package com.ray.pojo.message.resp;

/**
 * 消息基类（企业微信 -> 普通用户）
 * @author shirayner
 *
 */
public class BaseMessage {
    // 成员UserID
    private String ToUserName;
    // 企业微信CorpID
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型
    private String MsgType;

    
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}