<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_two">
<h2>Въвеждане на официални почивни дни</h2>

</div>

<div id="div_main_container">


<form method="get" action="printers_production_servlet_include_absence">

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