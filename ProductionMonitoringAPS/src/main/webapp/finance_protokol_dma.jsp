<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_two">
<h2>Протокол за въвеждане в експлоатация на ДМА</h2>

</div>


	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="create_dma_protokol_servlet">

				<table>

					<tr>
						<td Class="g"><label Class="label_form">Дата</label></td>
						<td Class="e"><input type="date"
							name="finance_protokol_dma_date" class="input" required="true">
						</td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Наименование на актива </label></td>
						<td Class="e"><input type="text" placeholder="име на актива"
							name="finance_protokol_dma_active_name" class="input" required="true">
						</td>
					</tr>

				

					<tr>
						<td Class="g"><label Class="label_form">Дата за въвеждане в експлоатация</label></td>
						<td Class="e"><input type="date"
							name="finance_protokol_dma_date_exploatation" class="input" required="true">
						</td>
					</tr>


			

					<tr>

						<td Class="g"><label Class="label_form">Доставчик </label></td>
						<td Class="e"><input type="text" placeholder="доставчик"
							name="finance_protokol_dma_supplier" class="input" required="true">
						</td>
					</tr>
				
				
			     	<tr>

                        <td Class="g"><label Class="label_form">Местонамиране </label></td>
                        <td Class="e"> 
                        <select name="finance_protokol_dma_place"
                                class="input" required="true">
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

						<td Class="g"><label Class="label_form">Разходен център / Проект </label></td>
						 <td Class="e"> 
                        <select name="finance_protokol_dma_project"
                                class="input" required="true">
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

						<td Class="g"><label Class="label_form">Марка и модел </label></td>
						<td Class="e"><input type="text" placeholder="марка и модел"
							name="finance_protokol_dma_model" class="input" required="true">
						</td>
					</tr>


				</table>
		</div>



		<div style="float: left">

			<table>
                  
				
				     <tr>

						<td Class="g"><label Class="label_form">Сериен номер </label></td>
						<td Class="e"><input type="text" placeholder="сериен номер"
							name="finance_protokol_dma_serial_number" class="input" required="true">
						</td>
					</tr>
					
					<tr>

						<td Class="g"><label Class="label_form">Инв. номер </label></td>
						<td Class="e"><input type="text" placeholder="инв. номер"
							name="finance_protokol_dma_invemtory_number" class="input" required="true">
						</td>
					</tr>

                     <tr>

						<td Class="g"><label Class="label_form">Отговорно лице </label></td>
						<td Class="e"><input type="text" placeholder="отговорно лице"
							name="finance_protokol_dma_responsibility_person" class="input" required="true">
						</td>
					</tr>


                     <tr>

						<td Class="g"><label Class="label_form">Производител </label></td>
						<td Class="i"><input type="text" placeholder="производител"
							name="finance_protokol_dma_produced" class="input" required="true">
						</td>
					</tr>


                     <tr>

						<td Class="g"><label Class="label_form">Предназначение </label></td>
						<td Class="i"><input type="text" placeholder="предназначение"
							name="finance_protokol_dma_about" class="input" required="true">
						</td>
					</tr>


                    <tr>

						<td Class="g"><label Class="label_form">Основание за покупка </label></td>
						<td Class="i"><input type="text" placeholder="основание за покупка"
							name="finance_protokol_dma_reason_for_buy" class="input" required="true">
						</td>
					</tr>
					
					
					<tr>

						<td Class="g"><label Class="label_form">Забележка </label></td>
						<td Class="i"><input type="text" placeholder="забележка"
							name="finance_protokol_dma_note" class="input" required="true">
						</td>
					</tr>


                <tr>
				    <td Class="h"></td>
					<td Class="i"><input type="submit" value="Запис и печат" class="button"></td>

				</tr>


			</table>
		</div>

		</form>

</div>
</body>
</html>