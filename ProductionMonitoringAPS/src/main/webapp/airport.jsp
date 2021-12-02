<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />


<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="js/jquery.plugin.js"></script>
<script src="js/jquery.flightboard.js"></script>


<script>
$(function () {
	$('#basicBoard').flightboard({messages: ['DELAY', '204014'],
		lettersImage: 'flightBoardLarge.png',
		shadingImages: ['flightBoardHigh.png', 'flightBoardShad.png']});
});
</script>



<body>

<div class="div_main_container">
	
<div id="basicBoard"></div>
	
	
</div>
</body>