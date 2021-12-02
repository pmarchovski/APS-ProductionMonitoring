<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<form id="empty_wardrob_info" method="get" action="printers_production_servlet_wardrob_empty_info"></form>
<form id="full_wardrob_info" method="get" action="printers_production_servlet_wardrob_info"></form>


	<div id="div_main_container_fixed">


   <table Class="table">
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/wardrobe_25x25.png"></td>
      <td Class="a"><h2>Информация за гардеробчета</h2></td>   
      
      
    </tr>
   </table>

</div>

<div id="div_empty"></div>

	<div id="div_main_container">



				<table Class="table">

					<tr>
						<td Class="e"><input type="submit"
							value="Покажи свободните гардеробчета" Class="button" form="empty_wardrob_info"></td>
					
						<td Class="e"><input type="submit"
							value="Покажи информация за гардеробчета" class="button" form="full_wardrob_info"></td>
					</tr>

				</table>
		</div>


<br><br>

<div id="div_main_overflow">	
	<div id="div_main_container">

		<table>
		
		 <thead Class="thead">
		${wardrobe_display_free_head }
		</thead>
		
		<tbody Class="tbody">
			${wardrobe_display_free_body }
		</tbody>	

		</table>
	</div>
	</div>
</body>
</html>