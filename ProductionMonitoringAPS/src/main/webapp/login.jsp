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

			<form id="login_form" method="get" action="admin_servlet_login"
				id="login_form">
				
				
			   <table Class="table">
			    
			    <br><br> <br><br>
			    
			    
			    
				<div class="input-container">
				
				<i class="fa fa-user icon"></i>
				            <input name="login_user_name" 
				                   class="input-field" 
				                   type="text"
							       placeholder="user name">
				</div>	
				<br><br>	
			    
		    	<div class="input-container">
				<i class="fa fa-key icon"></i>
		                    <input name="login_user_password" 
		                           class="input-field"
							       type="password" 
							       placeholder="Password">
				</div>
				<br><br>			
							
				
		     
					<tr>
						<td Class="i">
						     <input type="submit"  
						            class="button"
							        id="login_submit" 
							        value="Login">
				        </td>
							
					</tr>
		
	
				<br><br>
			
				
				    <tr>
					    
						 <td Class="e">
							<h4><a href="forgot_password.jsp">Забравена парола</a></h4>
						</td>	
					
					</tr>
				
				  </table>
				
					
			</form>


			<p>${login_inform_massage} ${change_password_message }
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