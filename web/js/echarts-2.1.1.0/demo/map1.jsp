<%--
  Created by IntelliJ IDEA.
  User: yh.zeng
  Date: 15-1-16
  Time: 下午4:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>地图例子1</title>
    <script src="${pageContext.request.contextPath}/js/echarts-2.1.1.0/min/echarts.js"></script>
</head>
<body>
<div id="mainMap" style="height:500px;border:1px solid #ccc;padding:10px;"></div>

<script type="text/javascript">
    require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/js/echarts-2.1.1.0/min'
        }
    });

    require(
            [
                'echarts',
                'echarts/chart/map'
            ],
            function (ec) {
                // --- 地图 ---
                var myChart2 = ec.init(document.getElementById('mainMap'));
                myChart2.setOption({
                    tooltip: {
                        trigger: 'item',
                        formatter: '{b}'
                    },
                    series: [
                        {
                            name: '中国',
                            type: 'map',
                            mapType: 'china',
                            selectedMode: 'multiple',
                            itemStyle: {
                                normal: {label: {show: true}},
                                emphasis: {label: {show: true}}
                            },
                            data: [
                                {name: '广东', selected: true}
                            ]
                        }
                    ]
                });
            }
    );
</script>
</body>
</html>