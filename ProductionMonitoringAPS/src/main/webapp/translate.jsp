<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<body>
<br><br>
<div class="div_main_container">
	<form action="admin_servlet_translator"
	      method="GET">
	      
	<input type="text"
	       class="input"
	       name="translator_input_text">  
	           
	<input type="submit"
	       class="button"
	       value="Translate">
	
	</form>
</div>

<div class="div_main_container">

<h3>${translated_text }</h3>

</div>

</body>