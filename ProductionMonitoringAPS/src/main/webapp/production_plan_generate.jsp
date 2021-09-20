<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="header.jsp"/>
<body>

<div id="div_main_overflow">

<div Class="div_right_container">
<form method="get", action="get_production_plan_servlet">
<table>

<tr>
<th Class="c" colspan="3">Генериране на седмичен план за производство</th>
</tr>

<tr>
<th Class="c">Година</th>
<th Class="c">Седмица</th>
<th Class="c">Производство</th>
</tr>

<td Class="d">
<select
							name="production_capacity_production_plan_year" class="input_small">
							<option value="2021">2021</option>
							<option value="2022">2022</option>				
							<option value="2023">2023</option>
							<option value="2024">2024</option>
							<option value="2020">2020</option>
							<option value="2019">2019</option>
							<option value="2018">2018</option>
						</select> 

</td>

<td Class="d">
<select
							name="production_capacity_production_plan_week" class="input_small">
							<option value="1">1</option>
							<option value="2">2</option>				
							<option value="3">3</option>
							<option value="4">4</option>
							<option value="5">5</option>
							<option value="6">6</option>
							<option value="7">7</option>
							<option value="8">8</option>
							<option value="9">9</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">12</option>
							<option value="13">13</option>
							<option value="14">14</option>
							<option value="15">15</option>
							<option value="16">16</option>
							<option value="17">17</option>
							<option value="18">18</option>
							<option value="19">19</option>
							<option value="20">20</option>
							<option value="21">21</option>
							<option value="22">22</option>
							<option value="23">23</option>
							<option value="24">24</option>
							<option value="25">25</option>
							<option value="26">26</option>
							<option value="27">27</option>
							<option value="28">28</option>
							<option value="29">29</option>
							<option value="30">30</option>
							<option value="31">31</option>
							<option value="32">32</option>
							<option value="33">33</option>
							<option value="34">34</option>
							<option value="35">35</option>
							<option value="36">36</option>
							<option value="37">37</option>
							<option value="38">38</option>
							<option value="39">39</option>
							<option value="40">40</option>
							<option value="41">41</option>
							<option value="42">42</option>
							<option value="43">43</option>
							<option value="44">44</option>
							<option value="45">45</option>
							<option value="46">46</option>
							<option value="47">47</option>
							<option value="48">48</option>
							<option value="49">49</option>
							<option value="50">50</option>
							<option value="51">51</option>
							<option value="52">52</option>
                            <option value="52">53</option>
							
						</select> 

</td>

<td Class="d">
<select
							name="production_capacity_production_plan_department" class="input_small">
							<option value="All">Всички</option>
							<option value="AL31">AL31</option>
							<option value="AL32">AL32</option>				
							<option value="BR21">BR21</option>
							<option value="BR22">BR22</option>
						</select> 

</td>

</table>

<input type="submit"
       Class="button"
       value="Изтегли">


</form>

</div>




<div>



</body>
</html>