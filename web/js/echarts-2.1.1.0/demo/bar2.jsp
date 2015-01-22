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
    <title>柱状图例子2</title>
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
                        text: '未来一周气温变化',
                        subtext: '纯属虚构'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        data: ['最高气温', '最低气温']
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
                            boundaryGap: false,
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value',
                            axisLabel: {
                                formatter: '{value} °C'
                            }
                        }
                    ],
                    series: [
                        {
                            name: '最高气温',
                            type: 'line',
                            data: [11, 11, 15, 13, 12, 13, 10],
                            markPoint: {
                                data: [
                                    {type: 'max', name: '最大值'},
                                    {type: 'min', name: '最小值'}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        },
                        {
                            name: '最低气温',
                            type: 'line',
                            data: [1, -2, 2, 5, 3, 2, 0],
                            markPoint: {
                                data: [
                                    {name: '周最低', value: -2, xAxis: 1, yAxis: -1.5}
                                ]
                            },
                            markLine: {
                                data: [
                                    {type: 'average', name: '平均值'}
                                ]
                            }
                        }
                    ]
                });
                window.onresize = myChart.resize;
            }
    );
</script>
</body>
</html>