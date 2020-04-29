<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add subscription</title>
</head>
<body>

	<form action="AdminAddSubscriptionsServlet">
        <input type="text" placeholder="Periodical name" name="periodical"/>
        <input type="text" placeholder="Type" name="type"/>
        <input type="number" placeholder="Cost per month" name="cost"/>
		<input type=submit value="Add subscription">
	</form>
</body>
</html>