<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


<jsp:include page="header.jsp" />


<body>

<div id="container">


<form id="form" method="get" action="printers_production_servlet_orders_information_servlet">

<div id="loader" style="display:none;"></div>  

<select name="orders_info_select_year" Class="input_small">

       <option value="1">Всички</option>
       <option value="2021">2021</option>
       <option value="2020">2020</option>
       <option value="2019">2019</option>
       <option value="2018">2018</option>
       <option value="2022">2022</option>


</select>



<input type="submit"
       value="GET INFO"
       Class="button"
       id="ButtonClick">
       
</form>     





<button id="getData">Show</button>

<div id="result">


</div>

</div>
<span id="result">dgdf</span>
<script>

 $(document).ready(function(){
	 
	 $("#getData").on("click", function(){
		 $.ajax({
			 type:"GET",
			 url:"printers_production_servlet_orders_information_servlet",
			 
			
			 
			 beforeSend:function(){
				 $("#loader").show();
			 },
			 
			 complete:function(){
				 $("#loader").hide();
			 },
			 
			 success:function(data){
				var output = "";
				output = '${data.production_order_quantity_info }';
				 
			 }
		 });
	 });
 });


</script>


</body>
</html>