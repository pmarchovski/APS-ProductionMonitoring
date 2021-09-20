<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
	
<jsp:include page="header.jsp"/>
<body>

<div>
<h4>${before_path} ${path }</h4><br><br>
</div>

<div id="div_main_overflow">
<div id="div_main_container_two">

<div Class="div_left_container">
<table>
<tr>
<th Class="d">
<form action="upload" method="POST" enctype="multipart/form-data">
					
			<p>Избери файл</p>
			<input type="file" 
			       name="fileToUpload" 
			       Class="button"
			       multiple>
			<br/><br/>
			<input type="submit" 
			       value="Изпрати" 
			       Class="button">
</form>
</th>


<th Class="d">
<form action="production_capacity_servlet" method="GET">

           


</th>
</tr>

<tr>

<td Class="c">

<label Class="label_form">Година</label> <select
							name="production_capacity_year" class="input_small">
							<option value="2021">2021</option>
							<option value="2022">2022</option>				
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2020">2020</option>
							<option value="2019">2019</option>
							<option value="2018">2018</option>
						</select> 



</td>

<td Class="c">

<label Class="label_form">Покажи по:</label> <select
							name="production_capacity_month" class="input_small">
							<option value="by_weeks">Седмици</option>
							<option value="by_months">Месеци</option>
						</select> 

</td>

<td Class="c">

 <input type="submit" 
			       value="Покажи" 
			       Class="button">

</td>

</tr>
</form>

</table>
</div>

<div Class="div_right_container">
<table>

<th colspan="12">Необходоми оператори по месеци ${year }</th>
<tr>
${production_capacity_requirement_operators_per_month_head }
${production_capacity_requirement_operators_per_month_data }
</tr>
</table>
</div>


</div><br>

<div id="div_main_container_two">

<div Class="div_left_container">
<canvas id="myChart" style="width:100%;max-width:1200px"></canvas>


<script>

var xValues = ${production_capacity_by_weeks_xValue};
var yValues = ${production_capacity_by_weeks_yValue};
var yValuesLine = ${production_available_time_by_week};
var barColors = ${production_capacity_by_weeks_bar_color};

var mixedChart = new Chart("myChart", {
	   type: 'bar',
	   data: {
	       datasets: [{
	           label: 'Bar Dataset',
	           data: yValues,
	           backgroundColor: barColors,
	           // this dataset is drawn below
	           order: 1
	       }, {
	           label: 'Line Dataset',
	           data: yValuesLine,
	           fill: false,
	           borderColor: "orange",
	           type: 'line',
	           // this dataset is drawn on top
	           order: 2
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
</div>



</div>
</div>

</body>
</html>