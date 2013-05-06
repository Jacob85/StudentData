<!doctype html>
<%@page import="java.util.List"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link rel="stylesheet" href="https://studentportal-jscapps.rhcloud.com/StudentData/css/style.css">
</head>
<body>
<%
	User user= (User)session.getAttribute("user");
	List<String> cartList=(List<String>)  session.getAttribute("cart");
	List<String> historylist = (List<String>) session.getAttribute("history");
	List<String> subjects= (List<String>)session.getAttribute("subjects");
%>
<p  class="focus" style="position: absolute; left: 5%; top: 050px; width: 137px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName()+"  |  " %>  <a  style= "width: 50px" href="#"> Log out</a> </p> 
<p  class="focus" style="text-decoration:none; position: absolute;left:90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/cart.jsp" >Todo</a> </p>

<p  class="focus" style="position: absolute; left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done</a> </p>
<a id="menuitem" href="#" style=" left: 30%;opacity:.9">Home Page</a>
<a id="menuitem" href="StudentData/upload.jsp" style=" left: 50%;">Upload</a>
<a id="menuitem" href="# " style="left: 70%;">About</a>
        <p class="tabBar"></p>
        	<div id="headLine"> My Cources</div>
    <div style="position: absolute; left: 50%; top: 308px;">
<table  id="filestable" style="border:none"  width="200px" border="1">
<% 

	String subjectUrl="\""+user.getUniversity()+"/"
						+user.getTrend()+"/"+user.getYear()+"/";
	String getfile="/get_files=true";
	for(String subject : subjects)
	{
		out.write("<tr style='border:none'>"
			    +"<td ><a href="+subjectUrl+subject+getfile+"\""+">"+subject+"</a></td> </tr>");
	}
	%>
	</table>
	</div>
</body>

</html>
