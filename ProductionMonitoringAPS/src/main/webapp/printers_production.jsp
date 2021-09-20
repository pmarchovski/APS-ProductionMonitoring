<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

	<div id="div_main_container">


		<table>
			<tr>
				<th Class="c">Фишове</th>
				<th Class="c">Служители</th>
				<th Class="c">Производство</th>
				<th Class="c">Други</th>
				<th Class="c">Справки</th>
			</tr>

			<tr>
				<td Class="c"><a href="input_production_card.jsp">Производствени фишове</a></td>
				<td Class="c"><a href="include_operators.jsp">Въвеждане на служител</a></td>
				<td Class="c"><a href="production_capacity.jsp">Производстен капацитет</a></td>
				<td Class="c"><a href="public_holidays.jsp">Въвеждане на празнични дни</a></td>
			</tr>

			<tr>
				<td Class="c"><a href="input_test_card.jsp">Фишове от тест</a></td>
				<td Class="c"><a href="absents_operators.jsp">Въвеждане на отсъствия</a></td>
				<td Class="c"><a href="production_plan_generate.jsp">Генериране на производствени планове</a></td>
				<td Class="c"> </td>
			</tr>
			
			<tr>
				<td Class="c"><a href="#">Добавяне на операции</a></td>
				<td Class="c"><a href="edit_operators.jsp">Редактиране на информация за служител</a></td>
				<td Class="c"> </td>
			</tr>
			
			<tr>
				<td Class="c"><a href="#">Редактиране на операции</a></td>
				<td Class="c"><a href="display_operator_info.jsp">Покажи информация за служител</a></td>
				<td Class="c"> </td>
			</tr>
			
			<tr>
				<td Class="c"></td>
				<td Class="c"><a href="monthly_presence_blank.jsp">Генериране на месечна форма за присъствие</a></td>
				<td Class="c"> </td>
			</tr>
			
			<tr>
				<td Class="c"></td>
				<td Class="c"><a href="hole_operators_info.jsp">Пълна информация за служителите</a></td>
				<td Class="c"> </td>
			</tr>


		</table>


	</div>

</body>
</html>