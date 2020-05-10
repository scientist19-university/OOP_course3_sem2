<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register new account</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

<form class="register-form" action="RegisterServlet">
      <input type="text" placeholder="login" name="loginReg"/>
      <input type="text" placeholder="first name" name="firstName"/>
      <input type="text" placeholder="last name" name="lastName"/>
      <input type="text" placeholder="birth date (yyyy-mm-dd)" name="birthDate"/>
      <input type="text" placeholder="male/female" name="sex"/>
      <input type="password" placeholder="password" name="passwordReg"/>
      <button>create</button>
      <p class="message">Already registered? <a href="index.html">Sign In</a></p>
    </form>
</body>
</html>