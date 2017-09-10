package com.ray.pojo.menu;  
  
/**
 * @desc  : 复杂按钮（父按钮） 
 * 
 * @author: shirayner
 * @date  : 2017-8-20 下午9:30:17
 */
public class ComplexButton extends Button {  
    private Button[] sub_button;  
  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}  