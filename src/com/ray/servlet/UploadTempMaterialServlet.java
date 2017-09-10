package com.ray.servlet;


import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.ray.util.fileupload.FileUploadModule;
import com.ray.util.fileupload.FileUploadUtil;


@WebServlet("/UploadTempMaterialServlet")
public class UploadTempMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadTempMaterialServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * 上传数据及保存文件
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		 FileUploadModule fu = new FileUploadUtil();  
	        fu.setCachePathString(request.getSession().getServletContext()  
	                .getRealPath(("/"))  
	                + "images/temp");  
	        fu.setFileType(new String[]{"jpg","gif","txt"});  
	        fu.setFileOverPolicy(FileUploadModule.ENABLE);  
	        try {  
	            fu.uploadFiles(request, request.getSession().getServletContext()  
	                    .getRealPath(("/")+"images/test"));  
	        } catch (Exception e1) {  
	            // TODO Auto-generated catch block  
	            e1.printStackTrace();  
	        }  
	        try {  
	            Map<String,String> mp = fu.getFormMap();  
	            Set<String> key = mp.keySet();  
	            //Object[] object =key.toArray();  
	            for(String s:key){  
	                System.out.println("表单名称："+s);  
	                System.out.println("表单值："+mp.get(s));  
	            }  
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
		// 跳转到 message.jsp
		getServletContext().getRequestDispatcher("/message.jsp").forward(
				request, response);
	}

}