<%@page import="in.co.rays.proj4.Controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<script src="/ORSProject-04/js/checkbox.js"></script>
<script src="/ORSProject-04/js/datepicker.js"></script>
</head>
<body>
<body>
	<!-- Logo -->
	<img src="<%=request.getContextPath()%>/img/customLogo.jpg"
		align="right" width="100" height="40" border="0">
	<%
	UserBean user = (UserBean) session.getAttribute("user");
	%>
	<%
	if (user != null) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>

	<%
	}
	%>

	<a href="<%=ORSView.WELCOME_CTL%>">Go Back</a>
	<b>|</b>

	<a href="<%=ORSView.MEETING_CTL%>">Add Meeting</a>
	<b>|</b>

	<a href="<%=ORSView.MEETING_LIST_CTL%>">List Meeting</a>
	<b>|</b>

	<a href="<%=ORSView.HOSPITAL_CTL%>">Add Hospital</a>
	<b>|</b>

	<a href="<%=ORSView.HOSPITAL_LIST_CTL%>">List Hospital</a>
	<b>|</b>

	<a href="<%=ORSView.INSURANCE_CTL%>">Add Insurance</a>
	<b>|</b>
	<a href="<%=ORSView.INSURANCE_LIST_CTL%>">List Insurance</a>
	<b>|</b>

	<a href="<%=ORSView.EVENT_MANAGEMENT_CTL%>">Add Event</a>
	<b>|</b>
	<a href="<%=ORSView.EVENT_MANAGEMENT_LIST_CTL%>">List Events</a>
	<b>|</b>
	<a href="<%=ORSView.LIBRARY_CTL%>">Add Library</a>
	<b>|</b>
	<a href="<%=ORSView.LIBRARY_LIST_CTL%>">List Library</a>

	<b>|</b>
	<a href="<%=ORSView.EMI_CTL%>">Add EMI</a>
	<b>|</b>
	<a href="<%=ORSView.EMI_LIST_CTL%>">List EMI</a>

	<b>|</b>
	<a href="<%=ORSView.ATM_CTL%>">Add ATM</a>
	<b>|</b>
	<a href="<%=ORSView.ATM_LIST_CTL%>">List ATM</a>
	<b>|</b>

	<hr>


</body>
</html>