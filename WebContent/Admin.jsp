<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255">
<title>Admin Panel</title>
</head>
<body>

	<form method="post" action="StudentData/addUnis">

	<h2>Add unies</h2>

	<input type="text"  name="unis" size="80" class="text-field" placeholder="unies" />
    <input  type="submit" value="Add" class="button" /><br><br>
</form>

	<form method="post" action="StudentData/addTrends">

	<h2>Add Trends</h2>

	<input type="text"  name="trends" size="80"  class="text-field" placeholder="trends" />
    <input  type="submit" value="Add" class="button" /><br><br>
</form>

<form method="post" action="StudentData/addCourses">

	<h2>Add Courses</h2>

	<input type="text"  name="courses" size="80"  class="text-field" placeholder="courses" />
    <input  type="submit" value="Add" class="button" /><br><br>
</form>


</body>
</html>