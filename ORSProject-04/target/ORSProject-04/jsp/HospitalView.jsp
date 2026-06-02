<%@page import="in.co.rays.proj4.Controller.HospitalCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Hospital Record</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.HOSPITAL_CTL%>" method="post">
        <%@ include file="Header2.jsp"%>
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.HospitalBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">
                <% if (bean != null && bean.getId() > 0) { %>Update<% } else { %>Add<% } %> Record
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
                    <th align="left">Patient ID<span style="color: red">*</span></th>
                    <td><input type="text" name="patientId" placeholder="Enter Patient ID"
                        value="<%=(bean.getPatientId() == 0) ? "" : bean.getPatientId()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("patientId", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Patient Name<span style="color: red">*</span></th>
                    <td><input type="text" name="patientName" placeholder="Enter Patient Name"
                        value="<%=DataUtility.getStringData(bean.getPatientName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("patientName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Doctor Name<span style="color: red">*</span></th>
                    <td><input type="text" name="doctorName" placeholder="Enter Doctor Name"
                        value="<%=DataUtility.getStringData(bean.getDoctorName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("doctorName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Disease<span style="color: red">*</span></th>
                    <td><input type="text" name="disease" placeholder="Enter Disease"
                        value="<%=DataUtility.getStringData(bean.getDisease())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("disease", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Room Number<span style="color: red">*</span></th>
                    <td><input type="text" name="roomNumber" placeholder="Enter Room Number"
                        value="<%=(bean.getRoomNumber() == 0) ? "" : bean.getRoomNumber()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("roomNumber", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <% if (bean != null && bean.getId() > 0) { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=HospitalCtl.OP_UPDATE%>"> 
                        <input type="submit" name="operation" value="<%=HospitalCtl.OP_CANCEL%>">
                    </td>
                    <% } else { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=HospitalCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation" value="<%=HospitalCtl.OP_RESET%>">
                    </td>
                    <% } %>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>