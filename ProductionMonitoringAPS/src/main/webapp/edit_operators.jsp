<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container">

<div Class="div_left_container">

<form method="post" action="update_operators_info">

<label Class="label_form">Избери оператор</label>
<select name="edit_operators_name" Class="input">

        <option value=""></option>
    <c:forEach items="${operators_name_collection}" var="operatorsNameCollection"> 
        <option value="${operatorsNameCollection}">${operatorsNameCollection}</option>      
    </c:forEach>
    
</select>


<label Class="label_form">Тийм лидер</label>

<select name="edit_operators_team_leader" Class="input">
        <option value=""></option>
       <c:forEach items="${team_leaders_List}" var="teamLeadersList">
    
        <option value="${teamLeadersList}">${teamLeadersList}</option>
        
    </c:forEach>

</select>



<label Class="label_form">Пол</label>
<select name="edit_operators_gender"
        class="input_less">
<option value=""></option>
<option value="Мъж">Мъж</option>
<option value="Жена">Жена</option>
</select> 



 <label Class="label_form">Тип оператор</label>   
 <table>
            <tr >
               <td Class="a"><input type="checkbox" name="edit_operators_skills" value="Оператор механичен монтаж"/>Оператор механичен монтаж</td>
          
               <td Class="a"><input type="checkbox" name="edit_operators_skills" value="Оператор ел. монтаж"/>Оператор ел. монтаж</td>
            
               <td Class="a"><input type="checkbox" name="edit_operators_skills" value="Оператор тест"/>Оператор тест</td>
           
               <td Class="a"><input type="checkbox" name="edit_operators_skills" value="Оператор опаковка"/>Оператор опаковка</td>
            </tr>
 </table>      

<p>
        
<label Class="label_form">Активен да/не</label>
<select name="edit_operators_isActive"
        class="input_less">
        
<option value=""></option>
<option value="Да">Да</option>
<option value="Не">Не</option>
</select>



<label Class="label_form">Майчинство да/не</label>
<select name="edit_operators_isMotherhood"
        class="input_less">
        
<option value=""></option>
<option value="Не">Не</option>        
<option value="Да">Да</option>
</select>


<label Class="label_form">Телефон</label>
<input type="text"
       name="edit_operators_phone"
       class="input_less"
       placeholder="Телефон">

<p>
   
<input type="submit"
       value="Приложи промените"
       Class="button">
       
       <p>
       ${massage_edit}

</form>
</div>

<div Class="div_right_container">

<form method="get" action="update_operators_info">

<label Class="label_form">Избери оператор</label>
<select name="edit_operators_name_two" Class="input">

        <option value=""></option>
    <c:forEach items="${operators_name_collection}" var="operatorsNameCollection"> 
        <option value="${operatorsNameCollection}">${operatorsNameCollection}</option>      
    </c:forEach>
    
</select>

<input type="submit"
       value="Покажи текущата информация"
       Class="button">

</form>

<p>

<table>

${update_operators_display_info }

</table>

</div>

</div>

<div>

</div>

</body>
</html>