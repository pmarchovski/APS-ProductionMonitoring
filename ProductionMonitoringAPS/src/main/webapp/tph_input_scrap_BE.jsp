<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>


<div id="div_main_container_fixed">

 <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Контролна карта за BE / (Control card BE, TPH production)</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="tph_data_be_servlet">

				<table>

					<tr>
						<td Class="g"><label Class="label_form">Дата </label></td>
						<td Class="e"><input type="date"
							name="tph_input_scrap_be_date" class="input_small" required="true">
						</td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Поръчка </label></td>
						<td Class="e"><input type="text" placeholder="поръчка"
							name="tph_input_scrap_be_order" class="input_small" required="true">
						</td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Оператор </label></td>
						<td Class="e"><select
							name="tph_input_scrap_be_operators_name" Class="input">

								<option value=""></option>
								<c:forEach items="${operators_name_collection}"
									var="operatorsNameCollection">
									<option value="${operatorsNameCollection}">${operatorsNameCollection}</option>
								</c:forEach>

						</select>
						</td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Смяна </label></td>
						<td Class="e"><select
							name="tph_input_scrap_be_shift" Class="input">

								<option value="1">Първа</option>
								<option value="2">Втора</option>
								<option value="31">Нощтна</option>
								<option value="3">Редовна</option>

						</select></td>
					</tr>

				</table>
		</div>



		<div style="float: left">

			<table>

				<tr>

					<td Class="g"><label Class="label_form">Визуален контрол</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_visual_control_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_visual_control_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>

						
				</tr>

				
				<tr>

					<td Class="g"><label Class="label_form">Асемблиране</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_assembly_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_assembly_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>
						
				</tr>
				
				
					<tr>

					<td Class="g"><label Class="label_form">Бондер чипове</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_bonder_chip_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_bonder_chip_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>
						
				</tr>
				
				
				<tr>

					<td Class="g"><label Class="label_form">Бондер жица</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_bonder_wire_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_bonder_wire_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>

						
				</tr>
				
				
				<tr>

					<td Class="g"><label Class="label_form">Заливане</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_flooding_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_flooding_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>	
				</tr>
				

			</table>
		</div>


		<div style="float: left">

			<table>


                <tr>

					<td Class="g"><label Class="label_form">Сушене</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_drying_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_drying_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>

				</tr>


                 <tr>

					<td Class="g"><label Class="label_form">Разлепване</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_ungluing_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_ungluing_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>
						
				</tr>


                 <tr>

					<td Class="g"><label Class="label_form">Почистване</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_cleaning_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_cleaning_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>
						
				</tr>



                  <tr>

					<td Class="g"><label Class="label_form">Тестване</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_be_test_ok"
						class="input_small"
						placeholder="OK">
						
						<br>
						
						<input type="text"
						name="tph_input_scrap_be_test_nok"
						class="input_small"
						placeholder="NOK">
                        
						</td>
						
				</tr>


				<tr>
				    <td Class="h"></td>
					<td Class="h"><input type="submit" value="Въведи" class="button"></td>

				</tr>
			</table>
${include_scrap_be_done_status }

		</div>

		</form>

	</div>
</body>
</html>