<!doctype html>
<!--  %@page import="com.apple.jobjc.JObjCRuntime.Width"%> -->
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
<p  class="focus" style="position: absolute; left: 5%; top: 050px; width: 200px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName()+"  |  " %>  <a  style= "width: 50px" href="StudentData/logout=true"> Log out</a> </p> 
<p  class="focus" style="text-decoration:none; position: absolute;left:90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#" >Todo-<%="   "+todoNum %> </a> </p>

<p  class="focus" style="position: absolute; left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/history.jsp">Done- <%="   "+doneNum %> </a></p>

	<menu:MenuTag menuItem1="Home Page" menuItem2="Upload " menuItem3="About" menuItem1Link="StudentData/homePage.jsp" menuItem2Link="StudentData/upload.jsp" menuItem3Link="#"/>
        	<headLineTag:HeadLinePage headLine="My TODO List"/>
 <div style="margin-left:335px;margin-right:auto;margin-top:280px; top: 260px;">

 <%
  if(cartList==null||cartList.size()==0)
  {
	  out.write("<p style=\"width:300px;position:absolute;left:40%;font-size:22px\">");
	  out.write("Hooray!!! No more TODO</br></br>");		 
	  out.write("You can see more files <a 	style=\"text-decoration: none\" href =\"StudentData/homePage.jsp\">here...</a>");
	  out.write("</p>");
  }
  else
  {
	  out.write("<a style=\"margin-left: 295px;\" href=\"StudentData/clear_cart=true\">Remove all</a> <br><br>");
	  	out.write("<table id=\"filestable\">");
		String subjectUrl="http://localhost:8080/StudentData/StudentData/";
		String removeActionString="/remove_from_cart=true";
		for(String item : cartList)
		{
			File file=new File(item);
			String itemName=file.getName();
			out.write("<tr>"
				    +"<td ><a href=\""+subjectUrl+item+"\"" +">"+itemName+"</a></td> ");
			out.write("<td ><a href=\""+subjectUrl+item +"/download\">"+"Download"+"</a></td>");
			out.write("<td ><a href=\""+subjectUrl+item +removeActionString+"\""+">"+"Done"+"</a></td> </tr>");
			
		}
		out.write("</table>");
  }
  %>
</div>
</body>

</html>
