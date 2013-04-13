<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
    <%@ page import="il.ac.shenkar.studentdata.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>


<div align="left">
	<%	
	HttpSession currSession = request.getSession();
	User user =  (User)currSession.getAttribute("user");
	java.util.List filesList = (java.util.List)currSession.getAttribute("fiesList");
	
	%>
	<%= "Email: "+ user.getEmail() + "User Name: "+ user.getUserName() %><br />
	
	<%= "Files number: "+ filesList.size() %><br />

</div>

<div align="right">
<form action="StudentData/UploadImage" method="post" enctype="multipart/form-data" 
name="productForm" id="productForm"><br><br>
<table width="400px" align="center" border=0 style="background-color:ffeeff;">
<tr>
<td align="center" colspan=2 style="font-weight:bold;font-size:20pt;">
Image Details</td>
</tr>

<tr>
<td align="center" colspan=2>&nbsp;</td>
</tr>

<tr>
<td>Image Link: </td>
<td>
<input type="file" name="file" id="file">
<td>
</tr>

<tr>
<td></td>
<td><input type="submit" name="Submit" value="Submit"></td>
</tr>
<tr>
<td colspan="2">&nbsp;</td>
</tr>

</table>
</form>
</div>
</body>
</html>