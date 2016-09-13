<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EJB demo</title>
<link rel="stylesheet" type="text/css" href="myStyle.css">
</head>
<body>


	<%
		String action = request.getParameter("action");

		if (action != null && action.equals("submitted")) {

			request.getRequestDispatcher("/servlet").forward(request, response);

		}
	%>


	<br>
	<h2>EJB calculator - bean will return sum of 2 numbers</h2>
	<br>

	<form action="${pageContext.request.contextPath}/forms.jsp" method="get">

		<input type="hidden" name="action" value="submitted" /> Integer1 <input
			type="text" name="int1" value="" /> <br> Integer2 <input
			type="text" name="int2" value="" /><br>
		<br> <input type="submit" value="Sum it !" />

	</form>

</body>
</html>