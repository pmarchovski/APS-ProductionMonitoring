<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

<div id="div_main_container_fixed">

   <table>
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/Info.png"></td>
      <td Class="a"><h2>Генериране на производствени планове</h2></td>   
    </tr>
   </table>

</div>

<div id="div_empty"></div>

		<div Class="div_right_container">
			<form method="get"
				action="printers_production_servlet_get_production_plan_servlet">
				<table>

					<tr>
						<th Class="c" colspan="2">Генериране на седмичен план за производство</th>
					</tr>

					<tr>
						<th Class="c">Седмица</th>
						<th Class="c">Производство</th>
					</tr>

                    <tr>
					<td Class="d"><input type="week"
						name="production_capacity_production_plan_week" class="input">


					</td>

					<td Class="d"><select
						name="production_capacity_production_plan_department"
						class="input">
							<option value="All">Всички</option>
							<option value="AL31">AL31</option>
							<option value="AL32">AL32</option>
							<option value="BR21">BR21</option>
							<option value="BR22">BR22</option>
					</select></td>
                   </tr>
				</table>

				<input type="submit" Class="button" value="Изтегли">


			</form>

		</div>

</body>
</html>