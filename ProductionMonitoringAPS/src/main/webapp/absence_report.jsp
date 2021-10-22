<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />
<body>

	<div id="div_main_container_two">
		<h2>Справка въведени отсъствия от часове</h2>

	</div>

	<div id="div_main_overflow">

		<div id="div_main_container">

			<div Class="div_left_container">
				<div style="float: left">

					<p>
					<form name="form_absence_report" method="get" action="printers_production_servlet_absence_report">

					
						<label Class="label_form">Избери служител</label> 
						  <select name="absence_report_operators_name" Class="input" onchange="document.form_absence_report.submit();">

                            <option value="${avsence_current_operator }">${avsence_current_operator }</option>
							<option value="Всички">Всички</option>
							<c:forEach items="${operators_name_collection}"
								var="operatorsNameCollection">
								<option value="${operatorsNameCollection}">${operatorsNameCollection}</option>
							</c:forEach>
                          </select>

					</form>

				</div>
			</div>
			<div style="float: left">
                
                <div Class="table">
				<table>

					<thead Class="thead">${absence_table_head }
					</thead>

					<tbody Class="tbody">${absence_table_body }
					</tbody>

				</table>
               </div>
			</div>
		</div>
	</div>

</body>
</html>