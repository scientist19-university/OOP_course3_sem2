<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add theme</title>
<link rel="stylesheet" href="templated-binary/assets/css/main.css" />
<link rel="stylesheet" href="adminStyle.css" />
</head>
<body>
<div class="form">
	<form action="AdminAddThemeServlet">
        <input type="text" placeholder="Print theme name" name="name"/><br>
		<input type=submit value="Add theme">
	</form>
	</div>
</body>
</html>