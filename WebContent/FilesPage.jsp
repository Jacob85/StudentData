<!doctype html>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@ page import="il.ac.shenkar.studentdata.*" %>
<%@ page errorPage="errorPage.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="menu" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="headLineTag" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css">
<script src="http://localhost:8080/StudentData/js/msg-box.js"></script>
</head>
<body>
<%
	int todoNum=0,donNum=0;
	String urlPrefix=(String)session.getAttribute("prefix");
	User user= (User)session.getAttribute("user");
	List<String> cartList=(List<String>)  session.getAttribute("cart");
	List<String> historylist = (List<String>) session.getAttribute("history");
	List<FileRecord> userFilesList= (List<FileRecord>)session.getAttribute("userFiles");
	List<FileRecord> courseFilesList= (List<FileRecord>)session.getAttribute("courseFiles");
	if(cartList!=null)
		todoNum=cartList.size();
	if(historylist!=null)
		donNum=historylist.size();
	
%>

<script type="text/javascript">

</script>
<p  style="position: absolute; left: 5%; top: 050px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName() %>|<a href="http://localhost:8080/StudentData/StudentData/logout=true"> Log out</a> </p> 
<p  style="text-decoration:none; position: absolute; left: 90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="StudentData/cart.jsp" >Todo-<%="   "+todoNum %></a>  </p>
<p  class="focus" style="position: absolute; left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done- <%="   "+donNum %></a></p>
	<menu:MenuTag menuItem1="Home Page" menuItem2="Upload " menuItem3="About" menuItem1Link="StudentData/homePage.jsp
	" menuItem2Link="StudentData/upload.jsp" menuItem3Link="#"/>
	
<div id="headLine">
	<%
	FileRecord f=courseFilesList.get(0);
	out.write(f.getSubject()+" Files");
	%>
</div>
        <p class="tabBar"></p>
        
    <div style="position: absolute; left: 35%; top: 280px;">

<table  id="filestable" style="border:none;left: 50%;padding: 10px" border="1">
 
  <%
  if(courseFilesList==null||courseFilesList.size()==0)
  {
	  out.write(" <tr style=\"border:none\">");
	  out.write("<td>");
	  out.write("Sory,No files for this course...");		  
	  out.write("</td>");
	  out.write("</tr>");
	  out.write(" <tr style=\"border:none\">");
	  out.write("<td>");
	  out.write("But you can add if you want");
	  out.write("<form name='f2' action='http://localhost:8080/StudentData/.jsp'>");
	  out.write("<input type='submit' name='button1' value='Login Page' class='button'>");
	  out.write("</form>");
	  out.write("</td>");
	  out.write("</tr>");
  }
  else
  {
	  for(FileRecord file:courseFilesList)
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
		  out.write("<a href=\""+urlPrefix+"/StudentData/"+file.getPath()+"/add_to_cart=true" +"\""+">TODO</a>");
		  out.write("</td>");
		  out.write("<td>");
		  out.write("<a href=\""+urlPrefix+"/StudentData/"+file.getPath()+"/download" +"\""+">Download</a>");
		  out.write("</td>");
		  out.write("</tr>");
	  }
  }
  %>
</table>
</div>
<%
String fileExistInTheCartMassage= (String)session.getAttribute("fileExistInTheCartMassage");
String addFileSuccsedMessage= (String)session.getAttribute("addFileSuccsedMessage");
if(fileExistInTheCartMassage!=null)
{
	out.write("<script type=\"text/javascript\">"+
			"alert(\""+fileExistInTheCartMassage+"\");"
	+"</script>");
	session.setAttribute("fileExistInTheCartMassage", null);
}
if(addFileSuccsedMessage!=null)
{
	out.write("<script type=\"text/javascript\">"+
			"alert(\""+addFileSuccsedMessage+"\");"
	+"</script>");
	session.setAttribute("addFileSuccsedMessage", null);
}
%>
</body>

</html>
