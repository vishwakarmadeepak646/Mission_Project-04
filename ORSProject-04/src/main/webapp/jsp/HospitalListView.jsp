<%@page import="in.co.rays.proj4.Controller.HospitalListCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.bean.HospitalBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital List</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/checkbox.js"></script>
</head>
<body>
    <%@include file="Header2.jsp"%>
    <jsp:useBean id="bean" class="in.co.rays.proj4.bean.HospitalBean" scope="request"></jsp:useBean>

    <div align="center">
        <h1 align="center" style="margin-bottom: -15; color: navy;">Hospital Records List</h1>

        <div style="height: 15px; margin-bottom: 12px">
            <h3><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></h3>
            <h3><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h3>
        </div>

        <form action="<%=ORSView.HOSPITAL_LIST_CTL%>" method="post">
            <%
            int pageNo = ServletUtility.getPageNo(request);
            int pageSize = ServletUtility.getPageSize(request);
            int index = ((pageNo - 1) * pageSize) + 1;
            int nextListSize = DataUtility.getInt(request.getAttribute("nextListSize") != null ? request.getAttribute("nextListSize").toString() : "0");

            List<HospitalBean> list = (List<HospitalBean>) ServletUtility.getList(request);
            if (list.size() != 0) {
                Iterator<HospitalBean> it = list.iterator();
            %>

            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">

            <table style="width: 100%">
                <tr>
                    <td align="center">
                        <label><b>Patient Name :</b></label> 
                        <input type="text" name="patientName" placeholder="Enter Patient Name"
                        value="<%=ServletUtility.getParameter("patientName", request)%>">&emsp;

                        <label><b>Doctor Name :</b></label> 
                        <input type="text" name="doctorName" placeholder="Enter Doctor Name"
                        value="<%=ServletUtility.getParameter("doctorName", request)%>">&emsp;

                        <input type="submit" name="operation" value="<%=HospitalListCtl.OP_SEARCH%>"> &nbsp; 
                        <input type="submit" name="operation" value="<%=HospitalListCtl.OP_RESET%>">
                    </td>
                </tr>
            </table>
            <br>

            <table border="1" style="width: 100%; border: groove;">
                <tr style="background-color: #e1e6f1e3;">
                    <th width="5%"><input type="checkbox" id="selectall" /></th>
                    <th width="5%">S.No</th>
                    <th width="15%">Patient ID</th>
                    <th width="20%">Patient Name</th>
                    <th width="20%">Doctor Name</th>
                    <th width="15%">Disease</th>
                    <th width="10%">Room No.</th>
                    <th width="10%">Edit</th>
                </tr>

                <% while (it.hasNext()) { bean = it.next(); %>
                <tr>
                    <td style="text-align: center;"><input type="checkbox" class="case" name="ids" value="<%=bean.getId()%>"></td>
                    <td style="text-align: center;"><%=index++%></td>
                    <td style="text-align: center;"><%=bean.getPatientId()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getPatientName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getDoctorName()%></td>
                    <td style="text-align: center; text-transform: capitalize;"><%=bean.getDisease()%></td>
                    <td style="text-align: center;"><%=bean.getRoomNumber()%></td>
                    <td style="text-align: center;">
                        <a href="HospitalCtl?id=<%=bean.getId()%>">Edit</a>
                    </td>
                </tr>
                <% } %>
            </table>

            <table style="width: 100%">
                <tr>
                    <td style="width: 25%"><input type="submit" name="operation" value="<%=HospitalListCtl.OP_PREVIOUS%>" <%=pageNo > 1 ? "" : "disabled"%>></td>
                    <td align="center" style="width: 25%"><input type="submit" name="operation" value="<%=HospitalListCtl.OP_NEW%>"></td>
                    <td align="center" style="width: 25%"><input type="submit" name="operation" value="<%=HospitalListCtl.OP_DELETE%>"></td>
                    <td style="width: 25%" align="right"><input type="submit" name="operation" value="<%=HospitalListCtl.OP_NEXT%>" <%=nextListSize != 0 ? "" : "disabled"%>></td>
                </tr>
            </table>
            <% } else { %>
            <table>
                <tr>
                    <td align="right"><input type="submit" name="operation" value="<%=HospitalListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <% } %>
        </form>
    </div>
</body>
</html>