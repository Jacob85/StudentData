<!doctype html>
<%@page import="java.util.List"%>
<%@page import="il.ac.shenkar.studentdata.*" %>
<%@ page errorPage="errorPage.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="headLineTag" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="https://studentportal-jscapps.rhcloud.com/StudentData/css/style.css">
</head>
<body>
<%
	  int todoNum=0,doneNum=0;
	  User user= (User)session.getAttribute("user");
	  Register r=(Register)session.getAttribute("register");
		List<String> cartList=(List<String>)  session.getAttribute("cart");
		List<String> historylist = (List<String>) session.getAttribute("history");
		if(cartList!=null)
			todoNum=cartList.size();
		if(historylist!=null)
			doneNum=historylist.size();
%>
<p  style="position: absolute; left: 5%; top: 050px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName() %>|<a href="#"> Log out</a> </p> 
<p  class="focus" style="text-decoration:none; position: absolute; left: 90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/cart.jsp" >Todo- <%="   "+todoNum %> </a> </p>
<p  class="focus" style="position: absolute;left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done- <%="   "+doneNum %></a></p>
<menu:MenuTag menuItem1="Home Page" menuItem2="Upload " menuItem3="About" menuItem1Link="StudentData/AfterLogin.jsp" menuItem2Link="#" menuItem3Link="#"/>
<headLineTag:HeadLinePage headLine="Upload"/>
<p class="tabBar"></p>
       <div style="position: absolute; left: 42%; top: 101px"> 
        <form action="StudentData/upload=true" method="post" enctype="multipart/form-data" 
name="productForm" id="productForm">
<input  type="file" name="file" id="file" required>
<input type="text"  name="description"  class="text-field" placeholder="Description"  required>
 <select style="width: 245px; height: 50px" name="uni" class="text-field"  id="Select1">
    <%
    	for(String uni:r.getUnis())
    	{
    		out.write("<option class='text-field'  value='"+uni+"'>");
    		out.write(uni);
    		out.write("</option>");
    	}
    %>
    </select>
    <select style="width: 245px; height: 50px" name="year" class="text-field"  id="Select1">
    <option class="text-field"   value="a">Fist year</option>
    <option class="text-field"   value="b">Second year</option>
    <option class="text-field"  value="c">Third year</option>
    <option class="text-field"  value="d">Forth year</option>
	</select>
        <select style="width: 245px; height: 50px" name="trend" class="text-field"  id="Select1">
    <%
    	for(String trend:r.getTrends())
    	{
    		out.write("<option class='text-field'  value='"+trend+"' >");
    		out.write(trend);
    		out.write("</option>");
    	}
    %>
    </select>
<input type="text"  name="course"  class="text-field" placeholder="Course" required>
<input class="button"  type="submit" name="Submit" value="Submit">
</form>
</div>
</body>

</html>
