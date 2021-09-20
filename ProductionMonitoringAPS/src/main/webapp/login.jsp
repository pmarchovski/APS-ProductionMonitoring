<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<jsp:include page="header.jsp"/>

<body>

	<div Class="header_line">
	</div>
<main>
<div id="registration">

<form method="post" action="login">

<label>User name</label>  
<input 
       name="login_user_name"
       class="input"
       type="text" 
       placeholder="user name">
       
<label>Password</label>      
<input 
       name="login_user_password"
       class="input"
       type="password" 
       placeholder="Password">
       

<input type="submit" value="Login" class="button">
</form>

<p>
${login_inform_massage}
<a href="user_registration.jsp">Регистрация</a>
</div>
</main>

</body>