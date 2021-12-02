<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
	
<jsp:include page="header.jsp"/>


<body>

<div id="div_main_container_two">


                <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2> Задачи</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_main_container">

    <table>
        <tr>
           <th Class="c">
           
           
           <details>
           <summary><a href="create_new_task.jsp">Нова задача</a></summary>
           <p>Тук имате възможността да създадете нова задача</p>
           </details>
           
           </th>
           
           
           
           <th Class="c"><a href="display_tasks.jsp">Покажи задачи</a></th>
           <th Class="c"><a href="update_tasks.jsp">Редактиране на задачи</a></th>
       </tr>
    </table>
</div>  


<div Class="div_left_container">

 
</div>

</body>
</html>