<!doctype html>
<%@page import="java.util.List"%>
<%@page import="il.ac.shenkar.studentdata.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css">
</head>
<body>

<p  class="focus" style="position: absolute; left: 5%; top: 050px; width: 137px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello |<a href="#"> Log out</a> </p>
<p  class="focus" style="text-decoration:none; position: absolute; left: 90%; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href=" http://localhost:8080/StudentData/StudentData/cart.jsp" >Todo</a> </p>
<p  class="focus" style="position: absolute;left: 90%; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done</a> </p>
	<div id="menues">
<a id="menuitem" href="http://localhost:8080/StudentData/StudentData/cart.jsp" style="left: 40%;"> Home Page </a>
<a id="menuitem" href="#" style=" left: 50%; opacity:.9">Upload</a>
<a id="menuitem" href="# " style="left: 60%;">About</a>
</div>
<p class="tabBar"></p>
        
       <div style="position: absolute; left: 42%; top: 101px;"> 
       <p class="focus" style="position: absolute; left: 106px; top: 149px; width: 65px; height: 21px;"><strong>Upload</strong></p>
        <form action="StudentData/uni/trend/a/course2/upload=true" method="post" enctype="multipart/form-data" 
name="productForm" id="productForm"><br><br>

<table>
<tr>
<td>
<input  type="file" name="file" id="file">
</td>
</tr>
<tr>
<td><input type="text"  name="email"  class="text-field" placeholder="Description" /></td>
</tr>
<tr>
<td><input class="button"  type="submit" name="Submit" value="Submit"></td>
</tr>
</table>
</form>
</div>
</body>

</html>
