<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<jsp:include page="header.jsp" />



<body>

	<div id="div_main_container_fixed">
		
		
		<table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2> Редактиране на информция за служител</h2></td>   
			      </tr>
			    </table>
		
		
	 <table>
			      <tr>
			         
			         <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 18px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:50px; 
			                    border: 0px solid black;
			                    border-right: 1px solid #c0c0c0;"><img src="pictures/ICONS/png/user_add_32.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="include_operators.jsp">Въвеждане на служител</a></td>
			                    </table>
			         </td>
			         
			          <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 18px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:50px; 
			                    border: 0px solid black;
			                    border-right: 1px solid #c0c0c0;"><img src="pictures/ICONS/png/Absence.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="absents_operators.jsp">Въвеждане на отсъствия</a></td>
			                    </table>
			          </td>
			          
			          
			           <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 18px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:50px; 
			                    border: 0px solid black;
			                    border-right: 1px solid #c0c0c0;"><img src="pictures/ICONS/png/user_info_32.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="display_operator_info.jsp">Информация за служител</a></td>
			                    </table>
			          </td>
			          
			            <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 18px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:50px; 
			                    border: 0px solid black;
			                    border-right: 1px solid #c0c0c0;"><img src="pictures/ICONS/png/New document.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="monthly_presence_blank.jsp">Форма за присъствие</a></td>
			                    </table>
			          </td>
			          
			        
			          
			            <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 18px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:50px; 
			                    border: 0px solid black;
			                    border-right: 1px solid #c0c0c0;"><img src="pictures/ICONS/png/User group.png"></td>
			                    <td style="width:300px; 
			                    border: 0px solid black;"> <a href="hole_operators_info.jsp">Пълна информация за служителите</a></td>
			                    </table>
			          </td>
                     
 
			      </tr>
			    </table>	
		

	</div>

<div id="div_empty"></div>
	<div id="div_main_container">

		<div Class="div_left_container">



			<form name="form_select" method="get"
				action="admin_servlet_current_operators_data">


				<label Class="label_form">Избери оператор</label> <select
					name="edit_operators_name" Class="input"
					onchange="document.form_select.submit();">

					<option value="${update_operators_display_info_selected_operator}">${update_operators_display_info_selected_operator}</option>

					<c:forEach items="${operators_name_collection}"
						var="operatorsNameCollection">
						<option value="${operatorsNameCollection}">${operatorsNameCollection}</option>
					</c:forEach>

				</select> <br>
				<br>

			</form>

			<br>
			<br>


			<form method="get"
				action="printers_production_servlet_operators_data_servlet">

				<input type="hidden" name="edit_operators_name"
					value="${update_operators_display_info_selected_operator}">

				<label Class="label_form">Тийм лидер</label> <select
					name="edit_operators_team_leader" Class="input">
					<option value=""></option>
					<c:forEach items="${team_leaders_List}" var="teamLeadersList">

						<option value="${teamLeadersList}">${teamLeadersList}</option>

					</c:forEach>

				</select> <br>
				<br> <label Class="label_form">Пол</label> <select
					name="edit_operators_gender" class="input_less">
					<option value=""></option>
					<option value="Мъж">Мъж</option>
					<option value="Жена">Жена</option>
				</select> <br>
				<br> <label Class="label_form">Тип оператор</label>
				<table>
					<tr>
						<td Class="a"><input type="checkbox"
							name="edit_operators_skills" value="Оператор механичен монтаж" />Оператор
							механичен монтаж</td>

						<td Class="a"><input type="checkbox"
							name="edit_operators_skills" value="Оператор ел. монтаж" />Оператор
							ел. монтаж</td>

						<td Class="a"><input type="checkbox"
							name="edit_operators_skills" value="Оператор тест" />Оператор
							тест</td>

						<td Class="a"><input type="checkbox"
							name="edit_operators_skills" value="Оператор опаковка" />Оператор
							опаковка</td>
					</tr>
				</table>

				<br>
				<br> <label Class="label_form">Активен да/не</label> <select
					name="edit_operators_isActive" class="input_less">

					<option value=""></option>
					<option value="Да">Да</option>
					<option value="Не">Не</option>
				</select> <br>
				<br> <label Class="label_form">Майчинство да/не</label> <select
					name="edit_operators_isMotherhood" class="input_less">

					<option value=""></option>
					<option value="Не">Не</option>
					<option value="Да">Да</option>
				</select> <br>
				<br> <label Class="label_form">Телефон</label> <input
					type="text" name="edit_operators_phone" class="input_less"
					placeholder="Телефон"> <br>
				<br>

				<table>

					<tr>
						<td Class="e"><label Class="label_form">Номер
								престилка</label> <input type="number" name="edit_operators_apron"
							class="input" placeholder="номер на престилка" max="68" min="38">
						</td>

						<td Class="e"><label Class="label_form">Номер на
								грейка</label> <select name="edit_operators_heater" class="input">

								<option value=""></option>
								<option value="S">S</option>
								<option value="M">M</option>
								<option value="L">L</option>
								<option value="XL">XL</option>
								<option value="XXL">XXL</option>
								<option value="XXXL">XXXL</option>
								<option value="XXXXL">XXXXL</option>

						</select></td>

						<td Class="e"><label Class="label_form">Номер на
								чехли</label> <input type="number" name="edit_operators_slippers"
							class="input" placeholder="номер на чехли" max="48" min="36">
						</td>



						<td Class="e"><label Class="label_form">Номер на
								гардеробче</label> <input type="text" name="edit_operators_wardrobe"
							class="input" placeholder="номер на гардеробе"></td>
					</tr>

					<td Class="e"><label Class="label_form">Дата престилка</label>
						<input type="date" name="edit_operators_date_change_appron"
						class="input"></td>


					<td Class="e"><label Class="label_form">Дата грейка</label> <input
						type="date" name="edit_operators_date_change_heater" class="input">
					</td>

					<td Class="e"><label Class="label_form">Дата чехли</label> <input
						type="date" name="edit_operators_date_change_slippers"
						class="input"></td>

				</table>


				<input type="submit" value="Приложи промените" Class="button">

				<p>${massage_edit}
			</form>


		</div>

		<div Class="div_right_container">

			<p>
			<table Class="a">

				<h3>${display_operators_info }</h3>


				${update_operators_display_info }

			</table>

		</div>

	</div>


</body>
</html>