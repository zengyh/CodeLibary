<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 14-9-14
  Time: 上午10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@include file="/common.jspf" %>
<%@include file="/demoHead.jspf" %><%-- 引用代码高亮插件syntaxHighliter --%>
<html>
<head><title>代码高亮插件syntaxHighliter使用例子</title></head>
<body>
html代码:
<pre name="code" class="html">
<%
        out.println( HtmlUtils.htmlEscape(
                "<p>\n" +
                "    SyntaxHighlighter is a fully functional self-contained code syntax highlighter developed in JavaScript.\n" +
                "</p>" ) );
%>
</pre>

java代码:
<pre name="code" class="java">
        public static void main( String args[] ) {

        }
</pre>

</body>
</html>