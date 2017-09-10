package com.ray.pojo.message.resp;

/**
 * @desc  : 图片消息
 * 
 * @author: shirayner
 * @date  : 2017-8-17 下午1:53:28
 */
public class ImageMessage extends BaseMessage {
    
    private Media Image;

    public Media getImage() {
        return Image;
    }

    public void setImage(Media image) {
        Image = image;
    }
}