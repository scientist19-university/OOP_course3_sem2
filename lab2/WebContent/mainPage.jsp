<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal account</title>
		<link rel="stylesheet" href="templated-binary/assets/css/main.css" />
		<link rel="stylesheet" href="adminStyle.css">
</head>
<body>
<div class="form">
		<h1>Main user menu</h1>
		
		<table>
			<tr>
				<td>
	    		<form action="ViewReaderSubscriptionsServlet">
      <input type=submit value="View my subscriptions">
    </form>
	    		</td>
				<td>
	    		<form action="ViewAvailableSubscriptionsServlet">
      <input type=submit value="View available subscriptions">
    </form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="ViewPeriodicalsServlet">
      <input type=submit value="View periodicals">
    </form>
	    		</td>
	    		<td>
	    		<form action="SubscribePeriodicalServlet">
      <input type=submit value="Subscribe periodical">
    </form>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td>
	    		<form action="AddPreferenceServlet">
      <input type=submit value="Add preferences">
    </form>
	    		</td>
	    		<td>
	    		<form action="RemovePreferenceServlet">
      <input type=submit value="Remove preferences">
    </form>
	    		</td>
	    	</tr>
    	</table>
	</div>

</body>
</html>