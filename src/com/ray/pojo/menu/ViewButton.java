package com.ray.pojo.menu;  
  
/**
 * @desc  : view类型的菜单 
 * 
 * @author: shirayner
 * @date  : 2017-8-20 下午9:30:44
 */
public class ViewButton extends Button {  
    private String type;  
    private String url;  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getUrl() {  
        return url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }  
}  