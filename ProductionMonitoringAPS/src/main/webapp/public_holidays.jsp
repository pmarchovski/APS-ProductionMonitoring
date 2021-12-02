<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>



<div id="div_main_container_fixed">

 <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Въвеждане на официални почивни дни</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

<div id="div_main_container">


<form method="get" action="printers_production_servlet_include_absence">



<p>
${public_holidays_access_massage }
${public_holidays_error_massage }
<p>

<table>
<tr>
<td Class="a"><label Class="label_form">Избери дата</label></td>
</tr>

<tr>
<td Class="a">
<input type="date"
       name="public_holidays_date"
       class="input">
</td>
<td Class="a">
<input type="submit"
       class="button"
       value="Въведи">
</td>
</tr>
</table>
</form>

</div>

</body>
</html>