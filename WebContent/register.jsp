<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>

<link rel="stylesheet" href="css/style.css" />

</head>

<body>

<nav><a href="http://localhost:8080/StudentData/Login.jsp">Log In</a> | <a href="#" class="focus">Register</a></nav>

<form class="register" action="StudentData/register" method="post">

	<h2>Register</h2>

	<input type="text" name="username" class="text-field" placeholder="User name  " />
    <input type="password" name="password" class="text-field" placeholder="Password" />
    <input type="email" name="email" class="text-field" placeholder="E-mail" />
    <input type="text" name="uni" class="text-field" placeholder="University" />
    <select style="width: 245px; height: 50px" name="year" class="text-field"  id="Select1">
    <option class="text-field"  value="select">Select year</option>
    <option class="text-field"   value="a">Fist year</option>
    <option class="text-field"   value="b">Second year</option>
    <option class="text-field"  value="c">Third year</option>
    <option class="text-field"  value="d">Forth year</option>
</select>
    <input type="text" name="trend" class="text-field" placeholder="trend" />
    <input type="submit" value="Register Account" class="button" />

</form>


</body>
</html>
