<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container_fixed">


                <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Добавяне на нов служител</h2></td>   
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
<form method="post" action="printers_production_servlet_operators_data_servlet" accept-charset="UTF-8">

<label Class="label_form">Трите имена</label>
<input type="text"
       name="include_operators_operator_name"
       class="input"
       placeholder="Трите имена"
       required="true">
<br><br>      
      

<label Class="label_form">Тийм лидер</label>

<select name="include_operators_team_leader" Class="input" required="true">
       <option value=""></option>
       <c:forEach items="${team_leaders_List}" var="teamLeadersList">
    
        <option value="${teamLeadersList}">${teamLeadersList}</option>
        
    </c:forEach>

</select>
<br><br>


<label Class="label_form">Пол</label>
<select name="include_operators_gender"
        class="input_less"
        required="true">
        
<option value="Жена">Жена</option>        
<option value="Мъж">Мъж</option>
</select> 

<br><br>

 <label Class="label_form">Тип оператор</label>   
 <table>
            <tr >
               <td Class="a"><input type="checkbox" name="include_operators_skills" value="Оператор механичен монтаж"/>Оператор механичен монтаж</td>
          
               <td Class="a"><input type="checkbox" name="include_operators_skills" value="Оператор ел. монтаж"/>Оператор ел. монтаж</td>
            
               <td Class="a"><input type="checkbox" name="include_operators_skills" value="Оператор тест"/>Оператор тест</td>
           
               <td Class="a"><input type="checkbox" name="include_operators_skills" value="Оператор опаковка"/>Оператор опаковка</td>
            </tr>
 </table>      

<p>
        
<br><br>


<label Class="label_form">Телефон</label>
<input type="text"
       name="include_operators_phone"
       class="input_less"
       placeholder="Телефон"
       required="true">



<table>

<td Class="e">
<label Class="label_form">Номер престилка</label>
<input type="number"
       name="include_operators_apron"
       class="input"
       placeholder="номер на престилка"
       max = "68"
       min = "38"
       required="true">
 </td>     
    
<td Class="e">
<label Class="label_form">Номер на грейка</label>

<select
name="include_operators_heater"
class="input"
required="true">

<option value=""></option>  
<option value="S">S</option>        
<option value="M">M</option>
<option value="L">L</option>
<option value="XL">XL</option>
<option value="XXL">XXL</option>
<option value="XXXL">XXXL</option>
<option value="XXXXL">XXXXL</option>
</select>
       
 </td>      
 
 <td Class="e">
<label Class="label_form">Номер на чехли</label>
<input type="number"
       name="include_operators_slippers"
       class="input"
       placeholder="номер на чехли"
       max = "48"
       min = "36"
       required="true">
 </td>       
 
 
  <td Class="e">
  <label Class="label_form">Номер на гардеробче</label>
 <select name="include_operators_wardrobe" Class="input" required="true">
       <option value=""></option>
       <c:forEach items="${free_wardrobe_collection}" var="freeWardrobeCollection">
    
        <option value="${freeWardrobeCollection}">${freeWardrobeCollection}</option>
        
    </c:forEach>

</select>
  </td> 
 
 
</table>
<p>
 
 <br><br>  
<input type="submit"
       value="Въведи"
       Class="button">
       
       <p>
       ${include_operators_inform_massage }

</form>
</div>

<div Class="div_left_container"></div>


</div>


</body>
</html>