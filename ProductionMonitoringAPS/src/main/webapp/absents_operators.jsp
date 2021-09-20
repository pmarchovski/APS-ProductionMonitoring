<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container">

<div Class="div_left_container">

<form method="post" action="include_absence">

<label Class="label_form">Избери оператор</label>
<select name="abcense_operators_operator_name" Class="input">

        <option value=""></option>
    <c:forEach items="${operators_name_collection}" var="operatorsNameCollection"> 
        <option value="${operatorsNameCollection}">${operatorsNameCollection}</option>      
    </c:forEach>
    
</select>

<label Class="label_form">Отсъствие - часове</label>

<input type="date"
       name="abcense_operators_date_absence"
       class="input"
       placeholder="Дата">

<input type="text"
       name="abcense_operators_hours_absence"
       class="input"
       placeholder="въведи часовете отсъствие">

<p>

<input type="number"
       name="abcense_operators_holiday_from_hours"
       class="input"
       placeholder="брой дни отпуска от часове">

<p>
   
<input type="submit"
       value="Въведи"
       Class="button">
       
       <p>
       ${massage_edit}

</form>
</div>

<div Class="div_left_container"></div>

</div>


</div>

</body>
</html>