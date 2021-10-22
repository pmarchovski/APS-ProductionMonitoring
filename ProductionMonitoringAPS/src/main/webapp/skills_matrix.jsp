<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

	<div id="div_main_container_two">
		<h2>Матрица на обученията</h2>

	</div>

	<div id="div_main_overflow">

		<div style="float: left">
			<form method="get" action="printers_production_servlet_skills_matrix">
			
				<table>

                  <td Class="y"><input type="submit" Class="button" value="Генерирай"></td>
                 

				</table>

			</form>

		</div>
		
		<div style="float: left">
			<form method="post" action="printers_production_servlet_skills_matrix">
			
				<table>

                
                  <td Class="y"><input type="submit" Class="button" value="Изтегли"></td>

				</table>

			</form>

		</div>
		
		
<table Class="b">


   <thead Class="thead"> ${skills_matrix_table_head }
   </thead>


    <tbody Class="tbody">${skills_matrix_table_data }
    </tbody>

</table>
	</div>
</body>
</html>