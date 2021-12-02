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
<form id=logout_form method="get" action="admin_servlet_logout"></form>
<form id="login_form" method="get" action="admin_servlet_login"></form>

<div Class="fixed-header">

		<div Class="header_left">
		
		<table style="border-collapse: collapse">
		      <tr>
		         <td style="min-width:100px; border: 0px">
		            <picture>
                            <source srcset="pictures/logo/APSlogo.png" media="(mix-width: 100px)">
                             <img src="pictures/logo/APSlogo.png" alt="MDN">
                    </picture>
		         </td>
		         
		          <td style="width:100%; border: 0px">
		             <h1 style="color:black">APS Трейдинг ООД</h1>
		          </td>
		
		          <td style="min-width:100px; border: 0px solid black; background-color: #b0b0b0">${avatar }</td>
                  <td style="min-width:200px; border: 0px solid black; background-color: #b0b0b0">${user_name } ${heap_mamory_data }</td>
		     </tr>
		</table>

		</div>


<div class="example">


    <div class="menu">
    
          <table>
               <tr>
                   <td style="min-width:1000px; border: 0px solid white">
    
                     <span>
                       <ul id="nav">
            
                         <li><a href="printers_production.jsp">Производство принтери</a>
                         <li><a href="#">Производство пластмаси</a>
                         <li><a href="tph_production.jsp">TPH производство</a>
                         <li><a href="finance_accounting.jsp">Счетоводство</a>        
                         <li><a href="index.jsp">Начало</a></li>
                         <li><a href="login.jsp">Login</a></li>
                         ${admin_panel }
                         
                     </ul>

                     </span>
                         
                     </td>       
                 
                    
                     <td style="width:100%; border: 0px solid white"></td>
                 
                     <td style="width:100px; border: 0px solid white">
                          <input type="text" 
					       class="inputs" 
					       placeholder="потребителско име"
						   name="login_user_name"
						   form="login_form"> 
        
                     </td>
                     
                     <td style="width:100px; border: 0px solid white">
                           <input type="password" 
				           class="inputs"
						   placeholder="парола" 
						   name="login_user_password"
						   form="login_form"> 
                     </td>
                     
                     <td style="width:50px; border: 0px solid white">
                           <input
						   type="submit" 
						   value="Вход"
						   form="login_form">
                     </td>
                     
                     <td style="width:50px; border: 0px solid white">
                          <input type="submit" 
					       value="Изход"
					       form="logout_form">
                     </td>
                 </tr>
         </table>     
    </div>
</div>

  <div Class="nav_bottom_line">
 
 </div> 

  </div>
  
</header>