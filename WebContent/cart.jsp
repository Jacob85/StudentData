<!doctype html>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.util.List"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<%@page import="com.sun.xml.internal.fastinfoset.util.PrefixArray"%>
<%@ page errorPage="errorPage.jsp" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css">
</head>
<body>
<%
	int todoNum=0,doneNum=0;
	String urlPrefix=(String)session.getAttribute("prefix");
	User user= (User)session.getAttribute("user");
	List<String> cartList=(List<String>)  session.getAttribute("cart");
	List<String> historylist = (List<String>) session.getAttribute("history");
	List<String> subjects= (List<String>)session.getAttribute("subjects");
	if(cartList!=null)
		todoNum=cartList.size();
	if(historylist!=null)
		doneNum=historylist.size();
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
	-moz-transition: all .4s;"><a href="StudentData/cart.jsp" >Todo-<%="   "+todoNum %> </a> </p>

<p  class="focus" style="position: absolute; left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done- <%="   "+doneNum %> </a></p>
<a id="menuitem" href="#" style=" left: 30%;opacity:.9">Home Page</a>
<a id="menuitem" href="StudentData/upload.jsp" style=" left: 50%;">Upload</a>
<a id="menuitem" href="# " style="left: 70%;">About</a>
        <p class="tabBar"></p>
        	<div id="headLine"> My TODO List</div>
        	    <div style="position: absolute; left: 40%; top: 280px;">

<table  id="filestable" style="border:none;left: 50%;padding: 10px" border="1">
 
 <%
  if(cartList==null||cartList.size()==0)
  {
	  out.write(" <tr style=\"border:none\">");
	  out.write("<td>");
	  out.write("Hooray!!! No more TODO");		  
	  out.write("</td>");
	  out.write("</tr>");
	  out.write(" <tr style=\"border:none\">");
	  out.write("<td>");
	  out.write("You can see more file here...");
	  out.write("<form name='f2' action='AfterLogin.jsp'>");
	  out.write("<input type='submit' name='button1' value='Login Page' class='button'>");
	  out.write("</form>");
	  out.write("</td>");
	  out.write("</tr>");
  }
  else
  {
		String subjectUrl="http://localhost:8080/StudentData/StudentData/";
		String removeActionString="/remove_from_cart=true";
		for(String item : cartList)
		{
			File file=new File(item);
			String itemName=file.getName();
			out.write("<tr style='border:none'>"
				    +"<td ><a href=\""+subjectUrl+item+"\"" +">"+itemName+"</a></td> ");
			out.write("<td ><a href=\""+subjectUrl+item +"/download\">"+"Download"+"</a></td>");
			out.write("<td ><a href=\""+subjectUrl+item +removeActionString+"\""+">"+"Done"+"</a></td> </tr>");
			
		}
  }
  %>
</table>
</div>
</body>

</html>
