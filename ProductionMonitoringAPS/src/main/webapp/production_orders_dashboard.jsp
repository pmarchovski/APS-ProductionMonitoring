<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<jsp:include page="header.jsp" />

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery.plugin.js"></script>
<script src="js/jquery.flightboard.js"></script>
<body>

	
	<div id="div_main_container_fixed">
	
	  <form id="production_orders_dashboard_form_id" 
            action="printers_production_servlet_display_production_orders_dashboard"
            name="production_orders_dashboard_form_name"></form>
       
       <table>
       <tr>
                            <td Class="a">
							  <label class="container">AL31
                                <input type="checkbox"
                                       form="production_orders_dashboard_form_id"
								       name="production_orders_dashboard_check_box"
								       checked="true" 
                                       value="AL31">
                                <span class="mark"></span>
                              </label></td>

							<td Class="a">
							  <label class="container">AL32
                                <input type="checkbox"
                                       form="production_orders_dashboard_form_id"
								       name="production_orders_dashboard_check_box" 
								       checked="true"
                                       value="AL32">
                                <span class="mark"></span>
                              </label></td>

							<td Class="a">
							  <label class="container">BR21
                                <input type="checkbox"
                                       form="production_orders_dashboard_form_id"
								       name="production_orders_dashboard_check_box" 
								       checked="true"
                                       value="BR21">
                                <span class="mark"></span>
                              </label></td>

							<td Class="a">
							  <label class="container">BR22
                                <input type="checkbox"
                                       form="production_orders_dashboard_form_id"
								       name="production_orders_dashboard_check_box" 
								       checked="true"
                                       value="BR22">
                                <span class="mark"></span>
                              </label></td>
								
								
							<td Class="d">
							    <input 
							    type="week"
						        name="production_orders_dashboard_week"
						        form="production_orders_dashboard_form_id" 
						        class="input"></td>	
								
								
							<td Class="d">
							    <input 
							    type="month"
						        name="production_orders_dashboard_month" 
						        form="production_orders_dashboard_form_id"
						        class="input"
						        min="2018-01" 
						        max="2030-12"
						        value="${current_month }"></td>		
										
								
							<td Class="a">
							    <input 
							    type="submit"
                                value="Display"
                                form="production_orders_dashboard_form_id"
                                class="button"></td>	
       
       </tr>
       </table>
       
	
	</div>
	<br><br><br><br>
	<div id="div_main_container">
    <div id="div_main_overflow">
     <table>
     <thead Class="thead">
     ${production_orders_dashboard_table_head }
     </thead>
     
     <tbody Class="tbody">
     <tr>
     ${production_orders_dashboard_table_body }
     </tr>
     </tbody>
     </table>
 </div>
 </div>


</body>
</html>