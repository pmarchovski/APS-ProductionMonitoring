<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container_fixed">


                <table>
			      <tr>
			         
			         <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 20px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:30px; 
			                    border: 0px solid black;"><img src="pictures/ICONS/png/Create.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="create_new_task.jsp">Нова задача</a></td>
			                    </table>
			         </td>
			         
			          <td style="width:300px; 
			                    border: 1px solid #c0c0c0; 
			                    border-radius: 10px; 
			                    font-size: 20px">
			                    
			                    <table>
			                    <tr>
			                    <td style="width:30px; 
			                    border: 0px solid black;"><img src="pictures/ICONS/png/user_info_32.png"></td>
			                    <td style="width:200px; 
			                    border: 0px solid black;"> <a href="admin_servlet_display_my_tasks">Моите задачи</a></td>
			                    </table>
			          </td>
                     
 
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

<div style="float: left">
 
   <form id="display_task_form" method="get" action="admin_servlet_display_tasks" accept-charset="UTF-8"></form>
   <form id="add_comment_form" method="get" action="admin_servlet_add_comment" accept-charset="UTF-8"></form>
   <form id="change_status_form" method="get" action="admin_servlet_change_status" accept-charset="UTF-8" name="status_change_form"></form>
   <form id="change_target_task_date_form" method="get" action="admin_servlet_change_task_target_date" accept-charset="UTF-8" name="change_target_date"></form>



     <table>

         <tr>
           <td Class="y">
             <label Class="label_form">Покажи задачите по отговорник</label>   
           </td>
           
           <td Class="y">
             <label Class="label_form">Покажи задачите по месец</label>
           </td>
           
           <td Class="y">
             <label Class="label_form">Покажи задачите по статус</label>
           </td>
           
         </tr>
         <tr>
           <td Class="y">
             <select name="display_tasks_users" 
                     Class="input"
                     form="display_task_form">
       
                     <option value="all">Всички</option>
                     <c:forEach items="${users_name_collection}" var="usersCollection">
    
                    <option value="${usersCollection}">${usersCollection}</option>
        
                    </c:forEach>

             </select>   
           </td>
           
           <td Class="y">
             
             <input type="month"
                    name="display_tasks_by_month"
                    Class="input"
                    form="display_task_form">  
           </td>
           
           <td Class="y">
             <select name="display_tasks_status" 
                     Class="input"
                     form="display_task_form">
                    <option value="all">Всички</option>
                    <option value="Active">Активни</option>
                    <option value="Closed">Затворени</option>

             </select>   
           </td>
           
           <td Class="y">
    
            <input type="submit"
                   value="Покажи"
                   Class="button"
                   form="display_task_form">
         
         </td>
           
         </tr>
         
     </table>   

     
       
   </div>
     

<div id="div_main_overflow">
    <br><br>
    <table>
    ${display_task_table_head }
     ${display_task_table_body }
     
   </table>

    </div>

</body>
</html>