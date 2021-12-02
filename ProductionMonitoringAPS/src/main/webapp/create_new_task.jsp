<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container_two">


                <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2> Създаване на нова задача</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_main_container">

    <table>
        <tr>
           <th Class="c"><a href="create_new_task.jsp">Създаване на задача</a></th>
           <th Class="c"><a href="display_tasks.jsp">Покажи задачи</a></th>
           <th Class="c"><a href="update_tasks.jsp">Редактиране на задачи</a></th>
       </tr>
    </table>
</div>  


<div id="div_main_container">

<div Class="div_left_container">
<form id="create_new_task_form" method="get" action="admin_servlet_create_new_task" accept-charset="UTF-8"></form>


<label Class="label_form">Краен срок за изпълнение</label>
<input type="date"
       name="task_target_date"
       class="input_less"
       form="create_new_task_form"
       required="true">
       
       <br><br>


<label Class="label_form">Кратко описание</label>
<input type="text"
       name="task_title"
       class="input_less"
       placeholder="Име на задача"
       required="true"
       form="create_new_task_form">
<br><br>      


<label Class="label_form">Пълно описание</label>

<textarea rows="20" 
          cols="60" 
          name="task_description"
          class="input_big"
          required="true"
          form="create_new_task_form"></textarea>
          <br>

<br><br> 
 
 
 <label Class="label_form">Отговорник</label>

<select name="task_owner" 
        Class="input_less" 
        required="true"
        form="create_new_task_form">
       <option value=""></option>
       
       <c:forEach items="${users_name_collection}" var="usersCollection">
    
        <option value="${usersCollection}">${usersCollection}</option>
        
    </c:forEach>

</select> 

<br><br> 

 <label Class="label_form">Екип<label>
<br>
<select name="task_members[]" 
        Class="input_multiple" 
        required="true"
        form="create_new_task_form"
        multiple="multiple">
       
       <c:forEach items="${users_name_collection}" var="usersCollection">
    
        <option value="${usersCollection}">${usersCollection}</option>
        
    </c:forEach>

</select>   

    

<br><br>
<input type="submit"
       value="Запис"
       Class="button"
       form="create_new_task_form">
       
</div>



</body>
</html>