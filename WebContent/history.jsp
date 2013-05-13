<!doctype html>
<%@page import="java.io.File"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.util.List"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<%@page import="com.sun.xml.internal.fastinfoset.util.PrefixArray"%>
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
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName()+"  |  " %>  <a  style= "width: 50px" href="StudentData/logout=true"> Log out</a> </p> 
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

	<menu:MenuTag menuItem1="Home Page" menuItem2="Upload " menuItem3="About" menuItem1Link="StudentData/homePage.jsp" menuItem2Link="StudentData/upload.jsp" menuItem3Link="#"/>
        	<headLineTag:HeadLinePage headLine="My History"/>
 <div style="margin-left:335px;margin-right:auto;margin-top:280px; top: 260px;">
<table  id="filestable" >
 
 <%
  if(historylist==null||historylist.size()==0)
  {
	  out.write(" <tr>");
	  out.write("<td>");
	  out.write("You haven't done anything!!! Start working!!!");		  
	  out.write("</td>");
	  out.write("</tr>");
	  out.write(" <tr>");
	  out.write("<td>");
	  out.write("You can see the files <a href =\"StudentData/homePage.jsp\">here...</a>");
	  out.write("</td>");
	  out.write("</tr>");
  }
  else
  {
	  out.write("<a style=\"margin-left: 295px;\" href=\"StudentData/clear_history=true\">Remove all</a> <br><br>");
		String subjectUrl="http://localhost:8080/StudentData/StudentData/";
		String removeActionString="/remove_from_historyt=true";
		for(String item : historylist)
		{
			File file=new File(item);
			String itemName=file.getName();
			out.write("<tr style='border:none'>"
				    +"<td ><a href=\""+subjectUrl+item+"\"" +">"+itemName+"</a></td> ");
			out.write("<td ><a href=\""+subjectUrl+item +"/download\">"+"Download"+"</a></td>");
			out.write("<td ><a href=\""+subjectUrl+item+removeActionString+"\""+">"+"Remove"+"</a></td> </tr>");
		}
  }
  %>
</table>
</div>
</body>

</html>
