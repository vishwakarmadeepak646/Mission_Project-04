<%@page import="in.co.rays.proj4.Controller.ATMCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add ATM</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.ATM_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.ATMBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">
                <% if (bean != null && bean.getId() > 0) { %>Update<% } else { %>Add<% } %> ATM Record
            </h1>

            <div style="height: 15px; margin-bottom: 12px">
                <H3 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></H3>
                <H3 align="center"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></H3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> 
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <th align="left">Bank Name<span style="color: red">*</span></th>
                    <td><input type="text" name="bankName" placeholder="Enter Bank Name"
                        value="<%=DataUtility.getStringData(bean.getBankName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("bankName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Location<span style="color: red">*</span></th>
                    <td><input type="text" name="location" placeholder="Enter Location"
                        value="<%=DataUtility.getStringData(bean.getLocation())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("location", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Cash Available<span style="color: red">*</span></th>
                    <td><input type="text" name="cashAvailable" placeholder="Enter Cash Available"
                        value="<%=(bean.getCashAvailable() == 0.0) ? "" : bean.getCashAvailable()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("cashAvailable", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Security Code<span style="color: red">*</span></th>
                    <td><input type="text" name="securityCode" placeholder="Enter Security Code"
                        value="<%=(bean.getSecurityCode() == 0) ? "" : bean.getSecurityCode()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("securityCode", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <% if (bean != null && bean.getId() > 0) { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=ATMCtl.OP_UPDATE%>"> 
                        <input type="submit" name="operation" value="<%=ATMCtl.OP_CANCEL%>">
                    </td>
                    <% } else { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=ATMCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation" value="<%=ATMCtl.OP_RESET%>">
                    </td>
                    <% } %>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>