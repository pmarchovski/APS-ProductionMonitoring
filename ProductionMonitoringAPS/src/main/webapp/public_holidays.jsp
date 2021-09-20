<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container">


<form method="post" action="public_holiday">

<label Class="label_form">Избери дата</label>

<p>
${public_holidays_access_massage }
${public_holidays_error_massage }
<p>

<input type="date"
       name="public_holidays_date"
       class="input">
       
<input type="submit"
       class="button"
       value="Въведи">

</form>

</div>

</body>
</html>