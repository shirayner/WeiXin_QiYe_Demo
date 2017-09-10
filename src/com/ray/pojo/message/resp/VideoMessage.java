package com.ray.pojo.message.resp;

/**
 * @desc  : 视频消息
 * 
 * @author: shirayner
 * @date  : 2017-8-21 上午10:36:33
 */
public class VideoMessage extends BaseMessage {
    // 视频
    private Video Video;

    public Video getVideo() {
        return Video;
    }

    public void setVideo(Video video) {
        Video = video;
    }
}