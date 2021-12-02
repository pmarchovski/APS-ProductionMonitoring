<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<body>

	<div>
	            <br><br>
	            <br><br>
	
	<fieldset id="registration">
		<legend style="font-size: 20px"><b>Регистрация на нов потребител:</b></legend>

    <form id="save_data_into_db_form" method="get" action="admin_servlet_registration" enctype="multipart/form-data"></form>

		
			<input name="user_registration_full_name" 
			       class="input" 
			       type="text"
				   placeholder="Трите имена" 
				   form="save_data_into_db_form"> 
				<br><br> 
			<input name="user_registration_user_name"
				   class="input" 
				   type="text" 
				   placeholder="User name" 
				   form="save_data_into_db_form"> 
				<br><br> 
			<input
				   name="user_registration_email" 
				   class="input" 
				   type="email" 
				   placeholder="E-mail" 
				   form="save_data_into_db_form">
                 <br><br> 
			<input name="user_registration_password" 
			       class="input" 
			       type="password"
				   placeholder="Парола" 
				   form="save_data_into_db_form"> 
				<br><br> 
				
			<p>
			<label id="labels">Тип потребител</label>
		<p>	
			<select name="user_registration_user_type" 
			        class="input" 
			        form="save_data_into_db_form">
							<option value=""></option>
							<option value="user">user</option>
							<option value="guest">guest</option>
						</select>	
				<br><br> 		

			<input type="submit"
				   value="Registration" 
				   class="button" 
				   form="save_data_into_db_form">
         </fieldset>
          <br><br>
  
	 ${massage_one }

	</div>

</body>