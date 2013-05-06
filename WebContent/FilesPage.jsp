<!doctype html>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
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
	List<FileRecord> userFilesList= (List<FileRecord>)session.getAttribute("userFiles");
%>
<p  class="focus" style="position: absolute; left: 5%; top: 050px; width: 137px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName() %>|<a href="#"> Log out</a> </p> 
<p  class="focus" style="text-decoration:none; position: absolute; left: 90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/cart.jsp" >Todo</a> </p>
<p  class="focus" style="position: absolute; left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done</a> </p>
<a id="menuitem" href="StudentData/homePage.jsp" style=" left: 30%;">Home Page</a>
<a id="menuitem" href="StudentData/upload.jsp" style=" left: 50%;">Upload</a>
<a id="menuitem" href="# " style="left: 70%;">About</a>
<div id="headLine">
	<%
	FileRecord f=userFilesList.get(0);
	out.write(f.getSubject()+" Files");
	%>
</div>
        <p class="tabBar"></p>
        
    <div style="position: absolute; left: 40%; top: 280px;">

<table  id="filestable" style="border:none;left: 50%;padding: 10px" border="1">
 
  <%
  for(FileRecord file:userFilesList)
  {
	  String fName=new File(file.getPath()).getName();
	  out.write(" <tr style=\"border:none\">");
	  out.write("<td>");
	  out.write(fName);
	  out.write("</td>");
	  out.write("<td>");
	  out.write(file.getDescription());
	  out.write("</td>");
	  out.write("<td>");
	  out.write("<a href=\""+file.getPath()+"\""+">TODO</a>");
	  out.write("</td>");
	  out.write("<td>");
	  out.write("<a href=\""+file.getPath()+"\""+">Download</a>");
	  out.write("</td>");
	  out.write("</tr>");
  }
  %>
</table>
</div>

</body>

</html>
