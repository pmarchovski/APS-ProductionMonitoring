<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

	<div id="div_main_container_two">
		<h2>Информация за гардеробчета</h2>

	</div>

	<div id="div_main_container">

		<div style="float: left">

			<form method="get" action="printers_production_servlet_wardrob_info">

				<table>

					<tr>
						<td Class="e"><input type="submit"
							value="Покажи свободните гардеробчета" class="button"></td>
					</tr>

				</table>
			</form>
		</div>
	</div>

<br><br>
	<div id="div_main_container">

		<table>${wardrobe_display_free_head }
			${wardrobe_display_free_body }

		</table>
	</div>
</body>
</html>