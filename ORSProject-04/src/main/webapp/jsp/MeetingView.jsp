<%@page import="in.co.rays.proj4.Controller.MeetingCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Meeting</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.MEETING_CTL%>" method="post">

        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.MeetingBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">
                <%
                if (bean != null && bean.getId() > 0) {
                %>Update<%
                } else {
                %>Add<%
                }
                %>
                Meeting
            </h1>

            <div style="height: 15px; margin-bottom: 12px">
                <H3 align="center">
                    <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                    </font>
                </H3>

                <H3 align="center">
                    <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                    </font>
                </H3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> 
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>"> 
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

            <table>
                <tr>
                    <th align="left">Host Name<span style="color: red">*</span></th>
                    <td><input type="text" name="hostName" placeholder="Enter Host Name"
                        value="<%=DataUtility.getStringData(bean.getHostName())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("hostName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Platform<span style="color: red">*</span></th>
                    <td><input type="text" name="platform" placeholder="Enter Platform (e.g., Zoom, Meet)"
                        value="<%=DataUtility.getStringData(bean.getPlatform())%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("platform", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Duration (mins)<span style="color: red">*</span></th>
                    <td><input type="text" name="duration" placeholder="Enter Duration"
                        value="<%=(bean.getDuration() == 0) ? "" : bean.getDuration()%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Participants<span style="color: red">*</span></th>
                    <td><input type="text" name="participants" placeholder="Enter Participants Count"
                        value="<%=(bean.getParticipants() == 0) ? "" : bean.getParticipants()%>"></td>
                    <td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("participants", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th></th>
                    <%
                    if (bean != null && bean.getId() > 0) {
                    %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=MeetingCtl.OP_UPDATE%>"> 
                        <input type="submit" name="operation" value="<%=MeetingCtl.OP_CANCEL%>">
                    </td>
                    <%
                    } else {
                    %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=MeetingCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation" value="<%=MeetingCtl.OP_RESET%>">
                    </td>
                    <%
                    }
                    %>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>