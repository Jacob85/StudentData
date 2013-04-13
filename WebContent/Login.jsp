

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Please login</title>
</head>
<body>
<br><br><br><br><br><br>
  <div align="center">
<form method="post" action="StudentData/loginto">
  Email: <input type="text" name="email" size="15" /><br />
  Password: <input type="password" name="password" size="15" /><br />

    <p><input type="submit" value="Login" /></p>
    <p><input type="checkbox" value="remember_me" />Remember Me</p>
    <a href="StudentData/testpic.jpg">Click here to download the test Pic</a>
    <a href="StudentData/Exam.pdf">Click here to download the test Pdf file</a>
    <a href="StudentData/IMG_0620.JPG">get the image from the server</a>
    
    <p><input type="button" value="submit" action="StudentData/newuser"/> </p>
  </div>
</form>

<form method="get" action="StudentData/newuser.jsp">

   <p><input type="submit" value="New User" /></p>
</form>

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

</body>
</html>