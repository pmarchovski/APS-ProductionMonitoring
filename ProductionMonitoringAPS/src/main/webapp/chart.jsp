<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>


<jsp:include page="header.jsp"/>


<body>

<form action="chart" method="get">

<input type="submit"
       value="submit"
       Class="button">


 </form>

<canvas id="myChart" style="width:100%;max-width:1200px"></canvas>

<script>

var xValues = ${production_capacity_by_weeks_xValue};
var yValues = ${production_capacity_by_weeks_yValue};
var barColors = ${production_capacity_by_weeks_bar_color};

var mixedChart = new Chart("myChart", {
	   type: 'bar',
	   data: {
	       datasets: [{
	           label: 'Bar Dataset',
	           data: yValues,
	           backgroundColor: barColors,
	           // this dataset is drawn below
	           order: 2
	       }, {
	           label: 'Line Dataset',
	           data: yValues,
	           fill: false,
	           borderColor: "orange",
	           type: 'line',
	           // this dataset is drawn on top
	           order: 1
	       }],
	       labels: xValues
	   },
	   options: {
		    legend: {display: false},
		    title: {
		      display: true,
		      text: "${production_capacity_by_weeks_year}"
		    }
		  }
	});

</script>


</body>
</html>