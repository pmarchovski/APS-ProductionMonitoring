<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>

<body>

<div id="div_main_container_fixed">


                <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2> ${title_task }</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

<div id="explicite_task">

 

   <form id="add_comment_form" method="get" action="admin_servlet_add_comment" accept-charset="UTF-8"></form>
   <form id="change_status_form" method="get" action="admin_servlet_change_status" accept-charset="UTF-8" name="status_change_form"></form>
   <form id="change_target_task_date_form" method="get" action="admin_servlet_change_task_target_date" accept-charset="UTF-8" name="change_target_date"></form>

  
 
    ${display_explicite_task }
    

</div>

</body>
</html>