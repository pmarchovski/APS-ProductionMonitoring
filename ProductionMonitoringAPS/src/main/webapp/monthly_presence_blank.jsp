<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="header.jsp" />
<body>

	<div id="div_main_overflow">

<div id="div_main_container">
<div Class="div_right_container">

		<form method="post" action="presents_blank">
<p>
            <label id="label_error"> ${monthly_presence_blank_empty_date_error_massage }</label>
<p>
			<input 
			       type="date" 
			       name="monthly_presence_blank_start_date"
				   Class="input_small" 
				   placeholder="Начална дата"> 
		    <input
				   type="date" 
				   name="monthly_presence_blank_end_date"
			       Class="input_small" 
				   placeholder="Крайна дата"> 
				
				${empty_date_fields_massage }
				
				<label Class="label_form">Тийм лидер</label> 
				
				<select
				   name="monthly_presence_blank_team_leader_name" 
				   Class="input_less">
				
				<option value="Всички">Всички</option>
				
				<c:forEach items="${team_leaders_List }" var="teamLeadersList">
					<option value="${teamLeadersList }">${teamLeadersList }</option>

				</c:forEach>
				</select>
         <div>
			<input 
			       type="submit" 
				   Class="button" 
				   value="Генерирай">
		</form>
		
		<p>
		<form method="get" action="presents_blank">
		
		    <input
		           type="submit"
		           Class="button"
		           value="Изтегли в ексел">
		
		</form>
          </div>
<p>

		<table Class= "a">
          <tr>
        
             <tr>${monthly_present_blank_table_head_date }</tr>
			
			<tr>${monthly_present_blank_table_head_week_day }</tr>
			
			<tr>${monthly_present_blank_table_body_data }</tr>
          </tr>
          
		</table>

</div>

<div Class="div_right_container"></div>
</div>
	</div>

</body>
</html>