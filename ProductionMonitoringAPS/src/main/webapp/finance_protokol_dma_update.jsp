<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

	<div id="div_main_container_two">
		<h2>Редактиране на протокол - ДМА</h2>

	</div>


     <div id="div_main_container">

         <div id="div_main_overflow">

		     <div style="float: left">

			<table Class="a">
			
			${ptocol_all_data_table }

			</table>

		 </div>
 
         <div style="float: left">
         <h6 style="font-color: white">. .</h6>
         
         </div>

		 <div style="float: left"></div>

         <form method="get" action="create_dma_protokol_update_final_explicit_servlet">
         <table Class="a">

        <tr>
        <td Class="z">
        <input type="submit" class="button" value="Обнови">
        
        </td>
        </tr>


        <tr>
        <td Class="z">
        <input type="date" name="finance_protokol_dma_update_date" class="input">
        
        </td>
        </tr>
        
         <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_active_name" class="input">
        
        </td>
        </tr>
        
         <tr>
        <td Class="z">
        <input type="date" name="finance_protokol_dma_update_date_exploatation" class="input">
        
        </td>
        </tr>
        
         <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_supplier" class="input" placeholder="доставчик">
        
        </td>
        </tr>


              <tr>
                 <td Class="z"> 
                        <select name="finance_protokol_dma_update_cost_center"
                                class="input">
                               <option value=""></option>
                               <option value="Индъстриал">Индъстриал</option>        
                               <option value="Аутомотив">Ауто</option>
                               <option value="Пластмаси">Пластмаси</option>
                               <option value="TPH">TPH</option>
                               <option value="Качество">Качество</option>
                               <option value="Склад">Склад</option>
                               <option value="Други">Други</option>
                             
                         </select> 
                   </td>
               </tr>

                 <tr>
                   <td Class="z"> 
                        <select name="finance_protokol_dma_update_place"
                                class="input" >
                               <option value=""></option>
                               <option value="AL31">Производство AL31</option>        
                               <option value="Al32">Производство AL32</option>
                               <option value="BR21">Производство BR21</option>
                               <option value="BR22">Производство BR22</option>
                               <option value="Plastic">Производство Пластмаси</option>
                               <option value="TPH">Производство TPH</option>
                               <option value="Warehouse">Склад</option>
                               <option value="R&D">R&D</option>
                               <option value="Laboratory">Лаборатория</option>
                               <option value="Quality_department">Отдел качество</option>
                         </select> 
                   </td>

                   </tr>


         <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_model" class="input" placeholder="марка / модел" >
        
        </td>
        </tr>

         <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_serial_number" class="input" placeholder="сериен номер">
        
        </td>
        </tr>
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_inv_number" class="input" placeholder="инв номер">
        
        </td>
        </tr>
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_responsible_person" class="input" placeholder="отговорно лице">
        
        </td>
        </tr>
        
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_produced_by" class="input" placeholder="производител">
        
        </td>
        </tr>
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_used_by" class="input" placeholder="предназначение">
        
        </td>
        </tr>
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_note" class="input" placeholder="забележка">
        
        </td>
        </tr>
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_active_number" class="input" placeholder="номер на актива">
        
        
          <tr>
        <td Class="z">
        <input type="number" name="finance_protokol_dma_update_value_one" class="input" placeholder="ДАС на актива">
        
        </td>
        </tr>
        
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_value_two" class="input" placeholder="ДАН на актива" >
        
        </td>
        </tr>
        
        
          <tr>
        <td Class="z">
        <input type="text" name="finance_protokol_dma_update_category" class="input" placeholder="категория">
        
        </td>
        </tr>
        
        
          <tr>
        <td Class="z">
        <input type="date" name="finance_protokol_dma_update_include_date" class="input">
        
        </td>
        </tr>
        
		</table>
        </form>
      </div>
     
	</div>

</body>
</html>