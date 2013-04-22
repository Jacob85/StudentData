<!doctype html>
<%@page import="java.util.List"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css" />

</head>

<body>
<%
	User user= (User)session.getAttribute("user");
	List<String> cartList=(List<String>)  session.getAttribute("cart");
	List<String> historylist = (List<String>) session.getAttribute("history");
	List<String> userFilesList= (List<String>)session.getAttribute("userFiles");
%>
<nav><p class="focus">Hello <%=" "+user.getUserName() %> </p> </nav>

</body>
</html>