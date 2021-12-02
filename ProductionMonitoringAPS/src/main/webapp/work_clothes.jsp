<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<form id="work_clothes_for_order" method="get" action="admin_servlet_work_clothes_for_order"></form>


	<div id="div_main_container_fixed">


   <table Class="table">
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/wardrobe_25x25.png"></td>
      <td Class="a"><h2>Поръчка на работно облекло</h2></td>   
      
      
    </tr>
   </table>

</div>
<div id="div_empty"></div>
	<div id="div_main_container">



				<table Class="table">

					<tr>
						<td Class="e"><input type="submit"
							value="Работно облекло за поръчка" Class="button" form="work_clothes_for_order"></td>
				
					</tr>

				</table>
		</div>


<br><br>
	
<div id="div_main_container">	
	
	
	<div style="width: 15%; float:left; margin: 0px;">

		<table>
		<h3 style="text-align: center;">${appron_column }</h3>
		${work_clothe_column_name }		
		${appron_table_for_order }
		</table>
	</div>
	
	
	<div style="width: 15%; float:left; margin: 0px;">	
		<table>
		<h3 style="text-align: center;">${slipper_column }</h3>
		${work_clothe_column_name }		
		${slipper_table_for_order }
		</table>
	</div>
	
	
	<div style="width: 15%; float:left; margin: 0px;">	
		<table>	
		<h3 style="text-align: center;">${heater_column }</h3>
		${work_clothe_column_name }	
		${heater_table_for_order }
		</table>
	</div>
</div>
</body>
</html>