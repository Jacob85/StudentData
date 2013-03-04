<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Please login</title>
</head>
<body>
<br><br><br><br><br><br>
  <div align="center">
<form method="get" action="StudentData/Exam.pdf">
  Username: <input type="text" name="username" size="15" /><br />
  Password: <input type="password" name="passwort" size="15" /><br />

    <p><input type="submit" value="Login" /></p>
    <p><input type="checkbox" value="remember_me" />Remember Me</p>
    <a href="StudentData/testpic.jpg">Click here to download the test Pic</a>
    <a href="StudentData/Exam.pdf">Click here to download the test Pdf file</a>
    
    
    <p><input type="button" value="NewUser"/> </p>
  </div>
</form>


</body>
</html>