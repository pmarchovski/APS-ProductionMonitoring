<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>


<div id="div_main_container_fixed">

 <table>
			      <tr>
			         <td Class="a"><img src="pictures/ICONS/png/User group.png"></td>
			         <td Class="a"><h2>Контролна карта за регистриране на брак на TPH след заливане със смола / (Control Card Reject - Final inspection)</h2></td>   
			      </tr>
			    </table>

</div>

<div id="div_empty"></div>

	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="tph_data_servlet">

				<table>

					<tr>
						<td Class="g"><label Class="label_form">Дата </label></td>
						<td Class="e"><input type="date"
							name="tph_input_scrap_after_epoxy_date" class="input_small" required="true">
						</td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Поръчка </label></td>
						<td Class="e"><input type="text" placeholder="поръчка"
							name="tph_input_scrap_after_epoxy_order" class="input_small" required="true">
						</td>
					</tr>

					<tr>

						<td Class="g"><label Class="label_form">Оператор </label></td>
						<td Class="e"><select
							name="tph_input_scrap_after_epoxy_name" Class="input">

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
							name="tph_input_scrap_after_epoxy_shift" Class="input">

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

					<td Class="g"><label Class="label_form">Not filled TPH with epoxy
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_not_filled_epoxy"
						class="input_small"
						placeholder="Незалята напълно глава"></td>
				</tr>

				<tr>

					<td Class="g"><label Class="label_form">Epoxy on the ceramic
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_epoxy_on_the_ceramic"
						class="input_small"
						placeholder="Смола върху керамиката"></td>
				</tr>

				<tr>

					<td Class="g"><label Class="label_form">Epoxy on the PCB
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_epoxy_on_the_pcb"
						class="input_small"
						placeholder="Смола върху платката"></td>
				</tr>

				<tr>

					<td Class="g"><label Class="label_form">TPH without epoxy
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_tph_without_epoxy"
						class="input_small"
						placeholder="Глава без смола"></td>
				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Air bubbles after drying
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_bubbles_after_drying"
						class="input_small"
						placeholder="Мехури след сушене"></td>
				</tr>


				<tr>

					<td Class="g"><label Class="label_form">Epoxy is not get hard
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_epoxy_is_not_get_hard"
						class="input_small"
						placeholder="Невтвърдена смола"></td>
						
				</tr>

				<tr>

					<td Class="g"><label Class="label_form">Change in the color after drying
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_change_color"
						class="input_small"
						placeholder = "Промяна на цвета на смолата
							след сушене"></td>
				</tr>

			</table>
		</div>


		<div style="float: left">

			<table>

				<tr>

					<td Class="g"><label Class="label_form">Total
							inspected
					</label></td>
					<td Class="h"><input type="text"
						name="tph_input_scrap_after_epoxy_total_inspected"
						class="input_small"
						placeholder="Общо произведени"
						required="true"></td>
				</tr>

				<tr>
				    <td Class="h"></td>
					<td Class="h"><input type="submit" value="Въведи" class="button"></td>

				</tr>
			</table>
${include_scrap_after_epoxy_done_status }
		</div>

		</form>

	</div>
</body>
</html>