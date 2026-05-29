<%@page import="in.co.rays.proj4.Controller.InsuranceCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Insurance Record</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.INSURANCE_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.InsuranceBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">
                <% if (bean != null && bean.getId() > 0) { %>Update<% } else { %>Add<% } %> Insurance Record
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
                    <th align="left">Customer Name<span style="color: red">*</span></th>
                    <td><input type="text" name="customerName" placeholder="Enter Customer Name"
                        value="<%=DataUtility.getStringData(bean.getCustomerName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("customerName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Policy Type<span style="color: red">*</span></th>
                    <td><input type="text" name="policyType" placeholder="Enter Policy Type"
                        value="<%=DataUtility.getStringData(bean.getPolicyType())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("policyType", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Premium Amount<span style="color: red">*</span></th>
                    <td><input type="text" name="premiumAmount" placeholder="Enter Premium Amount"
                        value="<%=(bean.getPremiumAmount() == 0) ? "" : bean.getPremiumAmount()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("premiumAmount", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Claim Status<span style="color: red">*</span></th>
                    <td><input type="text" name="claimStatus" placeholder="Enter Claim Status"
                        value="<%=DataUtility.getStringData(bean.getClaimStatus())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("claimStatus", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <% if (bean != null && bean.getId() > 0) { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=InsuranceCtl.OP_UPDATE%>"> 
                        <input type="submit" name="operation" value="<%=InsuranceCtl.OP_CANCEL%>">
                    </td>
                    <% } else { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=InsuranceCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation" value="<%=InsuranceCtl.OP_RESET%>">
                    </td>
                    <% } %>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>