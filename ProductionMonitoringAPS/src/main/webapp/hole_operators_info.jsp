<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>

<body>

<div id="div_main_container_two">
<h2>Пълна информация за наличните служители в производството</h2>

</div>

<div id="div_main_container">


<form method="get" action="printers_production_servlet_get_hole_operators_info_servlet">

<label Class="label_form">Тийм лидер</label>

<select name="hole_operators_info_team_leader" Class="input">
       <option value="all">Всички</option>
       <c:forEach items="${team_leaders_List}" var="teamLeadersList">
    
        <option value="${teamLeadersList}">${teamLeadersList}</option>
        
    </c:forEach>

</select>
<br><br>
<input type="submit"
       value="Покажи"
       Class="button">

<table>

${hole_operators_info }
<p>


</table>

<table>
${hole_operators_info_none_operator }

</table>


</form>

</div>

</body>
</html>