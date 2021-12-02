<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<body>

<br><br> 
	<div id="registration">

    <form id="display_and_update_user_into_db_form" method="get" action="admin_update_servlet_registration_info" enctype="multipart/form-data"></form>
    <form id="display_and_update_user_upload_avatar_form" method="post" action="admin_servlet_registration_upload_avatar" enctype="multipart/form-data"></form>

		<p>
			<h3>${avatar } ${user_name_avatar } </h3>
			
			
			<h4>${change_user_data_massage } ${change_password_message }</h4>

            <p>
			<label id="labels">Трите имена</label>
		    <p>
			<input name="display_and_update_user_full_name" class="input" type="text"
				   placeholder="${user_full_name }" form="display_and_update_user_into_db_form"> 
				<br><br> 
				
			<p>
			<label id="labels">Потребителско име</label>
		    <p>
			<input name="display_and_update_user_name"
				class="input" type="text" placeholder="${user_name_avatar }" form="display_and_update_user_into_db_form"> 
				<br><br> 
				
			<p>
			<label id="labels">e-mail</label>
		    <p>	
			<input
				name="display_and_update_user_email" class="input" type="email" placeholder="${user_mail }" form="display_and_update_user_into_db_form">
                 <br><br> 
                 
            <p>
			<label id="labels">Нова парола</label>
		    <p>     
			<input name="display_and_update_user_password" class="input" type="password"
				placeholder="" form="display_and_update_user_into_db_form"> 
				<br><br> 	
			
			 <p>
			<label id="labels">Повтори паролата</label>
		    <p>     
			<input name="display_and_update_user_repeat_password" class="input" type="password"
				placeholder="" form="display_and_update_user_into_db_form"> 
				<br><br> 	
				
				

			<input type="submit"
				value="Запис" class="button" form="display_and_update_user_into_db_form">
			
			
			<br><br> 
			
			<p>
			<label id="labels">Добави или редактирай снимка</label>
		<p>
			<input
			     name="display_and_update_user_upload_avatar"
			     type="file"
			     class="button" 
			     form="display_and_update_user_upload_avatar_form"
			     value="">	
					

          <br><br>
  
			 <input type="submit" class="button" value="Качи снимка" form="display_and_update_user_upload_avatar_form">  
		  <br><br>	   
	 ${massage_one }

	</div>

</body>