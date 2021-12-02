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
				<th Class="c">Задачи</th>
			</tr>

			<tr>
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Add.png"></td>
			         <td Class="a"><a href="input_production_card.jsp">Производствени фишове</a></td>   
			      </tr>
			    </table>
				</td>
				
						
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/user_add_32.png"></td>
			         <td Class="a"><a href="include_operators.jsp">Добавяне на служител</a></td>   
			      </tr>
			    </table>
				</td>
				
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Info.png"></td>
			         <td Class="a"><a href="production_capacity.jsp">Производстен капацитет</a></td>   
			      </tr>
			    </table>
				</td>
				
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Calendar.png"></td>
			         <td Class="a"><a href="public_holidays.jsp">Въвеждане на празнични дни</a></td>   
			      </tr>
			    </table>
				</td>
				
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Report.png"></td>
			         <td Class="a"><a href="display_reported_production_time.jsp">Отчетено време от фишове</a></td>   
			      </tr>
			    </table>
				</td>
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Report.png"></td>
			         <td Class="a"><a href="admin_servlet_display_tasks">Задачи</a></td>   
			      </tr>
			    </table>
				</td>

			</tr>

			<tr>
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/test.png"></td>
			         <td Class="a"><a href="input_test_card.jsp">Фишове от тест</a></td>   
			      </tr>
			    </table>
				</td>
			
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Absence.png"></td>
			         <td Class="a"><a href="absents_operators.jsp">Въвеждане на отсъствия</a></td>   
			      </tr>
			    </table>
				</td>
			
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Pinion.png"></td>
			         <td Class="a"><a href="production_plan_generate.jsp">Генериране на производствени планове</a></td>   
			      </tr>
			    </table>
				</td>
				
				 <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/How-to.png"></td>
			         <td Class="a"><a href="skills_matrix.jsp">Матрица на обученията</a></td>   
			      </tr>
			    </table>
				</td>


                 <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Report.png"></td>
			         <td Class="a"><a href="absence_report.jsp">Справка отсъствия от часове</a></td>   
			      </tr>
			    </table>
				</td>
				
				 <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Report.png"></td>
			         <td Class="a"><a href="production_orders_dashboard.jsp">Production Dashboards</a></td>   
			      </tr>
			    </table>
				</td>
				
				
			</tr>
			
			<tr>
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Target.png"></td>
			         <td Class="a"><a href="">Информация от въведени фишове</a></td>   
			      </tr>
			    </table>
				</td>
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><a href="edit_operators.jsp">Редактиране на информация за служител</a></td>   
			      </tr>
			    </table>
				</td>
			
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Report.png"></td>
			         <td Class="a"><a href="orders_info.jsp">Информация за поръчки по години</a></td>   
			      </tr>
			    </table>
				</td>
				
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Report.png"></td>
			         <td Class="a"><a href="display_materials_type.jsp">Справка материали по типове</a></td>   
			      </tr>
			    </table>
				</td>


                <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Dollar.png"></td>
			         <td Class="a"><a href="planed_labour_cost.jsp">Планирани разходи за производство</a></td>   
			      </tr>
			    </table>
				</td>
			
				
			</tr>
			
			<tr>
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Target.png"></td>
			         <td Class="a"><a href="">Редактиране на операции</a></td>   
			      </tr>
			    </table>
				</td>
			
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/user_info_32.png"></td>
			         <td Class="a"><a href="display_operator_info.jsp">Информация за служител</a></td>   
			      </tr>
			    </table>
				</td>
			
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"> </td>
			         <td Class="a"><a href=" "> </a></td>   
			      </tr>
			    </table>
				</td>
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/wardrobe_25x25.png"></td>
			         <td Class="a"><a href="wardrob_info.jsp">Гардеробчета</a></td>   
			      </tr>
			    </table>
				</td>
			
				
				<td Class="c"> </td>
			</tr>
			
			<tr>
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"> </td>
			         <td Class="a"><a href=" "> </a></td>   
			      </tr>
			    </table>
				</td>
			
			
			    <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/New document.png"></td>
			         <td Class="a"><a href="monthly_presence_blank.jsp">Генериране на месечна форма за присъствие</a></td>   
			      </tr>
			    </table>
				</td>
	
	            <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"> </td>
			         <td Class="a"><a href=" "> </a></td>   
			      </tr>
			    </table>
				</td>
	
	
	            <td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Component.png"></td>
			         <td Class="a"><a href="generate_serial_number.jsp">Генериране на серийни номера по поръчка</a></td>   
			      </tr>
			    </table>
				</td>
	
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"> </td>
			         <td Class="a"><a href=" "> </a></td>   
			      </tr>
			    </table>
				</td>
			</tr>
			
			<tr>
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"> </td>
			         <td Class="a"><a href=" "> </a></td>   
			      </tr>
			    </table>
				</td>
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><a href="hole_operators_info.jsp">Пълна информация за служителите</a></td>   
			      </tr>
			    </table>
				</td>

				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"> </td>
			         <td Class="a"><a href=" "> </a></td>   
			      </tr>
			    </table>
				</td>
				
				<td Class="a">		
			    <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"> </td>
			         <td Class="a"><a href="work_clothes.jsp">Работно облекло </a></td>   
			      </tr>
			    </table>
				</td>
			</tr>


		</table>


	</div>

</body>
</html>