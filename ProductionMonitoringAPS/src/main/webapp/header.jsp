<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>APS Production Monitoring</title>
<link rel="stylesheet" href="style/style.css">
</head>



<header>


	<div Class="header">
		<div Class="header_left">
			<h1>АПС Трейдинг ООД</h1>

		</div>

		<div Class="header_right">${user_name} ${login_inform_massage}</div>

	</div>

<div class="example">
    <div class="menu">
        <span>
            <ul id="nav">
            
                <li><a href="printers_production.jsp">Производство принтери</a>
                   
                
                <li><a href="#">Производство пластмаси</a>
                   
                <li><a href="tph_production.jsp">TPH производство</a>
                
                <li><a href="finance_acaunting.jsp">Счетоводство</a>
                  
                
                ${registration }
                <li><a href="index.jsp">Начало</a></li>
                <li><a href="login.jsp">Login</a></li>
            </ul>
            
            
            
        </span>
        
         <div Class="topnav_input">
				<form method="get" action="admin_servlet_logout">
					<input type="submit" value="Изход">
				</form>
			</div>

			<div Class="topnav_input">

				<form method="get" action="admin_servlet_login">
				
					<input type="text" class="inputs" 
					       placeholder="потребителско име"
						   name="login_user_name"> 
						   
				    <input type="password" class="inputs"
						   placeholder="парола" 
						   name="login_user_password"> 
					<input
						type="submit" value="Вход">

				</form>
			</div>
        
        
        
    </div>
</div>

  <div Class="nav_bottom_line">
 
 </div> 
 
  
 <div Class="header_line">
 
 </div> 
  
</header>