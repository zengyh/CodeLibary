﻿<%--
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
    <title>柱状图例子1</title>
    <script src="${pageContext.request.contextPath}/js/echarts-2.1.1.0/min/echarts.js"></script>
</head>
<body>
<div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
<script type="text/javascript">
    require.config({
        paths: {
            echarts: '${pageContext.request.contextPath}/js/echarts-2.1.1.0/min'
        }
    });

    require(
            [
                'echarts',
                'echarts/chart/line',
                'echarts/chart/bar'
            ],
            function (ec) {
                //--- 折柱 ---
                var myChart = ec.init(document.getElementById('main'));
                myChart.setOption({
                    title: {
                        text: '降雨量和蒸发量变化',
                        subtext: '纯属虚构'
                    },
                    tooltip: {
                        //trigger: 'axis',  //鼠标在图形上移动时出现竖线，并弹出冒泡框
                        trigger: 'item',    //鼠标在图形上移动不会出现竖线，只有移动到对应的点（数据）才会弹出冒泡框，默认值是item
                        show: true
                    },
                    legend: {
                        data: ['蒸发量', '降水量']
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: {show: true},
                            dataZoom: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            splitArea: {show: true}
                        }
                    ],
                    series: [
                        {
                            name: '蒸发量',
                            type: 'bar',
                            data: [2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                        },
                        {
                            name: '降水量',
                            type: 'bar',
                            data: [2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                        }
                    ]
                });
                window.onresize = myChart.resize;
            }
    );
</script>
</body>
</html>