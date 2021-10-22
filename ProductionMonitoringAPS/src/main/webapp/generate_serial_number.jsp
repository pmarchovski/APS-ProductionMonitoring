<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

<body>



	<div style="float: left">

     
			<form method="get"
				action="printers_production_servlet_generate_serial_number">
				
				<input type="text"
				       Class="input_less"
				       placeholder="поръчка"
				       required="true"
				       name="printers_production_generate_serial_number_order">

				<input type="submit" class="button" value="Генерирай">

			</form>

			

	</div>
	
</body>