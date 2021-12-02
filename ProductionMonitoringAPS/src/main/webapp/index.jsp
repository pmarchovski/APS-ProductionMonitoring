<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="header.jsp" />

<body>

 <div id="loader" class="center"></div>
  
         
    <script>
        document.onreadystatechange = function() {
            if (document.readyState !== "complete") {
                document.querySelector(
                  "body").style.visibility = "hidden";
                document.querySelector(
                  "#loader").style.visibility = "visible";
            } else {
                document.querySelector(
                  "#loader").style.display = "none";
                document.querySelector(
                  "body").style.visibility = "visible";
            }
        };
    </script>

    <div id="div_main_container">
        <table>
           <tr>
             <td Class="a">${avatar }</td>
             <td Class="a"><h2>${login_inform_massage }</h2></td>
           </tr>
        </table>
    </div>
    
    <div id="div_main_container">
        <table>
        
           <tr>
           <th Class="j">Производство принтери</th>
           <th Class="j">Производство TPH</th>
           <th Class="j">Производство пластмаси</th>
           <th Class="j">Счетоводство</th>
           <th Class="j">Други</th>
           </tr>
        
        
           <tr>
             <td Class="a">
               <a href="printers_production.jsp">
                 <img Class="img" alt="Qries" src="pictures/printers_production.png"
                      width=400" height="200">
               </a>   
             </td>
             
              <td Class="a">
               <a href="tph_production.jsp">
                 <img Class="img" alt="Qries" src="pictures/tph.png"
                      width=400" height="200">
               </a>   
             </td>
             
              <td Class="a">
               <a href="#">
                 <img Class="img" alt="Qries" src="pictures/plastic_production.jpg"
                      width=400" height="200">
               </a>   
             </td>
             
             <td Class="a">
               <a href="finance_accounting.jsp">
                 <img Class="img" alt="Qries" src="pictures/accounting.jpeg"
                      width=400" height="200">
               </a>   
             </td>
             
              <td Class="a">
               <a href="#">
                 <img Class="img" alt="Qries" src="pictures/others.png"
                      width=400" height="200">
               </a>   
             </td>

           </tr>
        </table>
    </div>

</body>
</html>