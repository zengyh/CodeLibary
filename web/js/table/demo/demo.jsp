<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 14-9-21
  Time: 下午12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@include file="/common.jspf" %>
<%@include file="/demoHead.jspf" %><%-- 引用代码高亮插件syntaxHighliter --%>
<html>
<head>
    <title>表格各行换色例子</title>
    <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/js/table/tablecolor.js"></script>
    <script>
       $(function(){
           TableColor.changeTableColorSample1("table1");
           TableColor.changeTableColorSample1("table2","#ddd2b2","#5c7cff","#96596F");
       });
    </script>
</head>

<body>
<table id="table1">
   <thead>
      <tr>
          <td>序号</td>
          <td>标题1</td>
          <td>标题2</td>
          <td>标题3</td>
          <td>标题4</td>
          <td>标题5</td>
      </tr>
   </thead>
   <tbody>
      <tr>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
      </tr>
      <tr>
          <td>2</td>
          <td>2</td>
          <td>2</td>
          <td>2</td>
          <td>2</td>
          <td>2</td>
      </tr>
      <tr>
          <td>3</td>
          <td>3</td>
          <td>3</td>
          <td>3</td>
          <td>3</td>
          <td>3</td>
      </tr>
      <tr>
          <td>4</td>
          <td>4</td>
          <td>4</td>
          <td>4</td>
          <td>4</td>
          <td>4</td>
      </tr>
   </tbody>
</table>

<br>
<br>
<table id="table2">
   <thead>
      <tr>
          <td>序号</td>
          <td>标题1</td>
          <td>标题2</td>
          <td>标题3</td>
          <td>标题4</td>
          <td>标题5</td>
      </tr>
   </thead>
   <tbody>
      <tr>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
          <td>1</td>
      </tr>
      <tr>
          <td>2</td>
          <td>2</td>
          <td>2</td>
          <td>2</td>
          <td>2</td>
          <td>2</td>
      </tr>
      <tr>
          <td>3</td>
          <td>3</td>
          <td>3</td>
          <td>3</td>
          <td>3</td>
          <td>3</td>
      </tr>
      <tr>
          <td>4</td>
          <td>4</td>
          <td>4</td>
          <td>4</td>
          <td>4</td>
          <td>4</td>
      </tr>
   </tbody>
</table>

<br>
<br>
代码:
<pre name="code" class="html">
<%
        out.println( HtmlUtils.htmlEscape(
                "<script type=\"text/javascript\" src=\"/js/jquery-1.4.2.min.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"/js/table/table.js\"></script>\n" +
                "<script>\n" +
                "       $(function(){\n" +
                "           changeTableColorSample1(\"table1\");\n" +
                "           changeTableColorSample1(\"table2\",\"#ddd2b2\",\"#5c7cff\",\"#96596F\");\n" +
                "       });\n" +
                "</script>\n\n" +
                "<table id=\"table1\">\n" +
                "   <thead>\n" +
                "      <tr>\n" +
                "          <td>序号</td>\n" +
                "          <td>标题1</td>\n" +
                "          <td>标题2</td>\n" +
                "          <td>标题3</td>\n" +
                "          <td>标题4</td>\n" +
                "          <td>标题5</td>\n" +
                "      </tr>\n" +
                "   </thead>\n" +
                "   <tbody>\n" +
                "      <tr>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "      </tr>\n" +
                "   </tbody>\n" +
                "</table>\n" +
                "\n" +
                "<br>\n" +
                "<br>\n" +
                "<table id=\"table2\">\n" +
                "   <thead>\n" +
                "      <tr>\n" +
                "          <td>序号</td>\n" +
                "          <td>标题1</td>\n" +
                "          <td>标题2</td>\n" +
                "          <td>标题3</td>\n" +
                "          <td>标题4</td>\n" +
                "          <td>标题5</td>\n" +
                "      </tr>\n" +
                "   </thead>\n" +
                "   <tbody>\n" +
                "      <tr>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "          <td>1</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "          <td>2</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "          <td>3</td>\n" +
                "      </tr>\n" +
                "      <tr>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "          <td>4</td>\n" +
                "      </tr>\n" +
                "   </tbody>\n" +
                "</table>" ) );
%>
</pre>

</body>

</html>