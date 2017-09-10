package com.ray.util.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Java原生的API可用于发送HTTP请求，即java.net.URL、java.net.URLConnection，这些API很好用、很常用，
 * 但不够简便；
 * 
 * 1.通过统一资源定位器（java.net.URL）获取连接器（java.net.URLConnection） 2.设置请求的参数 3.发送请求
 * 4.以输入流的形式获取返回内容 5.关闭输入流
 * 
 * @author H__D
 *
 */
public class HttpConnectionUtil {


	/**
	 * 多文件上传的方法
	 * 
	 * @param actionUrl：上传的路径
	 * @param uploadFilePaths：需要上传的文件路径，数组
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String uploadFile(String actionUrl, String[] uploadFilePaths) {
		String end = "\r\n";
		String twoHyphens = "--";
		String boundary = "*****";

		DataOutputStream ds = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
		String tempLine = null;

		try {
			// 统一资源
			URL url = new URL(actionUrl);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpURLConnection.setDoInput(true);
			// 设置是否向httpUrlConnection输出
			httpURLConnection.setDoOutput(true);
			// Post 请求不能使用缓存
			httpURLConnection.setUseCaches(false);
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码连接参数
			httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 设置请求内容类型
			httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

			// 设置DataOutputStream
			ds = new DataOutputStream(httpURLConnection.getOutputStream());
			for (int i = 0; i < uploadFilePaths.length; i++) {
				String uploadFile = uploadFilePaths[i];
				String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
				ds.writeBytes(twoHyphens + boundary + end);
				ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + i + "\";filename=\"" + filename
						+ "\"" + end);
				ds.writeBytes(end);
				FileInputStream fStream = new FileInputStream(uploadFile);
				int bufferSize = 1024;
				byte[] buffer = new byte[bufferSize];
				int length = -1;
				while ((length = fStream.read(buffer)) != -1) {
					ds.write(buffer, 0, length);
				}
				ds.writeBytes(end);
				/* close streams */
				fStream.close();
			}
			ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
			/* close streams */
			ds.flush();
			if (httpURLConnection.getResponseCode() >= 300) {
				throw new Exception(
						"HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
			}

			if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				inputStream = httpURLConnection.getInputStream();
				inputStreamReader = new InputStreamReader(inputStream);
				reader = new BufferedReader(inputStreamReader);
				tempLine = null;
				resultBuffer = new StringBuffer();
				while ((tempLine = reader.readLine()) != null) {
					resultBuffer.append(tempLine);
					resultBuffer.append("\n");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ds != null) {
				try {
					ds.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStreamReader != null) {
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			return resultBuffer.toString();
		}
	}



	/**
	 * Java原生的API可用于发送HTTP请求，即java.net.URL、java.net.URLConnection，这些API很好用、很常用，
	 * 但不够简便；
	 * 
	 * 1.通过统一资源定位器（java.net.URL）获取连接器（java.net.URLConnection） 2.设置请求的参数 3.发送请求
	 * 4.以输入流的形式获取返回内容 5.关闭输入流
	 * 
	 * @author H__D
	 *
	 */



	/**
	 * 
	 * @param urlPath
	 *            下载路径
	 * @param downloadDir
	 *            下载存放目录
	 * @return 返回下载文件
	 */
	public static File downloadFile(String urlPath, String downloadDir) {
		File file = null;
		try {
			// 统一资源
			URL url = new URL(urlPath);
			// 连接类的父类，抽象类
			URLConnection urlConnection = url.openConnection();
			// http的连接类
			HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
			// 设定请求的方法，默认是GET
			httpURLConnection.setRequestMethod("POST");
			// 设置字符编码
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			// 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
			httpURLConnection.connect();

			// 文件大小
			int fileLength = httpURLConnection.getContentLength();

			// 文件名
			String filePathUrl = httpURLConnection.getURL().getFile();
			String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

			System.out.println("file length---->" + fileLength);

			URLConnection con = url.openConnection();

			BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

			String path = downloadDir + File.separatorChar + fileFullName;
			file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			OutputStream out = new FileOutputStream(file);
			int size = 0;
			int len = 0;
			byte[] buf = new byte[1024];
			while ((size = bin.read(buf)) != -1) {
				len += size;
				out.write(buf, 0, size);
				// 打印下载百分比
				// System.out.println("下载了-------> " + len * 100 / fileLength +
				// "%\n");
			}
			bin.close();
			out.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return file;
		}

	}



	public static void main(String[] args) {

		// 上传文件测试
		String str = uploadFile("http://tavvej.natappfree.cc/WeiXin_QiYe_Demo/img",new String[] { "D:/3.png","D:/4.png" });
		System.out.println(str);

		// 下载文件测试
		downloadFile("http://tavvej.natappfree.cc/WeiXin_QiYe_Demo/img/3.png", "D:/Img");

	}

}

