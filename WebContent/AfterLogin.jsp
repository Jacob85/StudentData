<!doctype html>
<%@page import="com.sun.xml.internal.fastinfoset.util.PrefixArray"%>
<%@page import="java.util.List"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<%@ page errorPage="errorPage.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="headLineTag" %>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
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
<p  class="focus" style="position: absolute; left: 5%; top: 050px; width: 200px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName()+"  |  " %>  <a href="StudentData/logout=true"> Log out</a> </p> 
<p  class="focus" style="text-decoration:none; position: absolute;left:90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/cart.jsp" >Todo-<%="   "+todoNum %> </a> </p>

<p  class="focus" style="position: absolute; left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/history.jsp">Done- <%="   "+doneNum %> </a></p>
	<menu:MenuTag menuItem1="Home Page" menuItem2="Upload " menuItem3="About" menuItem1Link="#" menuItem2Link="StudentData/upload.jsp" menuItem3Link="#"/>
	<headLineTag:HeadLinePage headLine="My Cources"/>
	<form  style="height: 0px;box-shadow:none;padding: 0px;position: absolute;top: 60px;left: 530px" method="get" action="http://localhost:8080/StudentData/StudentData/search">
	<input  type="submit" value="Search"/>
	<input type="text"  name="substring" placeholder="Search" required/>

    </form>
    <div style="margin-left:570px; margin-top: 308px;">
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
