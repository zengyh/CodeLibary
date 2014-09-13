<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 14-8-23
  Time: 下午8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.springframework.web.util.HtmlUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common.jspf"%>
<%@include file="/demoHead.jspf"%>
<html>
<head><title>menu test</title></head>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu/MENU.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/menu/MENU2.css">
<script type="text/javascript">
    //addEvent是本人写的一个追加事件的方法，见tool.js
    addEvent(window,"load",function(){

        //右键页面显示菜单弹出框
        var menu = new MENU();
        menu.init(thisDiv,"contextmenu");
        menu.addItem({id: "1",txt:"测试1" ,img:"/images/menu/1.JPG",enable: true,click: function(){alert("1");}});
        menu.addItem({id: "2",txt:"测试2" ,img:"/images/menu/4.jpg",enable: false});
        menu.addItem({id: "3",txt:"测试2" ,enable: true});
        menu.addItem({id: "4",txt:"测试2" ,img:"/images/menu/5.jpg",enable: true,click:function(){alert("5");}});
        menu.setClickFunc("3" ,function(){alert("3");});
        menu.setClickFunc("2" ,function(){alert("2");});

        //点击显示菜单
        var menu2 = new MENU();
        menu2.init("thisText","click");
        menu2.addItem({id: "2_1",txt:"测试1" ,img:"/images/menu/1.JPG",enable: true});
        menu2.addItem({id: "2_4",txt:"测试2" ,img:"/images/menu/5.jpg",enable: true});
        menu2.setClickFunc("2_1" ,function(){alert("2_1");});
        menu2.setClickFunc("2_4" ,function(){alert("2_4");});
    });
</script>
<body style="width: 100%; height: 100%;">
<input type="text" value="点击我显示菜单" id="thisText"/>
<div style="width: 100px; height: 100px;background-color: #f5f5dc;padding-top:40px;" id="thisDiv">
   右击我显示菜单
</div>
<br>
<br>
<br>
代码：
<pre name="code" class="html">
<%
    out.print( HtmlUtils.htmlEscape(
            "<script type=\"text/javascript\">\n" +
            "    //addEvent是本人写的一个追加事件的方法，见tool.js\n" +
            "    addEvent(window,\"load\",function(){\n" +
            "\n" +
            "        //右键页面显示菜单弹出框\n" +
            "        var menu = new MENU();\n" +
            "        menu.init(thisDiv,\"contextmenu\");\n" +
            "        menu.addItem({id: \"1\",txt:\"测试1\" ,img:\"/images/menu/1.JPG\",enable: true,click: function(){alert(\"1\");}});\n" +
            "        menu.addItem({id: \"2\",txt:\"测试2\" ,img:\"/images/menu/4.jpg\",enable: false});\n" +
            "        menu.addItem({id: \"3\",txt:\"测试2\" ,enable: true});\n" +
            "        menu.addItem({id: \"4\",txt:\"测试2\" ,img:\"/images/menu/5.jpg\",enable: true,click:function(){alert(\"5\");}});\n" +
            "        menu.setClickFunc(\"3\" ,function(){alert(\"3\");});\n" +
            "        menu.setClickFunc(\"2\" ,function(){alert(\"2\");});\n" +
            "\n" +
            "        //点击显示菜单\n" +
            "        var menu2 = new MENU();\n" +
            "        menu2.init(\"thisText\",\"click\");\n" +
            "        menu2.addItem({id: \"2_1\",txt:\"测试1\" ,img:\"/images/menu/1.JPG\",enable: true});\n" +
            "        menu2.addItem({id: \"2_4\",txt:\"测试2\" ,img:\"/images/menu/5.jpg\",enable: true});\n" +
            "        menu2.setClickFunc(\"2_1\" ,function(){alert(\"2_1\");});\n" +
            "        menu2.setClickFunc(\"2_4\" ,function(){alert(\"2_4\");});\n" +
            "    });\n" +
            "</script>\n" +
            "<input type=\"text\" value=\"点击我显示菜单\" id=\"thisText\"/>\n" +
            "<div style=\"width: 100px; height: 100px;background-color: #f5f5dc;padding-top:40px;\" id=\"thisDiv\">\n" +
            "   右击我显示菜单\n" +
            "</div>" ) );
%>
</pre>
</body>
</html>