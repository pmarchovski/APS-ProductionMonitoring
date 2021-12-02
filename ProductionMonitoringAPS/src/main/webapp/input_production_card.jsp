<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_fixed">

 <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Въвеждане на производствени фишове</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

<div id="div_main_container">

<div Class="div_left_container">


${done_status }
<p>

<form method="post" action="printers_production_servlet_input_production_card">

<label Class="label_form">Дата</label>
<input type="date"
       name="input_production_card_date"
       value="${input_production_card_current_date }"
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

<div Class="div_right_container">

<h4>Информация от последно въведен фиш</h4>

<table Class="a">
${input_production_card_table_head }
${input_production_card_table_body }

</table>

</div>
</div>


</body>
</html>