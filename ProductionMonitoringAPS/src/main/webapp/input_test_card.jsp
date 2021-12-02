<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_fixed">

 <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Въвеждане на фишове от операция тест</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

<div id="div_main_overflow">
<div id="div_main_container">
<div Class="div_left_container">

${done_status }

<p>
<form method="get" action="printers_production_servlet_input_production_card">

<label Class="label_form">Дата</label>
<input type="date"
       name="input_test_card_date"
       class="input_less"><br><br>

<label Class="label_form">Поръчка</label>       
<input type="number"
       name="input_test_card_production_order"
       placeholder="Номер на поръчка"
       min="100000"
       max="999999"
       class="input_less"><br><br>

 <table>
 
 <tr Class="tr">
 <td Class="d">
 <label Class="label_form">Блед печат</label>
 </td>
 
 <td Class="e">
 <input type="number"
       name="input_test_card_pale_print"
       class="input_small_padding"
       value="0">
 </td>
  <td Class="d">
  
 <label Class="label_form">Термистор</label>
 </td>
  <td Class="e">
 <input type="number"
       name="input_test_card_thermistor"
       class="input_small_padding"
       value="0">
 </td>
 </tr>
 
 
 
  <tr Class="tr">
 <td Class="d">
 <label Class="label_form">Сбит печат</label>
 </td>
 
 <td Class="e">
 <input type="number"
       name="input_test_card_concise_print"
       class="input_small_padding"
       value="0">
 </td>
  <td Class="d">
  
 <label Class="label_form">Основна платка</label>
 </td>
  <td Class="e">
 <input type="number"
       name="input_test_card_main_board"
       class="input_small_padding"
       value="0">
 </td>
 </tr>
 
 
   <tr Class="tr">
 <td Class="d">
 <label Class="label_form">Опто сензор</label>
 </td>
 
 <td Class="e">
 <input type="number"
       name="input_test_card_opto_sensor"
       class="input_small_padding"
       value="0">
 </td>
  <td Class="d">
  
 <label Class="label_form">Помощна платка</label>
 </td>
  <td Class="e">
 <input type="number"
       name="input_test_card_second_board"
       class="input_small_padding"
       value="0">
 </td>
 </tr>
 
 
    <tr Class="tr">
 <td Class="d">
 <label Class="label_form">Мотор</label>
 </td>
 
 <td Class="e">
 <input type="number"
       name="input_test_card_motor"
       class="input_small_padding"
       value="0">
 </td>
  <td Class="d">
  
 <label Class="label_form">Счупен компонент</label>
 </td>
  <td Class="e">
 <input type="number"
       name="input_test_card_broken_component"
       class="input_small_padding"
       value="0">
 </td>
 </tr>
 
 
     <tr Class="tr">
 <td Class="d">
 <label Class="label_form">TPH</label>
 </td>
 
 <td Class="e">
 <input type="number"
       name="input_test_card_tph"
       class="input_small_padding"
       value="0">
 </td>
  <td Class="d">
  
 <label Class="label_form">Мех. дефект</label>
 </td>
  <td Class="e">
 <input type="number"
       name="input_test_card_mechanical_deffect"
       class="input_small_padding"
       value="0">
 </td>
 </tr>
 
 
      <tr Class="tr">
 <td Class="d">
 <label Class="label_form">Шумен</label>
 </td>
 
 <td Class="e">
 <input type="number"
       name="input_test_card_noise"
       class="input_small_padding"
       value="0">
 </td>
  <td Class="d">
  
 <label Class="label_form">Други</label>
 </td>
  <td Class="e">
 <input type="number"
       name="input_test_card_others"
       class="input_small_padding"
       value="0">
 </td>
 </tr>
 
 </table>


<input type="submit"
       value="Въведи"
       class="button">

</form>
<p>







</div>



<div Class="div_left_container">

<form method="get" action="printers_production_servlet_test_card_display_info_servlet">

<table>

<tr>
<th Class="d">
<label Class="label_form">Година</label>
<select name="input_test_card_year"
        class="input_small"
        required="true">


<option value="${test_card_display_choise_year }" >${test_card_display_choise_year }</option>
<option value="2017">2017</option>
<option value="2018">2018</option>
<option value="2019">2019</option>
<option value="2020">2020</option>
<option value="2021">2021</option>
<option value="2022">2022</option>

</select><br><br>

</th>

<th Class="d">

<label Class="label_form">Месец</label>
<select name="input_test_card_month"
        class="input_small"
        required="true">

<option value="${test_card_display_choise_month }">${test_card_display_choise_month }</option>
<option value="Всички">Всички</option>
<option value="1">January</option>
<option value="2">February</option>
<option value="3">March</option>
<option value="4">April</option>
<option value="5">May</option>
<option value="6">June</option>
<option value="7">July</option>
<option value="8">August</option>
<option value="9">September</option>
<option value="10">October</option>
<option value="11">November</option>
<option value="12">December</option>

</select><br><br>

</th>

<th Class="d">
<label Class="label_form">Производство</label>
<select name="input_test_card_department"
        class="input_small"
        required="true">

<option value="${test_card_display_choise_department }">${test_card_display_choise_department }</option>
<option value="INDUSTRIAL">INDUSTRIAL</option>
<option value="AUTO">AUTO</option>

</select><br><br>

</th>

<th Class="d">
<label Class="label_form">Тип графика</label>
<select name="input_test_card_chart_type"
        class="input_small"
        required="true">

<option value="${test_card_display_choise_chart_type }">${test_card_display_choise_chart_type }</option>
<option value="PARETO">PARETO</option>
<option value="MAIN">MAIN</option>

</select><br><br>

</th>


</tr>

</table>


<input type="submit"
       value="Покажи"
       Class="button">

</form>

<canvas id="myChart" style="width:100%;max-width:1200px"></canvas>


<script>

var xValues = ${test_card_display_xValue};
var yValues = ${test_card_display_yValue};
var yValuesLine = ${test_card_display_yValueTarget};
var barColors = ${test_card_display_color};

var mixedChart = new Chart("myChart", {
	   type: 'bar',
	   
	   data: {
	       datasets: [{
	           label: 'data',
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
		   
${precentage}
		   	 
		    legend: {display: false},
		    title: {
		      display: true,
		      text: "${test_card_display_name_year_month_type}"
		    }
		  }
	});

</script>



</div>
</div>
</div>

</body>
</html>