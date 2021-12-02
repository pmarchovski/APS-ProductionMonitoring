<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_two">
<h2>Информация за служители</h2>

</div>

	<div id="div_main_overflow">

		<div id="div_main_container">

			<div Class="div_left_container">

				<form method="get" action="printers_production_servlet_display_operators_info">


					<label Class="label_form">Оператор</label> <select
						name="display_operator_info_operator_name" Class="input">
						<option value="Всички">Всички</option>
						<c:forEach items="${operators_name_collection}"
							var="operatorsNameCollection">

							<option value="${operatorsNameCollection}">${operatorsNameCollection}</option>
						</c:forEach>

					</select> 
					
					<br><br>
					
					<label Class="label_form">Тийм лидер</label> <select
						name="display_operator_info_team_leader_name" Class="input">
						<option value="Всички">Всички</option>
						<c:forEach items="${team_leaders_List }" var="teamLeadersList">
							<option value="${teamLeadersList }">${teamLeadersList }</option>

						</c:forEach>

					</select> 
					
					<br><br>
					<label Class="label_form">Тип оператор</label>
					<table>
						<tr>
							<td Class="a"><input type="checkbox"
								name="display_operator_info_skills"
								value="Оператор механичен монтаж" />Оператор механичен монтаж</td>

							<td Class="a"><input type="checkbox"
								name="display_operator_info_skills" value="Оператор тест" />Оператор
								тест</td>

							<td Class="a"><input type="checkbox"
								name="display_operator_info_skills" value="Оператор ел. монтаж" />Оператор
								ел. монтаж</td>

							<td Class="a"><input type="checkbox"
								name="display_operator_info_skills" value="Оператор опаковка" />Оператор
								опаковка</td>
						</tr>
					</table>

					<br><br>

						<label Class="label_form">Активен да/не</label> <select
							name="display_operator_info_isActive" class="input_less">
							<option value="Да">Да</option>	
							<option value="Не">Не</option>
							<option value="Всички">Всички</option>
						</select> 
						
						<br><br>
						<label Class="label_form">Майчинство да/не</label> <select
							name="display_operator_info_isMotherhood" class="input_less">	
							<option value="Не">Не</option>
							<option value="Да">Да</option>
							<option value="Всички">Всички</option>
						</select>
					<br><br>

						<input type="submit" value="Покажи" Class="button">
			</div>

			<div Class="div_right_container">

				<table>

                   <tr><h4>Конфигуриране на таблицата</h4></tr>


                    <tr>
                    <th Class="a"><label Class="label_form">Трите имена</label></th>
                    <th Class="a"><label Class="label_form">Тийм лидер</label></th>
                    <th Class="a"><label Class="label_form">Пол</label></th>
                    <th Class="a"><label Class="label_form">Тип оператор</label></th>
                    </tr>
				
				
					<tr>
						<td Class="a">
						<select name="table_operators_name" class="input_small">
							<option value="Да">Да</option>
							<option value="Не">Не</option>
						</select>	
						</td>
						
						<td Class="a">
						<select name="table_team_leader" class="input_small">
							<option value="Да">Да</option>
							<option value="Не">Не</option>
						</select>	
						</td>
						
						<td Class="a">
						<select name="table_gender" class="input_small">	
						    <option value="Не">Не</option>
							<option value="Да">Да</option>
							
						</select>
						</td>	
						
						<td Class="a">
						<select name="table_skills" class="input_small">
						    <option value="Не">Не</option>
							<option value="Да">Да</option>	
						</select>
						</td>
				
					</tr>
					
					<tr>
					<th Class="a"><label Class="label_form">Активен да/не</label></th>
					<th Class="a"><label Class="label_form">Майчинство да/не</label></th>
					<th Class="a"><label Class="label_form">Телефон</label></th>
					<th Class="a"><label Class="label_form">Часове отсъствия</label></th>
					</tr>
					
					<tr>
					<td Class="a">
					<select name="table_isActive" class="input_small">
					        <option value="Не">Не</option>
							<option value="Да">Да</option>			
						</select>
					</td>
					
					<td Class="a">
					<select name="table_isMotherhood" class="input_small">		
					        <option value="Не">Не</option>
							<option value="Да">Да</option>		
						</select>
					</td>
					
					<td Class="a">
					<select name="table_phone" class="input_small">
					        <option value="Не">Не</option>
							<option value="Да">Да</option>	
						</select>
					</td>
					
					<td Class="a">
					<select name="table_absence_hours" class="input_small">
							<option value="Да">Да</option>
							<option value="Не">Не</option>
						</select>
					</td>
					
					</tr>

					
					<tr>
					<th Class="a"><label Class="label_form">Номер престилка</label></th>
					<th Class="a"><label Class="label_form">Номер грейка</label></th>
					<th Class="a"><label Class="label_form">Номер чехли</label></th>
					<th Class="a"><label Class="label_form">Номер гардеробче</label></th>
					</tr>

                     <tr>
					<td Class="a">
					<select name="table_number_appron" class="input_small">
					        <option value="Не">Не</option>
							<option value="Да">Да</option>			
						</select>
					</td>
					
					<td Class="a">
					<select name="table_number_heater" class="input_small">		
					        <option value="Не">Не</option>
							<option value="Да">Да</option>		
						</select>
					</td>
					
					<td Class="a">
					<select name="table_number_slippers" class="input_small">
					        <option value="Не">Не</option>
							<option value="Да">Да</option>	
						</select>
					</td>
					
					<td Class="a">
					<select name="table_absence_number_wardrobe" class="input_small">
							<option value="Не">Не</option>
							<option value="Да">Да</option>
						</select>
					</td>
					
					</tr>

				</table>

				</form>


			</div>

		</div>

		<div id="div_main_container">



			<div Class="table">

				<form method="post" action="printers_production_servlet_display_operators_info">
					<input type="submit" value="Изтегли в ексел" Class="button">

				</form>
				<p>
				<table class="a">

                 <thead Class="thead">
					${table_head }
                </thead>
                
					<tbody Class="tbody">

						<tr>${operators_info_table_body }

						</tr>

					</tbody>

				</table>
			</div>
		</div>

	</div>
</body>
</html>