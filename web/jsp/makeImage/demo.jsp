<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 12-1-8
  Time: 下午5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jpeg" pageEncoding="UTF-8"%>
<%@ page import="utils.MakeContentImage" %>
<%@ page import="java.net.URL" %>
<head>制作图片DEMO</head>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   URL backgroundUrl = new URL( basePath+"/images/background.jpg" );
   try{
          new MakeContentImage("人生",backgroundUrl, 50, 100).write( response.getOutputStream() );
   }catch(Exception e){
        //e.printStackTrace();
   }
   out.clear();
   out = pageContext.pushBody();
%>