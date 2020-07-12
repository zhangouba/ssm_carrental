<%--
  Created by IntelliJ IDEA.
  User: zhangouba
  Date: 2020/2/21
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        pageContext.setAttribute("ctx",request.getContextPath());
    %>
    <title>客户地区统计</title>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/echarts/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
     $.post("${ctx}/stat/loadOpernameYearAreaStatJson.action",function (data) {
         var dom = document.getElementById("container");
         var myChart = echarts.init(dom);
         var app = {};
         option = null;
         option = {
             title: {
                 text: '客户地区统计',
                 subtext: '真实有效',
                 left: 'center'
             },
             tooltip: {
                 trigger: 'item',
                 formatter: '{a} <br/>{b} : {c} ({d}%)'
             },
             legend: {
                 orient: 'vertical',
                 left: 'left',
                 data: data,
             },
             series: [
                 {
                     name: '客户数量以及占比',
                     type: 'pie',
                     radius: '55%',
                     center: ['50%', '60%'],
                     data: data,
                     emphasis: {
                         itemStyle: {
                             shadowBlur: 10,
                             shadowOffsetX: 0,
                             shadowColor: 'rgba(0, 0, 0, 0.5)'
                         }
                     }
                 }
             ]
         };
         ;
         if (option && typeof option === "object") {
             myChart.setOption(option, true);
         }
     })
</script>
</body>
</html>