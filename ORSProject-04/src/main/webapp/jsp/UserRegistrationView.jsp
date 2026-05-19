<%@page import="in.co.rays.proj4.Controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.Controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
	
	
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<script src="/ORSProject-04/js/checkbox.js"></script>
<script src="/ORSProject-04/js/datepicker.js"></script>
</head>
<body>
	<%@include file="Header.jsp"%>
	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">


		<%-- <%UserBean bean = (UserBean) request.getAttribute("bean"); %> --%>

		<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>
		<div align="Center">
			<h1>User Registration</h1>

			<div style="height: 15px; margin-bottom: 12px">
				<h3 align="center">
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%>
					</font>

				</h3>
				<h3 align="center">
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>

				</h3>

			</div>

			<table>

				<tr>
					<th>First Name :</th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>

				<tr>
					<th>Last Name :</th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>

				</tr>
				<tr>
					<th>Login Id :</th>
					<td><input type="text" name="login"
						placeholder="Enter Login Id"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></td>

				</tr>
				<tr>
					<th>Password :</th>
					<td><input type="password" name="password"
						placeholder="Enter password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>

				<tr>
					<th>confirm Password :</th>
					<td><input type="password" name="confirmPassword"
						placeholder="Enter Confirm password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>

				<tr>
					<th>Date of birth :</th>
					<td><input type="text" id="udate"  name="dob" style="width: 98%"
						placeholder="Enter Date of birth"
						value="<%=DataUtility.getDateString(bean.getDob())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>
				</tr>

				<tr>
					<th>Gender :</th>
					<td><select style="width: 169px; text-align-last: center;"
						class='form-control' name='gender'>
							<option selected value="">------------Select------------</option>
							<option value="male">Male</option>
							<option value="female">Female</option>
					</select></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>

				<tr>
					<th>Mobile:</th>
					<td><input type="text" name="mobileNo"
						placeholder="Enter mobile number"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
						type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_RESET%>"></td>

				</tr>
			</table>
		</div>
	</form>

	<%@ include file="Footer.jsp"%>
</body>
</html>