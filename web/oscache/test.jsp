<%@ page language="java" import="java.util.Date" pageEncoding="utf-8"%>
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript">
       function clearCache(){
           $.post("${pageContext.request.contextPath}/servlet/clearCacheServlet",{cachekey:"/oscache/test.jsp"},function(result){
               if(result == "success"){
                  alert("删除缓存成功！");
               }else{
                  alert("删除缓存失败！");
               }
           });
           return false;
       }
    </script>
</head>
<body>
时间：<%=new Date()%>
<br>
<a href="javascript:void(0)" onclick="clearCache()">清除缓存</a>
</body>
</html>


