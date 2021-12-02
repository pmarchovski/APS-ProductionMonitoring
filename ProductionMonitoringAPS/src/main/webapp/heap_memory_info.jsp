<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>


<jsp:include page="header.jsp" />


<body>

<br><br>
<br><br>
${heap_memory_total }
<br><br>
${heap_memory_free }
<br><br>
${heap_memory_max }
<br><br>
${processor }
<br><br>

</body>
</html>