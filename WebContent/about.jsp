<!doctype html>
<%@page import="java.util.List"%>
<%@page import="il.ac.shenkar.studentdata.*" %>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="לימודים/שנה ג/java ee/StudentData/WebContent/css/style.css">
<%
	User user= (User)session.getAttribute("user");
	List<String> cartList=(List<String>)  session.getAttribute("cart");
	List<String> historylist = (List<String>) session.getAttribute("history");
	List<String> userFilesList= (List<String>)session.getAttribute("userFiles");
%>
<p  class="focus" style="position: absolute; left: 34px; top: 050px; width: 137px; height: 34px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;">Hello <%=" "+user.getUserName() %>|<a href="#"> Log out</a> </p> </nav>
<p  class="focus" style="text-decoration:none; position: absolute; left: 883px; top: 050px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#" >Todo</a> </p>
<p  class="focus" style="position: absolute; left: 883px; top: 77px;color: #4889C2;
	font-weight: bold;
	text-decoration: none;
	opacity: .9;
	-moz-transition: all .4s;"><a href="#">Done</a> </p>
<a id="menuitem" href="#" style=" left: 338px; ">Home Page</a>
<a id="menuitem" href="#" style=" left: 538px;">Upload</a>
<a id="menuitem" href="# " style="left: 704px; opacity:.9">About</a>
        <p class="tabBar"></p>
        
    <div style="position: absolute; left: 471px; top: 308px;">

<table  id="filestable" style="border:none"  width="200" border="1">
  <tr style="border:none">
    <td ><a href="#">mxklasmxas</a></td>
  </tr>
    <tr>
    <td><a href="#">mxklasmxas</a></td>
    <td><a href="#">Todo</a></td>
  </tr>
    <tr>
    <td><a href="#">mxklasmxas</a></td>
    <td><a href="#">Todo</a></td>
  </tr>
    <tr>
    <td><a href="#">mxklasmxas</a></td>
    <td><a href="#">Todo</a></td>
  </tr>

</table>
</div>
</body>

</html>
