<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 12-1-8
  Time: 下午5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jpeg" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page import="utils.MakeContentImage" %>
<%@ page import="java.net.URL" %>
<%@include file="/common.jspf" %>
<%@include file="/demoHead.jspf" %><%-- 引用代码高亮插件syntaxHighliter --%>
<head>制作图片DEMO</head>
<%
   String path = request.getContextPath();
   String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
   URL backgroundUrl = new URL( basePath+"/images/background.jpg" );
   try{
          new MakeContentImage("人生",backgroundUrl).write( response.getOutputStream() );
   }catch(Exception e){
        //e.printStackTrace();
   }
   out.clear();
   out = pageContext.pushBody();
%>

代码:
<pre name="code" class="java">
<%
        out.println( HtmlUtils.htmlEscape("" +
                "   String path = request.getContextPath();\n" +
                "   String basePath = request.getScheme()+\"://\"+request.getServerName()+\":\"+request.getServerPort()+path+\"/\";\n" +
                "   URL backgroundUrl = new URL( basePath+\"/images/background.jpg\" );\n" +
                "   try{\n" +
                "          new MakeContentImage(\"人生\",backgroundUrl).write( response.getOutputStream() );\n" +
                "   }catch(Exception e){\n" +
                "        //e.printStackTrace();\n" +
                "   }\n" +
                "   out.clear();\n" +
                "   out = pageContext.pushBody();") );
%>
</pre>
