<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<jsp:include page="header.jsp" />



<body>

	<div id="loader" class="center" style="display: none">
		<img src="pictures/spinner.gif">
	</div>



	<div Class="header_line"></div>
	<main>
	
	<br><br><br><br><br><br><br><br>
		<div id="registration">

			<form id="login_form" method="get" action="admin_servlet_forgot_password"
				id="login_form">
				
			   <table Class="table">
			    <tr>
			    <td Class="i"></td>
			    </tr>
			    
			    </table>
			
				<div class="input-container">
				
				<i class="fa fa-envelope icon"></i>
				<input name="forgot_password_email" class="input-field" type="email"
							placeholder="e-mail">
				</div>	
				<br><br>	
			    
			    
			
		    	<div class="input-container">
				<i class="fa fa-key icon"></i>
		        <input name="forgot_password_new_password" class="input-field"
							type="password" placeholder="Нова парола">
				</div>
				<br><br>
				
				<div class="input-container">
				<i class="fa fa-key icon"></i>
		        <input name="forgot_password_repeat_new_password" class="input-field"
							type="password" placeholder="Потвърди паролата">
				</div>
				<br><br>				
							
				<table Class="table">
		
					<tr>
						<td Class="i">
						
						<input type="submit"  class="button"
							id="forgot_password_submit" value="Потвърди">
							
							</td>
							
					</tr>

				</table>
					
			</form>


			<p>${change_password_message}
		</div>
	</main>



	<script>
		$(document).ready(function() {

			$("#login_submit").on("click", function() {

				$.ajax({

					type : "GET",
					url : "admin_servlet_generate_lists",
					beforeSend : function() {
						$("#loader").show();
					},
					complete : function() {
						$("#loader").hide();
					}

				});

			});

		});
	</script>


</body>