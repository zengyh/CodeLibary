<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 14-8-16
  Time: 下午7:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/js/table/table.js"></script>
<style type="text/css">
    h1{
        background-color: #ddd2b2;
    }
</style>
<script>
   $(function(){
       changeTableColorSample1("table1");
       changeTableColorSample1("table2","#ddd2b2","#5c7cff","#96596F");
   });
</script>
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
</html>