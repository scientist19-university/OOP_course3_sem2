<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<div class="login-page">
  <div class="form">
	<form class="login-form" action="AdminLoginServlet">
      <input type="text" placeholder="login" name="login"/>
      <input type="password" placeholder="admin index" name="adminIndex"/>
      <input type="password" placeholder="password" name="password"/>
      <input type=submit value="login">
    </form>
    </div>
    </div>
</body>
</html>