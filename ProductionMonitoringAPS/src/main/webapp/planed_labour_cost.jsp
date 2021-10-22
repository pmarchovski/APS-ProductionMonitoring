<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_two">
<h2>Планирани разходи за труд</h2>

</div>


	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="printers_production_servlet_planed_labor_cost">

				<table>


					<tr>
					<td Class="e">
					<input type="submit"
                    value="Покажи"
                     class="button">
                     </td>
					</tr>

				</table>
		</div>

	</div>
	
	<div id="div_main_container">
	
	<table>
	
	${planet_production_cost_head }
	${planet_production_cost_body }
	${real_production_cost_body_second_row }
	
	
	</table>

	</div>
	
	
	<canvas id="myChart" style="width:100%;max-width:1200px"></canvas>


<script>

var xValues = ${chart_planed_production_cost_xValue};
var yValuesPlaned = ${chart_planed_production_cost_yValue};
var yValuesReal = ${chart_real_production_cost_yValue};


var mixedChart = new Chart("myChart", {
	   type: 'bar',
	   
	   data: {
	       datasets: [{
	           label: 'Планирани разходи за труд BGN',
	           data: yValuesPlaned,
	           backgroundColor: "blue",
	           // this dataset is drawn below
	           order: 1
	       }, {
	           label: 'Реални разходи за труд BGN',
	           data: yValuesReal,
	           fill: true,
	           backgroundColor: "orange",
	           type: 'line',
	           // this dataset is drawn on top
	           order: 2
	       }],
	       labels: xValues
	   },
	   options: {
 
		    legend: {display: true},
		    title: {
		      display: true,
		      text: "Справка разходи за труд"
		    }
		  }
	});

</script>
	
</body>
</html>