<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal account</title>
		<link rel="stylesheet" href="templated-binary/assets/css/main.css" />
</head>
<body>
<div>
  <div>
    
    <form action="ViewReaderSubscriptionsServlet">
      <input type=submit value="View my subscriptions">
    </form>
    
    <form action="ViewAvailableSubscriptionsServlet">
      <input type=submit value="View available subscriptions">
    </form>
    
    <form action="ViewPeriodicalsServlet">
      <input type=submit value="View periodicals">
    </form>
    
    <form action="SubscribePeriodicalServlet">
      <input type=submit value="Subscribe periodical">
    </form>
    
  </div>
</div>
</body>
</html>