<!doctype html>
<%@page import="java.util.List"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css">
</head>
<body>
<%
	User user= (User)session.getAttribute("user");
	List<String> cartList=(List<String>)  session.getAttribute("cart");
	List<String> historylist = (List<String>) session.getAttribute("history");
	List<String> subjects= (List<String>)session.getAttribute("subjects");
%>
<p  class="focus" style="position: absolute; left: 34px; top: 050px; width: 137px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName()+"  |  " %>  <a href="#"> Log out</a> </p> 
<p  class="focus" style="text-decoration:none; position: absolute; left: 883px; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href=" http://localhost:8080/StudentData/StudentData/cart.jsp" >Todo</a> </p>
<p  class="focus" style="position: absolute; left: 883px; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done</a> </p>
<a id="menuitem" href="#" style=" left: 338px;opacity:.9">Home Page</a>
<a id="menuitem" href="/StudentData/upload.jsp" style=" left: 538px;">Upload</a>
<a id="menuitem" href="# " style="left: 704px;">About</a>
        <p class="tabBar"></p>
        
    <div style="position: absolute; left: 471px; top: 308px;">

<table  id="filestable" style="border:none"  width="200px" border="1">
<% 
	String subjectUrl="http://localhost:8080/StudentData/StudentData"+ user.getUniversity()+"/"
						+user.getTrend()+"/"+user.getYear()+"/";
	for(String subject : subjects)
	{
		out.write("<tr style='border:none'>"
			    +"<td ><a href="+subjectUrl+subject+">"+subject+"</a></td> </tr>");
	}
	%>
	</table>
	</div>
</body>

</html>
