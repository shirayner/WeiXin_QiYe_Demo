package com.ray.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ray.service.TempMaterialService;
import com.ray.util.WeiXinParamesUtil;
import com.ray.util.WeiXinUtil;

/**
 * Servlet implementation class UploadExpenseAccaoutServlet
 */
@WebServlet("/UploadExpenseAccaoutServlet")
public class UploadExpenseAccaoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadExpenseAccaoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String mediaId=request.getParameter("serverId");
		System.out.println("serverId:"+mediaId);

		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.contactsSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//String savePath=System.getProperty("user.dir").replaceAll("\\\\", "/")+"/WebContent/img/"+mediaId+".png"; 
		String savePath=request.getSession().getServletContext().getRealPath("").replaceAll("\\\\", "/")+"/img/"; 
		System.out.println("savePath:"+savePath);

		//2.调用业务类，获取临时素材
		TempMaterialService tms=new TempMaterialService();
		tms.getTempMaterial(accessToken, mediaId,savePath);


		PrintWriter out = response.getWriter(); 
		out.print("HHHHHHHHHH");  
		out.close();  
		out = null;  
	}

}
