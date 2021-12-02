<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
   <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   <script src="https://jquery-3.3.1.min.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
 <header>
 <jsp:include page="header.jsp" />
 </header>  

<body>

<div id="div_main_container_fixed">

   <table>
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/Info.png"></td>
      <td Class="a"><h2>Производствен капацитет</h2></td>   
    </tr>
   </table>

</div>

<div id="div_empty"></div>
<div id="loader" class="center" style="display:none"><img src="pictures/spinner.gif"></div>



	<div>
		<h3>${before_path}${path }</h3>
	
	</div>

		<div id="div_main_container_two">

			<div Class="div_left_container">
				<table>
					<tr>
						<td Class="e">
							<form id="upload_files_form" action="admin_servlet_upload_files" method="post" enctype="multipart/form-data">
							



								<p>Избери файл</p>
								<input type="file" name="fileToUpload" Class="button" multiple>
								<br />
								<br /> <input type="submit" value="Изпрати" Class="button" id="click_upload_file">
	
							</form>


						</td>
						</tr>
                   </table>
         </div>
         </div>

</body>
</html>