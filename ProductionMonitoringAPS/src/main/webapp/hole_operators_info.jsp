<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>

<body>
<div id="div_main_container">


<form method="get" action="get_hole_operators_info_servlet">

<label Class="label_form">Тийм лидер</label>

<select name="include_operators_team_leader" Class="input">
       <option value=""></option>
       <c:forEach items="${team_leaders_List}" var="teamLeadersList">
    
        <option value="${teamLeadersList}">${teamLeadersList}</option>
        
    </c:forEach>

</select>

<input type="submit"
       value="Покажи"
       Class="button">

<table>

${hole_operators_info }

</table>

</form>

</div>

</body>
</html>