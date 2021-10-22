<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script src="https://jquery-3.3.1.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
   
<jsp:include page="header.jsp" />
<body>

<div id="loader" class="center" style="display:none"><img src="pictures/spinner.gif"></div>


<div id="div_main_container_two">
<h3>Производствен капацитет</h3>

</div>
	<div>
		<h3>${before_path}${path }</h3>
	
	</div>

		<div id="div_main_container_two">

			<div Class="div_left_container">
				<table>
					<tr>
						<td Class="e">
							<form id="upload_files_form" action="admin_servlet_upload_files_for_production_capacity" method="post" enctype="multipart/form-data">
							



								<p>Избери файл</p>
								<input type="file" name="fileToUpload" Class="button" multiple>
								<br />
								<br /> <input type="submit" value="Изпрати" Class="button" id="click_upload_file">
	
							</form>

 ${admin_servlet_upload_data_into_coois }


						</td>
						</tr>
                   </table>
                   
                   <table>			
							<form action="printers_production_servlet_production_capacity_servlet" method="GET" id="start">
				
					<tr>

						<td Class="e">
						<label Class="label_form">Година</label> 
						<select
							name="production_capacity_year" class="input_small">
							    
							    <option value="${production_capacity_select_year }">${production_capacity_select_year }</option>
							    <option value="2018">2018</option>
							    <option value="2019">2019</option>
							    <option value="2020">2020</option>
							    <option value="2021">2021</option>
								<option value="2022">2022</option>
								<option value="2023">2023</option>
								<option value="2024">2024</option>
		
						</select>
						</td>

						<td Class="e">
						<label Class="label_form">Покажи по:</label> 
						<select
							name="production_capacity_month" class="input_small">
							
							    <option value="${production_capacity_select_month }">${production_capacity_select_month }</option>
								<option value="Седмици">Седмици</option>
								<option value="Месеци">Месеци</option>
						</select>
						</td>

						<td Class="e"><input 
					        type="submit" 
					        value="Покажи"
							Class="button"
							id="getData">
							
							</td>
					
					</tr>

					</form>

				</table>
				
			<div Class="div_right_container">
				<table>

					${production_capacity_requirement_operators_per_main_head }
					<tr>
						${production_capacity_requirement_operators_per_month_head }
						${production_capacity_requirement_operators_per_month_data }
					</tr>
				</table>
			</div>
			
			</div>

<div id="div_main_container_two">

			<div Class="div_left_container">
				<canvas id="myChart" style="width: 100%; max-width: 1200px"></canvas>


				<script>
				
				var xValues = ${production_capacity_by_weeks_xValue};
				var yValues = ${production_capacity_by_weeks_yValue};
				var yValuesLine = ${production_available_time_by_week};
				var barColors = ${production_capacity_by_weeks_bar_color};

				var mixedChart = new Chart("myChart", {
					type : 'bar',
					data : {
						datasets : [ {
							label : 'Requirement time human/hours',
							data : yValues,
							backgroundColor : barColors,
							// this dataset is drawn below
							order : 1
						}, {
							label : 'Available time human/hours',
							data : yValuesLine,
							fill : true,
							borderColor : "red",
							backgroundColor : "orange",
							type : 'line',
							// this dataset is drawn on top
							order : 2
						} ],
						
						labels : xValues
					},
					options : {
						legend : {
							display : true
						},
						title : {
							display : true,
							text : "${production_capacity_by_weeks_year}"
						}
					}
				});
				</script>
			</div>
		</div>
	</div>


 <script>
 $(document).ready(function(){
	 
	 $("#getData").on("click", function(){
		 
		 $.ajax({
			 
			 type:"GET",
			 url:"printers_production_servlet_production_capacity_servlet",
			 beforeSend: function(){
				 $("#loader").show();
				 },
			 complete: function(){
				 $("#loader").hide();
				 }
		 });		 
	 });	 
 });
 
 </script>


 <script>
 $(document).ready(function(){
	 
	 $("#upload_files_form").submit(function(){
		    var $form = $(this);

		    $.ajax({
		     type     : "POST",
		     cache    : false,
		     url      : $form.attr('action'),
		     data     : $form.serializeArray(),
			 beforeSend: function(){
				 $("#loader").show();
				 },
			 complete: function(){
				 $("#loader").hide();
				 }
		 });		 
	 });	 
 });
 
 </script>
 


</body>
</html>