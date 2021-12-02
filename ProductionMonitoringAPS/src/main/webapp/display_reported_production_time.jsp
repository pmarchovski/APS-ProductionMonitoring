<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_fixed">

 <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Справка отчетено време от фишов</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

</div>


	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="printers_production_servlet_display_reported_production_time_servlet">

				<table>

					<tr>
						
						<td Class="e"><input type="date"
							name="dsplay_reported_production_time_start_date" 
							class="input_small">
							</td>
					

						
						<td Class="e"><input type="date"
							name="dsplay_reported_production_time_end_date" 
							class="input_small">
							</td>
					

						<td Class="e"> <input 
					        type="text"
						    name="dsplay_reported_production_time_orders"
						    class="input_small"
						    placeholder="Поръчка">
						    						
					</td>
					

						<td Class="e"><select
							name="dsplay_reported_production_time_product_number" 
							Class="input_small">

								<option value="all">Изделие</option>
								<c:forEach items="${product_number_name_collection}" var="newArray">
									<option value="${newArray}">${newArray}</option>
								</c:forEach>

						</select></td>
					
					<td Class="e">
					       <input type="submit"
                                  value="Покажи"
                                  class="button">
                     </td>
					</tr>

				</table>
			</form>	
		</div>


	
		
		   <div id="div_main_overflow">
		
			<table>

             <thead Class="thead">
                 ${display_reported_tiem_from_production_cards_table_head }
                </thead>      
      
                <tbody Class="tbody">      
	            ${display_reported_tiem_from_production_cards_table_body }			
               </tbody>	  
			</table>
			</div>
		</div>



</body>
</html>