<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_fixed">

   <table>
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/Info.png"></td>
      <td Class="a"><h2>Информация за поръчки по години</h2></td>   
    </tr>
   </table>

</div>

<div id="div_empty"></div>


<div id="div_main_container">
	<form method="get"
		action="printers_production_servlet_orders_information_servlet">

		<select name="orders_info_select_year" Class="input_small">

			<option value="1">Всички</option>
			<option value="2021">2021</option>
			<option value="2020">2020</option>
			<option value="2019">2019</option>
			<option value="2018">2018</option>
			<option value="2022">2022</option>


		</select> <input type="submit" value="GET INFO" Class="button">

	</form>
</div>

<h3>Количества поръчки по група изделие и години</h3>

<br><br>

<div id="div_main_overflow">

	${production_order_quantity_info }
</div>

</body>
</html>