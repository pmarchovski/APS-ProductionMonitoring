<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_two">
<h2>Контролна карта за краен тест / (Final test control card)</h2>

</div>


	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="tph_data_final_test_servlet">

				<table>

					<tr>
						<td Class="g"><label Class="label_form">Дата </label></td>
						<td Class="e"><input type="date"
							name="tph_input_final_test_date" class="input_small"
							required="true"></td>
					</tr>
					<br>

					<tr>

						<td Class="g"><label Class="label_form">Поръчка </label></td>
						<td Class="e"><input type="text" placeholder="поръчка"
							name="tph_input_scrap_final_test_order" class="input_small"
							required="true"></td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Оператор </label></td>
						<td Class="e"><select
							name="tph_input_final_test_name" Class="input">

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
							name="tph_input_final_test_shift" Class="input">

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
					<td Class="g"><label Class="label_form">TPH 01</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_tph_01"
						class="input_small">						
					</td>
				</tr>


				<tr>
					<td Class="g"><label Class="label_form">TPH E02</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_tph_e02"
						class="input_small">					
					</td>
				</tr>


				<tr>
					<td Class="g"><label Class="label_form">TPH E03</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_tph_e03"
						class="input_small">						
					</td>
				</tr>


				<tr>
					<td Class="g"><label Class="label_form">TPH E04</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_tph_e04"
						class="input_small">					
					</td>
				</tr>
			
				
                <tr>
					<td Class="g"><label Class="label_form">TPH E08</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_tph_e08"
						class="input_small">
						
					</td>
				</tr>
			</table>
		</div>


		<div style="float: left">

			<table>
			
				<tr>
					<td Class="g"><label Class="label_form">THM 01</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_thm_01"
						class="input_small">
						
					</td>
				</tr>

				<tr>			
					<td Class="g"><label Class="label_form">THM 02</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_thm_02"
						class="input_small">					
					</td>
				</tr>


				<tr>			
					<td Class="g"><label Class="label_form">THM 04</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_thm_04"
						class="input_small">
						
					</td>
				</tr>


				<tr>			
					<td Class="g"><label Class="label_form">Други</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_others"
						class="input_small">
						
					</td>
				</tr>

                 <tr>			
					<td Class="g"><label Class="label_form">Общо количество от тест</label></td>
					<td Class="h">
					    <input 
					    type="text"
						name="tph_input_final_test_quantity"
						class="input_small">
						
					</td>
				</tr>


				<tr>
				    <td Class="h"></td>
					<td Class="h"><input type="submit" value="Въведи"
						class="button"></td>

				</tr>
			</table>

${include_final_test_information_done_status }

		</div>

		</form>

	</div>
</body>
</html>