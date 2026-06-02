<%@page import="in.co.rays.proj4.Controller.EventManagementCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Event</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.EVENT_MANAGEMENT_CTL%>" method="post">
        <%@ include file="Header2.jsp"%>
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.EventManagementBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">
                <% if (bean != null && bean.getId() > 0) { %>Update<% } else { %>Add<% } %> Event
            </h1>

            <div style="height: 15px; margin-bottom: 12px">
                <H3 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></H3>
                <H3 align="center"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></H3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> 

            <table>
                <tr>
                    <th align="left">Event Name<span style="color: red">*</span></th>
                    <td><input type="text" name="eventName" placeholder="Enter Event Name"
                        value="<%=DataUtility.getStringData(bean.getEventName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("eventName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Organizer Name<span style="color: red">*</span></th>
                    <td><input type="text" name="organizerName" placeholder="Enter Organizer Name"
                        value="<%=DataUtility.getStringData(bean.getOrganizerName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("organizerName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Venue<span style="color: red">*</span></th>
                    <td><input type="text" name="venue" placeholder="Enter Venue"
                        value="<%=DataUtility.getStringData(bean.getVenue())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("venue", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Budget<span style="color: red">*</span></th>
                    <td><input type="text" name="budget" placeholder="Enter Budget"
                        value="<%=(bean.getBudget() == 0.0) ? "" : bean.getBudget()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("budget", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <% if (bean != null && bean.getId() > 0) { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=EventManagementCtl.OP_UPDATE%>"> 
                        <input type="submit" name="operation" value="<%=EventManagementCtl.OP_CANCEL%>">
                    </td>
                    <% } else { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=EventManagementCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation" value="<%=EventManagementCtl.OP_RESET%>">
                    </td>
                    <% } %>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>