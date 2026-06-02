<%@page import="in.co.rays.proj4.Controller.LibraryCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16x16" />
</head>
<body>
    <form action="<%=ORSView.LIBRARY_CTL%>" method="post">
        <%@ include file="Header2.jsp"%>
        <jsp:useBean id="bean" class="in.co.rays.proj4.bean.LibraryBean" scope="request"></jsp:useBean>

        <div align="center">
            <h1 align="center" style="margin-bottom: -15; color: navy">
                <% if (bean != null && bean.getId() > 0) { %>Update<% } else { %>Add<% } %> Book
            </h1>

            <div style="height: 15px; margin-bottom: 12px">
                <H3 align="center"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></H3>
                <H3 align="center"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></H3>
            </div>

            <input type="hidden" name="id" value="<%=bean.getId()%>"> 

            <table>
                <tr>
                    <th align="left">Book Name<span style="color: red">*</span></th>
                    <td><input type="text" name="bookName" placeholder="Enter Book Name"
                        value="<%=DataUtility.getStringData(bean.getBookName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("bookName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Author Name<span style="color: red">*</span></th>
                    <td><input type="text" name="authorName" placeholder="Enter Author Name"
                        value="<%=DataUtility.getStringData(bean.getAuthorName())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("authorName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Issue Date<span style="color: red">*</span></th>
                    <td><input type="text" id="udate" name="issueDate" placeholder="Select Issue Date"
                        value="<%=DataUtility.getDateString(bean.getIssueDate())%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("issueDate", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Price<span style="color: red">*</span></th>
                    <td><input type="text" name="price" placeholder="Enter Price"
                        value="<%=(bean.getPrice() == 0.0) ? "" : bean.getPrice()%>"></td>
                    <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("price", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <% if (bean != null && bean.getId() > 0) { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=LibraryCtl.OP_UPDATE%>"> 
                        <input type="submit" name="operation" value="<%=LibraryCtl.OP_CANCEL%>">
                    </td>
                    <% } else { %>
                    <td align="left" colspan="2">
                        <input type="submit" name="operation" value="<%=LibraryCtl.OP_SAVE%>"> 
                        <input type="submit" name="operation" value="<%=LibraryCtl.OP_RESET%>">
                    </td>
                    <% } %>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>