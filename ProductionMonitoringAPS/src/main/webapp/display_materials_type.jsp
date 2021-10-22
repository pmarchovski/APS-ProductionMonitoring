<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

<body>


    <div style="float: left">
    <form method="get"
				action="printers_production_servlet_display_material_without_type">

				<input type="submit" class="button" value="Display">

			</form>
    
    </div>
    
<br><br>
<br><br>
	<div style="float: left">
			<form method="get"
				action="printers_production_servlet_update_material_without_type">

				<input type="submit" class="button" value="Update"> 
		
				<br><br>

				<div id="div_main_overflow">

					<table>
						<thead Class="thead">${materials_without_type_table_head }
						</thead>

						<tbody Class="tbody">${materials_without_type_table_body }
						</tbody>

					</table>
				</div>
				
			</form>

	</div>
	
</body>