<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"     %>
    <%@page language="java" import="com.ray.pojo.*"%>
<%@page language="java" import="com.ray.service.*"%>
<%@page language="java" import="com.ray.util.*"%>
<%@page language="java" import="net.sf.json.JSONObject"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>移动端网页授权</title>

<script src="/js/jquery.js"></script>
<script src="http://rescdn.qqmail.com/node/ww/wwopenmng/js/sso/wwLogin-1.0.0.js"></script>
</head>
<body>

<%
         String code= request.getParameter("code");
         String state=request.getParameter("state");
         
         MTAuthorizationService mts=new MTAuthorizationService();
         String accessToken=WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();
         //获取成员信息
         JSONObject userInfo=mts.getUserInfo(accessToken, code);
         
         //获取成员详情
         UserTicket userTicket=new UserTicket();
         userTicket.setUser_ticket(userInfo.getString("user_ticket"));

        JSONObject userDetail=mts.getUserDetail(accessToken, userTicket);
        
         
         
%>
hello，这里是第三方应用
code=<%= code%>    <br>
state=<%= state%>      <br>
userInfo=<%= userInfo.toString()%>    <br><br>
userTicket=<%= userInfo.getString("user_ticket")%>   <br><br>
userDetail=<%= userDetail.toString()%>   <br><br>

</body>
</html>