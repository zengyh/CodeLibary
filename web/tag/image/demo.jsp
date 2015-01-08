<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 15-1-7
  Time: 下午1:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@include file="/demoHead.jspf" %><%-- 引用代码高亮插件syntaxHighliter --%>
<%@taglib prefix="image" uri="http://yh.zeng/tag/image" %>
<html>
<head><title>Image标签使用例子</title></head>

按照<font color="red">宽度</font>缩放图片的例子效果:<br>
<image:scale src="${pageContext.request.contextPath}/images/background.jpg" width="200"></image:scale>
<br>
按照<font color="red">宽度</font>缩放图片的例子代码:
<pre name="code" class="html">
<%
        out.println( HtmlUtils.htmlEscape("<image:scale src=\"${pageContext.request.contextPath}/images/background.jpg\" width=\"200\"></image:scale>") );
%>
</pre>

<hr>

按照<font color="red">高度</font>缩放图片的例子效果:<br>
<image:scale src="${pageContext.request.contextPath}/images/background.jpg" height="200"></image:scale>
<br>
按照<font color="red">高度</font>缩放图片的例子代码:
<pre name="code" class="html">
<%
        out.println( HtmlUtils.htmlEscape("<image:scale src=\"${pageContext.request.contextPath}/images/background.jpg\" height=\"200\"></image:scale>") );
%>
</pre>

<hr>

鼠标悬停到链接上预览图片(按照<font color="red">宽度</font>缩放图片)例子效果:<br><br>
<image:previewLink imageId="previewImageId1" linkId="imageLinkId1" linkTxt="预览图片" imageSrc="${pageContext.request.contextPath}/images/background.jpg" imageWidth="100" onClick="alert('t1');"></image:previewLink>
<br>
鼠标悬停到链接上预览图片(按照<font color="red">宽度</font>缩放图片)例子代码:
<pre name="code" class="html">
<%
        out.println( HtmlUtils.htmlEscape("<image:previewLink imageId=\"previewImageId1\" linkId=\"imageLinkId1\" linkTxt=\"预览图片\" imageSrc=\"${pageContext.request.contextPath}/images/background.jpg\" imageWidth=\"100\" onClick=\"alert('t1');\"></image:previewLink>") );
%>
</pre>

<hr>

鼠标悬停到链接上预览图片(按照<font color="red">高度</font>缩放图片)例子效果:<br><br>
<image:previewLink imageId="previewImageId2" linkId="imageLinkId2" linkTxt="预览图片" imageSrc="${pageContext.request.contextPath}/images/background.jpg" imageHeight="100" onClick="alert('t2');"></image:previewLink>
<br>
鼠标悬停到链接上预览图片(按照<font color="red">高度</font>缩放图片)例子代码:
<pre name="code" class="html">
<%
        out.println( HtmlUtils.htmlEscape("<image:previewLink imageId=\"previewImageId2\" linkId=\"imageLinkId2\" linkTxt=\"预览图片\" imageSrc=\"${pageContext.request.contextPath}/images/background.jpg\" imageHeight=\"100\" onClick=\"alert('t2');\"></image:previewLink>") );
%>
</pre>
</body>
</html>