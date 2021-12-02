<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>

<body>

<div id="div_main_container_fixed">



 <table Class="table">
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
      <td Class="a"><h2>Пълна информация за наличните служители</h2></td>   
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
			                    border-right: 1px solid #c0c0c0;"><img src="ictures/ICONS/png/New document.png"></td>
			                    <td style="width:300px; 
			                    border: 0px solid black;"> <a href="monthly_presence_blank.jsp">Форма за присъствие</a></td>
			                    </table>
			          </td>
                     
 
			      </tr>
			    </table>	
   
   

</div>

<div id="div_empty"></div>
<div id="div_main_container">


<form method="get" action="printers_production_servlet_get_hole_operators_info_servlet">

<label Class="label_form">Тийм лидер</label>

<select name="hole_operators_info_team_leader" Class="input">
       <option value="all">Всички</option>
       <c:forEach items="${team_leaders_List}" var="teamLeadersList">
    
        <option value="${teamLeadersList}">${teamLeadersList}</option>
        
    </c:forEach>

</select>
<br><br>
<input type="submit"
       value="Покажи"
       Class="button">

<table>

${hole_operators_info }
<p>


</table>

<table>
${hole_operators_info_none_operator }

</table>


</form>

</div>

</body>
</html>