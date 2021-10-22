<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_two">
<h2>Генериране на списък с протоколи ДМА</h2>

</div>


	<div id="div_main_container">

      
		  <div style="float: left">

			<form method="get" action="create_dma_protokol_display_servlet">

				<table>


                <tr>
                   <td Class="y"><label Class="label_form">Дата на протокол</label></td>
                   <td Class="y"><label Class="label_form">Дата на въвеждане в експлоатация</label></td>
                   <td Class="y"><label Class="label_form">Доставчик</label></td>
                   <td Class="y"><label Class="label_form">Местонамиране</label></td>
                   <td Class="y"><label Class="label_form">Разходен център</label></td>
                   <td Class="y"><label Class="label_form">Инвентарен номер</label></td>
                   <td Class="y"><label Class="label_form">Отговорно лице</label></td>
                </tr>

                 <tr>
                   <td Class="y"><input type="date" name="finance_protokol_display_update_dma_date" class="input"></td>
                   <td Class="y"><input type="date" name="finance_protokol_display_update_dma_exploatation" class="input"></td>
				   <td Class="y"><input type="text" placeholder="доставчик" name="finance_protokol_display_update_dma_supplier" class="input"></td>
					
				   <td Class="y"> 
                        <select name="finance_protokol_display_update_dma_place"
                                class="input">
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
					
				
				 <td Class="y"> 
                        <select name="finance_protokol_display_update_dma_project"
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
					
					
				<td Class="y"><input type="text" placeholder="инв. номер" name="finance_protokol_display_update_dma_invemtory_number" class="input"></td>
				<td Class="y"><input type="text" placeholder="отговорно лице" name="finance_protokol_display_updatel_dma_responsibility_person" class="input"></td>

                 </tr>

                <tr>
                <td Class="y"><input type="submit" Class="button" value="Покажи" ></td>
                </tr>

			</table>
			
			</form>
		</div>

</div>

<div id="div_main_overflow">
<div style="float: left">

        <div id="div_main_overflow">
            <table Class="a">
            
           ${display_prtocol_collection_table_head }
           ${display_prtocol_collection_table_body }
            
            </table>

       </div>
</div>


<div style="float: left">

<table Class="a">

${ptocol_all_data_table }

</table>

</div>
</div>
</body>
</html>