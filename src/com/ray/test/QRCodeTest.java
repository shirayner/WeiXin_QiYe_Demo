package com.ray.test;

import java.io.File;

import org.junit.Test;
import com.ray.util.QRCodeUtil;


public class QRCodeTest {

	@Test
	public void testQRCode(){
		//String text = QRCodeUtil.generateNumCode(12);  //随机生成的12位验证码
		//System.out.println("随机生成的12位验证码为： " + text);

		String text="http://www.cnblogs.com/shirui/";
		System.out.println("text");
		int width = 300;    //二维码图片的宽
		int height = 300;   //二维码图片的高
		String format = "png";  //二维码图片的格式

		try {
			//生成二维码图片，并返回图片路径
			String pathName = QRCodeUtil.generateQRCode(text, width, height, format);
			System.out.println("生成二维码的图片路径： " + pathName);

			String content = QRCodeUtil.parseQRCode(pathName);
			System.out.println("解析出二维码的图片的内容为： " + content);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testQRCode2(){

		try {
			//生成二维码图片，并返回图片路径
			String pathName ="D:/1.png";
			File file = new File(pathName);

			//String content = QRCodeUtil.parseQRCode(pathName);
			String content = QRCodeUtil.decode(file);

			System.out.println("解析出二维码的图片的内容为： " + content);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}
