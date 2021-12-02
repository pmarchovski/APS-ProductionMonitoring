<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="header.jsp" />


<body>



<div id="div_main_container_fixed">


   <table Class="table">
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
      <td Class="a"><h2>Информация за служители</h2></td>   
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
			                    border-right: 1px solid #c0c0c0;"><img src="pictures/ICONS/png/User group.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="edit_operators.jsp">Редактиране на служител</a></td>
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

	<div id="div_main_overflow">

		<div id="div_main_container">

			<div Class="div_right_container">

<form id="operators_info_form" method="get" action="printers_production_servlet_display_operators_info"></form>
<form id="operators_info_form_post" method="post" action="printers_production_servlet_display_operators_info"></form>

					<label Class="label_form">Служител</label> 
					
					<select
						name="display_operator_info_operator_name" 
						Class="input" 
						form="operators_info_form">
						<option value="Всички">Всички</option>
						
						<c:forEach items="${operators_name_collection}"
							var="operatorsNameCollection">

							<option value="${operatorsNameCollection}">${operatorsNameCollection}</option>
						</c:forEach>

					</select> 
					
					<br><br>
					
					<label Class="label_form">Тийм лидер</label> 
					
					<select
						name="display_operator_info_team_leader_name" 
						Class="input"
						form="operators_info_form">
						<option value="Всички">Всички</option>
						
						<c:forEach items="${team_leaders_List }" var="teamLeadersList">
							<option value="${teamLeadersList }">${teamLeadersList }</option>

						</c:forEach>

					</select> 
					
					<br><br>
					<label Class="label_form">Тип служител</label>
					<table>
						<tr>
							<td Class="a">
							   <input 
							    type="checkbox"
							    form="operators_info_form"
								name="display_operator_info_skills"
								value="Оператор механичен монтаж" />Оператор механичен монтаж</td>

							<td Class="a">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="display_operator_info_skills" 
								value="Оператор тест" />Оператор тест</td>

							<td Class="a">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="display_operator_info_skills" 
								value="Оператор ел. монтаж" />Оператор ел. монтаж</td>

							<td Class="a">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="display_operator_info_skills"
								value="Оператор опаковка" />Операторопаковка</td>
						</tr>
					</table>

					<br><br>

						<label Class="label_form">Активен да/не</label> 
						<select
							name="display_operator_info_isActive" 
							class="input_less"
							form="operators_info_form">
							<option value="Да">Да</option>	
							<option value="Не">Не</option>
							<option value="Всички">Всички</option>
						</select> 
						
						<br><br>
						<label Class="label_form">Майчинство да/не</label> 
						<select
							name="display_operator_info_isMotherhood" 
							class="input_less"
							form="operators_info_form">	
							<option value="Не">Не</option>
							<option value="Да">Да</option>
							<option value="Всички">Всички</option>
						</select>
					<br><br>

						<input 
						type="submit" 
						value="Покажи" 
						Class="button"
						form="operators_info_form">
						
						
						<input 
				    	type="submit" 
				    	value="Изтегли в ексел" 
				    	Class="button"
					    form="operators_info_form_post">
			</div>

			<div Class="div_right_container">

				
				<h4>Конфигуриране на таблицата</h4>
				<table Class="table">

						<tr>
							<td Class="e">
							   <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="full_name" checked/>Трите имена</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="team_leader" checked/>Тийм лидер</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="jender" />Пол</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="operator_type" />Тип служител</td>
						</tr>


                        <tr>
							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="is_active" />Активен да/не</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="is_motherhood" />Майчинство да/не</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="phone" />Телефон</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="absence_hours" />Часове отсъствия</td>
						</tr>


					     <tr>
							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="number_apron" />Номер престилка</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="number_heater" />Номер грейка</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="number_slippers" />Номер чехли</td>

							<td Class="e">
							    <input 
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="number_wardrob" />Номер гардеробче</td>
					
						</tr>	
						
						<tr>
						
						    <td Class="e">
							    <input 
							    onClick="toggle(this)"
							    type="checkbox"
							    form="operators_info_form"
								name="operators_info_config_table" 
								value="check_all" />Избери всички</td>
						
						</tr>
                      </table>
			</div>
		</div>

		<div id="div_main_container">
			<div Class="table">
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
	
	<script lang="JavaScript">
	function toggle(source) {
		  checkboxes = document.getElementsByName('operators_info_config_table');
		  for(var i=0, n=checkboxes.length;i<n;i++) {
		    checkboxes[i].checked = source.checked;
		  }
		}
</script>
	
	
</body>
</html>