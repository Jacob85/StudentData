<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Insert title here</title>
</head>
<body>


	<div align="center">
		<form action="StudentData/register" method="post">
			 Username: <input type="text" name="username" size="15" /><br />
 			 Password: <input type="password" name="password" size="15" /><br />
 			 email: <input type="email" name="email" /><br />
 			 University: <input type="text" name="uni" /> <br />
 			 year: <input type="text" name="year" /> <br />
 			 Subject: <input type="text" name="subject" /><br />
 			 Trend: <input type="text" name="trend" /><br />
			 	 <p><input type="submit" value="Register" /></p>		 
		</form>
	</div>
</body>
</html>