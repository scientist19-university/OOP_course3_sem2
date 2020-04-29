<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subscribe</title>
		<link rel="stylesheet" href="templated-binary/assets/css/main.css" />
</head>
<body>

	<form action="SubscribePeriodicalServlet" method="post">
   		<p>
   			<select size="3" multiple name="subscriptions">
			    <option disabled>Select option</option>
			    <option value="opt1">option1</option>
			    <option selected value="opt2">option2</option>
			    <option value="opt3">option3</option>
			    <option value="opt4">option4</option>
   			</select>
   		</p>
   		<p><input type="text" value="1" name="months"></p>
   		<p><input type="submit" value="Subscribe"></p>
  	</form>
	
</body>
</html>