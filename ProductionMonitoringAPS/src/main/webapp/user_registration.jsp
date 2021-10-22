<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<body>

	<div id="registration">

		<p>
			<label id="labels">Форма за регистрация на нови потребители</label>
		<p>

		<form method="get" action="admin_servlet_registration">

			<input name="user_registration_full_name" class="input" type="text"
				   placeholder="Трите имена"> 
				<br><br> 
			<input name="user_registration_user_name"
				class="input" type="text" placeholder="User name"> 
				<br><br> 
			<input
				name="user_registration_email" class="input" type="email" placeholder="E-mail">
                 <br><br> 
			<input name="user_registration_password" class="input" type="password"
				placeholder="Парола"> 
				<br><br> 
			<select name="user_registration_user_type" class="input">
							<option value=""></option>
							<option value="user">user</option>
							<option value="guest">guest</option>
						</select>	
			<br><br> 	
			<input type="submit"
				value="Registration" class="button">
            ${massage_one }
		</form>

	</div>

</body>