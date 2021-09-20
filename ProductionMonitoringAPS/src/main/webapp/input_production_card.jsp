<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container">

<div Class="div_left_container">


${done_status }
<p>

<form method="post" action="input_production_card">

<label Class="label_form">Дата</label>
<input type="date"
       name="input_production_card_date"
       class="input_less"><br><br>

<label Class="label_form">Поръчка</label>       
<input type="number"
       name="input_production_card_production_order"
       placeholder="Номер на поръчка"
       min="100000"
       max="999999"
       class="input"><br><br>

<label Class="label_form">Оператор</label>
<select name="input_production_card_operators_name" Class="input">

        <option value=""></option>
    <c:forEach items="${operators_name_collection}" var="operatorsNameCollection"> 
        <option value="${operatorsNameCollection}">${operatorsNameCollection}</option>      
    </c:forEach>
    
</select><br><br>

<label Class="label_form">Операция</label>
<select name="input_production_card_operation" Class="input">

        <option value=""></option>
    <c:forEach items="${operations_name_collection}" var="operationsCollection"> 
        <option value="${operationsCollection}">${operationsCollection}</option>      
    </c:forEach>
    
</select><br><br>

<label Class="label_form">Количество</label>
<input type="number"
       name="input_production_card_quantity"
       placeholder="Количество"
       min="0"
       class="input"
       required="true"><br><br>
 
 <label Class="label_form">Време</label>      
<input type="number"
       name="input_production_card_time"
       placeholder="Време"
       min="0"
       max="450"
       class="input"
       required="true"><br><br>

<label Class="label_form">Нов оператор да/не</label>
<select name="input_production_card_isNewOperator"
        class="input_less">
        
<option value=""></option>
<option value="Не">Не</option>
<option value="Да">Да</option>
</select><br><br>


<input type="submit"
       value="Въведи"
       class="button">

</form>

</div>

<div Class="div_left_container"></div>

</div>


</body>
</html>