<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="header.jsp" />
<body>

	

		<div id="div_main_container">
		
			<div Class="div_right_container">

				<form method="post" action="printers_production_servlet_presents_blank">

					<label id="label_error"> ${monthly_presence_blank_empty_date_error_massage }</label> <br>
					<br> <input type="date"
						        name="monthly_presence_blank_start_date" 
						        Class="input_small"
						        value=${monthly_presence_blank_start_date }> 
						
						
						 <input type="date"
						        name="monthly_presence_blank_end_date" 
						        Class="input_small"
						        value=${monthly_presence_blank_end_date }> 
						${empty_date_fields_massage } 
						<br><br> 
						  <label Class="label_form">Тийм лидер</label> 
						  <select
						        name="monthly_presence_blank_team_leader_name" 
						        Class="input_less">

						<option value="Всички">Всички</option>

						<c:forEach items="${team_leaders_List }" var="teamLeadersList">
							<option value="${teamLeadersList }">${teamLeadersList }</option>

						</c:forEach>
					</select>

					<div>
						<table>

							<td Class="k">
							<input type="submit" 
							       Class="button"
								   value="Генерирай">
								</form></td>

							<form method="get" action="printers_production_servlet_presents_blank">

								<td Class="k">
								<input type="submit" 
								       Class="button"
									   value="Изтегли в ексел"></td>
							</form>

						</table>

					</div>
					<br><br>
			<div id="div_main_overflow">		
					<div id="div_main_container">
						<table Class="a">
                        
							<thead Class="thead">
								<tr>${monthly_present_blank_table_head_date }</tr>
								<tr>${monthly_present_blank_table_head_week_day }</tr>
							</thead>

							<tbody Class="tbody">
								<tr>${monthly_present_blank_table_body_data }</tr>
							</tbody>

						</table>

					</div>
			</div>
		</div>
</body>
</html>