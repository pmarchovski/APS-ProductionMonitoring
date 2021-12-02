<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<jsp:include page="header.jsp" />




<body>

<div id="div_main_container_fixed">

   <table>
    <tr>
      <td Class="a"><img src="pictures/ICONS/png/Info.png"></td>
      <td Class="a"><h2>Информация за поръчки по години</h2></td>   
    </tr>
   </table>

</div>

<div id="div_empty"></div>

<a href="/ProductionMonitoringAPS/login.jsp" onClick="return popup(this, 'notes')">my popup</a>


<SCRIPT TYPE="text/javascript"> 
function popup(mylink, windowname) { 
	if (! window.focus)return true; 
	var href; 
    if (typeof(mylink) == 'string') href=mylink; 
    else href=mylink.href; 
    window.open(href, windowname, 'width=800,height=400,left=500,top=400,menubar=yes'); 
    return false; 
    
    } 
</SCRIPT>

</body>
</html>