<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="http://localhost:8080/StudentData/css/style.css" />

</head>

<body>

<nav><a href="#" class="focus">Log In</a> | <a href="http://localhost:8080/StudentData/newuser.jsp">Register</a></nav>
<%
	String message= (String)request.getAttribute("message");
%>
<form method="post" action="StudentData/loginto">

	<h2>Log In</h2>

	<input type="text"  name="email"  class="text-field" placeholder="Email" />
    <input type="password" name="password" class="text-field" placeholder="Password" />

    <input  type="submit" value="Log In" class="button" /><br><br>
   Remember me <input type="checkbox" value="remember_me"/>
   <br><br>
   <%if(message!=null)
	   {
	   	out.write(message);
	   }
	   %>

</form>

</body>
</html>
