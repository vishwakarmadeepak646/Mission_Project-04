<%@page import="in.co.rays.proj4.Controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Module Page</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />

</head>
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
	
	<a href="<%=ORSView.MEETING_CTL%>">Add Meeting</a>
	<b>|</b>
	
	<a href="<%=ORSView.MEETING_LIST_CTL%>">List Meeting</a>
	<b>|</b>
	<hr>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<h1 align="center">
		<font size="10px" color="navy">Daily Module page</font>
	</h1>
	

	<%@include file="Footer.jsp"%>
</body>
</html>