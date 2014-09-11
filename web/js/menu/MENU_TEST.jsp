<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 14-8-23
  Time: 下午8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title></head>
<script type="text/javascript" src="MENU.js"></script>
<link type="text/css" rel="stylesheet" href="MENU2.css">
<script type="text/javascript">
    window.onload = function(){

        //右键页面显示菜单弹出框
        var menu = new MENU();
        menu.init(document.getElementsByTagName("body" )[0],"contextmenu");
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
    }
</script>
<body style="width: 100%; height: 100%;">
<input type="text" value="点击我显示菜单" id="thisText"/>
</body>
</html>