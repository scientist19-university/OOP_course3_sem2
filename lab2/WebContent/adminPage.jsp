<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
<link rel="stylesheet" href="templated-binary/assets/css/main.css" />
<link rel="stylesheet" href="adminStyle.css" />
</head>
<body>
	<div class="form">
		<h1>Main administration menu</h1>
		
		<table>
			<tr>
				<td>
	    		<form action="ViewPeriodicalsServlet">
	      			<input type=submit value="View periodicals">
	    		</form>
	    		</td>
				<td>
	    		<form action="ViewAvailableSubscriptionsServlet">
	      			<input type=submit value="View subscriptions">
	    		</form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="addPeriodical.jsp">
	      			<input type=submit value="Add periodical">
	    		</form>
	    		</td>
	    		<td>
	    		<form action="addSubscription.jsp">
	      			<input type=submit value="Add subscription">
	    		</form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="addTheme.jsp">
	      			<input type=submit value="Add theme">
	    		</form>
	    		</td>
	    		<td>
	    		<form action="AdminAddThemeToPeriodicalServlet">
	      			<input type=submit value="Add theme to periodical">
	    		</form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="ViewStatisticsServlet">
	      			<input type=submit value="View statistics">
	    		</form>
	    		</td>
	    		<td>
	    		<form action="ViewThemesServlet">
	      			<input type=submit value="View themes">
	    		</form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="ViewThemesPeriodicalsServlet">
	      			<input type=submit value="View periodicals' themes">
	    		</form>
	    		</td>
	    		<td>
	    		<form action="ViewThemesReadersServlet">
	      			<input type=submit value="View readers' preferences">
	    		</form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="ViewReadersServlet">
	      			<input type=submit value="View readers">
	    		</form>
	    		</td>
	    		<td>
	    	</tr>
    	</table>
	</div>
</body>
</html>