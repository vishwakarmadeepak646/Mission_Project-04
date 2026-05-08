<%@page import="in.co.rays.proj4.Controller.ORSView"%>
<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="/ORS_Project4/js/checkbox.js"></script>
<script src="/ORS_Project4/js/datepicker.js"></script>
</head>
<body>
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
	<a href="<%=ORSView.ROLE_CTL%>">Add Role</a> |
	<a href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> |
	<a href="<%=ORSView.USER_CTL%>">Add User</a> |
	<a href="<%=ORSView.USER_LIST_CTL%>">User List</a> |
	<a href="<%=ORSView.COLLEGE_CTL%>">Add College</a> |
	<a href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> |

	<a href="<%=ORSView.LOGIN_CTL + "?operation=Logout"%>"><b>Logout</b></a>

	<%
	} else {
	%>
	<h3>Hi, Guest</h3>
	<a href="WelcomeCtl"><b>Welcome</b></a> |
	<a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>
	<%
	}
	%>
	<hr>
</body>
</html>