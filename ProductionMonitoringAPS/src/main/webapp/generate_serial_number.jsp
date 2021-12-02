<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />

<body>

<div id="div_main_container_fixed">


   <table Class="table">
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
      <td Class="a"><h2>Генериране на сериини номера по поръчка</h2></td>   
    </tr>
   </table>

</div>
<div id="div_empty"></div>

<form id="generate_serial_number_form" method="get" action="printers_production_servlet_generate_serial_number"></form>

	<div style="float: left">

     <table>
           <tr>
              <td Class="d">
                       <input type="text"
				              Class="input_less"
				              placeholder="поръчка"
				              required="true"
				              name="printers_production_generate_serial_number_order"
				              form="generate_serial_number_form">
     
          </td>
             <td Class="d">
                       <input type="submit" 
                              class="button" 
                              value="Генерирай" 
                              form="generate_serial_number_form">
     
             </td>
           </tr>
    </table>
			
				
				

				

			

			

	</div>
	
</body>