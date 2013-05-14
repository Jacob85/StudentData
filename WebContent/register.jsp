<!doctype html>
<%@page import="java.util.List"%>
<%@page import="il.ac.shenkar.studentdata.*" %>
<%@ page errorPage="errorPage.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css">
</head>

<body>
<% 
	Register r=(Register)session.getAttribute("register");
	String userExistMessage=(String)request.getAttribute("userExistMessage");
%>
<nav><a href="Login.jsp">Log In</a> | <a href="#" class="focus">Register</a></nav>

<form class="register"  action="StudentData/register" method="post">

	<h2>Register</h2>

	<input type="text" name="username" class="text-field" placeholder="User name  "  required/>
    <input type="password" name="password" class="text-field" placeholder="Password" required/>
    <input type="email" name="email" class="text-field" placeholder="E-mail" required/>
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
    <input type="submit" value="Register Account" class="button" />
    <%
	if(userExistMessage!=null)
	{
		out.write("<p>"+userExistMessage+"</p>");
	}
%>

</form>


</body>
</html>
