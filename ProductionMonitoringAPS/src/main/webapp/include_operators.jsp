<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container_two">
<h2>Въвеждане на нови служители</h2>

</div>

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