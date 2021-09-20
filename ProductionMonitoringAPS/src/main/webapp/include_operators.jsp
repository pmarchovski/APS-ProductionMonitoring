<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>


<div id="div_main_container">


<div Class="div_left_container">
<form method="post" action="include_operators" accept-charset="UTF-8">

<label Class="label_form">Трите имена</label>
<input type="text"
       name="include_operators_operator_name"
       class="input"
       placeholder="Трите имена">
      
      

<label Class="label_form">Тийм лидер</label>

<select name="include_operators_team_leader" Class="input">
       <option value=""></option>
       <c:forEach items="${team_leaders_List}" var="teamLeadersList">
    
        <option value="${teamLeadersList}">${teamLeadersList}</option>
        
    </c:forEach>

</select>



<label Class="label_form">Пол</label>
<select name="include_operators_gender"
        class="input_less">
<option value="Жена">Жена</option>        
<option value="Мъж">Мъж</option>
</select> 



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
        



<label Class="label_form">Телефон</label>
<input type="text"
       name="include_operators_phone"
       class="input_less"
       placeholder="Телефон">

<p>
   
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