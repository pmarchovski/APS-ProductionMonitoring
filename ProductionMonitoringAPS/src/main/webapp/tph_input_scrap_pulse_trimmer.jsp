<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>


<div id="div_main_container_two">
<h2>Контролна карта за крайна операция FE / (Control card FE final step, TPH production)</h2>
</div>

	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="tph_data_pulse_trimmer_servlet">

				<table>

					<tr>
						<td Class="g"><label Class="label_form">Дата </label></td>
						<td Class="e"><input type="date"
							name="tph_input_scrap_pulse_trimmer_date" class="input_small"
							required="true"></td>
					</tr>
				

					<tr>

						<td Class="g"><label Class="label_form">Поръчка </label></td>
						<td Class="e"><input type="text" placeholder="поръчка"
							name="tph_input_scrap_pulse_trimmer_order" class="input_small"
							required="true"></td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Оператор </label></td>
						<td Class="e"><select
							name="tph_input_scrap_pulse_trimmer_name" Class="input">

								<option value=""></option>
								<c:forEach items="${operators_name_collection}"
									var="operatorsNameCollection">
									<option value="${operatorsNameCollection}">${operatorsNameCollection}</option>
								</c:forEach>

						</select></td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Смяна </label></td>
						<td Class="e"><select
							name="tph_input_scrap_pulse_trimmer_shift" Class="input">

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

					<td Class="g"><label Class="label_form">Серия 1</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_scrap_pulse_trimmer_serie_one_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_one_nok"
						class="input_small" 
						placeholder="NOK">
					</td>


				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Серия 2</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_scrap_pulse_trimmer_serie_two_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_two_nok"
						class="input_small" 
						placeholder="NOK">
					</td>

				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Серия 3</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_scrap_pulse_trimmer_serie_three_ok"
						class="input_small" 
						placeholder="OK"> <br>
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_three_nok"
						class="input_small" 
						placeholder="NOK">
					</td>

				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Серия 4</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_scrap_pulse_trimmer_serie_four_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_four_nok"
						class="input_small" 
						placeholder="NOK">
					</td>


				</tr>


			</table>
		</div>


		<div style="float: left">

			<table>

				<tr>

					<td Class="g"><label Class="label_form">Серия 5</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_scrap_pulse_trimmer_serie_five_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_five_nok"
						class="input_small" 
						placeholder="NOK">
					</td>


				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Серия 6</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_scrap_pulse_trimmer_serie_six_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_six_nok"
						class="input_small" 
						placeholder="NOK">
					</td>

				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Серия 7</label></td>
					<td Class="h">
					    <input type="text"
						name="tph_input_scrap_pulse_trimmer_serie_seven_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_seven_nok"
						class="input_small" 
						placeholder="NOK">
					</td>

				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Серия 8</label></td>
					<td Class="h">
					    <input type="text"
						name="tph_input_scrap_pulse_trimmer_serie_eight_ok"
						class="input_small" 
						placeholder="OK"> <br> 
						
						<input
						type="text" 
						name="tph_input_scrap_pulse_trimmer_serie_eight_nok"
						class="input_small" 
						placeholder="NOK">
					</td>


				</tr>


				<tr>
				    <td Class="h"></td>
					<td Class="h"><input type="submit" value="Въведи"
						class="button"></td>

				</tr>
			</table>

${include_scrap_pulse_trimmer_done_status }

		</div>

		</form>

	</div>
</body>
</html>