<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container_fixed">

                <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/Absence.png"></td>
			         <td Class="a"><h2>Въвеждане на отсъствия по часове</h2></td>   
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
			                    border: 0px solid black;"> <a href="include_operators.jsp">Добавяне на служител</a></td>
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
			                    border: 0px solid black;"> <a href="edit_operators.jsp">Редактиране на  служител</a></td>
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

<form method="post" action="printers_production_servlet_include_absence">

<label Class="label_form">Избери оператор</label>
<select name="abcense_operators_operator_name" Class="input">

        <option value=""></option>
    <c:forEach items="${operators_name_collection}" var="operatorsNameCollection"> 
        <option value="${operatorsNameCollection}">${operatorsNameCollection}</option>      
    </c:forEach>
    
</select>

<br><br>

<label Class="label_form">Отсъствие - часове</label>

<input type="date"
       name="abcense_operators_date_absence"
       class="input"
       placeholder="Дата">

<br><br>

<input type="text"
       name="abcense_operators_hours_absence"
       class="input"
       placeholder="въведи часовете отсъствие">

<br><br>

<input type="number"
       name="abcense_operators_holiday_from_hours"
       class="input"
       placeholder="брой дни отпуска от часове">

<br><br>
   
<input type="submit"
       value="Запис"
       Class="button">
       
       <p>
       ${massage_edit}

</form>
</div>

<div Class="div_left_container">

<h3>Последно въведена информация</h3>

<table Class="a">
${absence_operators_table_head }
${absence_operators_table_body }

</table>
</div>
</div>

</body>
</html>