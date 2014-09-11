<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 12-1-8
  Time: 下午5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jpeg" pageEncoding="UTF-8"%>
<%@ page import="utils.MakeContentImage,java.io.*" %>
<%
   try{
          new MakeContentImage("人生",response.getOutputStream());
   }catch(Exception e){

   }
   out.clear();
   out = pageContext.pushBody();
%>
