<%-- 
    Document   : AddOrModifyMeter
    Created on : Nov 30, 2018, 11:17:38 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aaronjenkins.web.WebObjectFactory"%>
<%@page import="aaronjenkins.model.ServiceFactory"%>
<%@page import="aaronjenkins.model.ServiceFacade"%>
<%@page import="aaronjenkins.model.Meter"%>


<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String meterIdReq = (String) request.getParameter("meterId");
    String meterField_AReq = (String) request.getParameter("location");
    String meterField_BReq = (String) request.getParameter("scheduleList");
    String errorMessage = "";

    Meter meter = null;
    Integer meterId = null;

    if ("modifyMeter".equals(action)) {
        try {
            meterId = Integer.parseInt(meterIdReq);
            meter = serviceFacade.retrieveMeter(meterId);
        } catch (Exception e) {
            errorMessage = "problem finding meter " + e.getMessage();
        }
    } else if ("createMeter".equals(action)) {
        try {
            meter = new Meter();
        } catch (Exception e) {
            errorMessage = "problem finding meter " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Edit Meter</title>
    </head>
    <body>
        <% if ("createMeter".equals(action)) {
        %>
        <h1>Add New Meter</h1>
        <% } else {%>
        <h1>Modify Meter <%=meterId%></h1>
        <% }
            assert meter != null;%>
        <form action="ListMeters.jsp">
            <table>
                <tr>
                    <th>Field</th>
                    <th>Current Value</th>
                    <th>New Value</th>
                </tr>
                <tr>
                    <td>Meter Id</td>
                    <td><%=meter.getId()%></td>
                    <td></td>
                </tr>
                <tr>
                    <td>location</td>
                    <td><%=meter.getLocation()%></td>
                    <td><input type="text" name="location" value ="<%=meter.getLocation()%>"></td>
                </tr>
            </table> 
            <BR>
            <% if ("createMeter".equals(action)) {
            %>
            <input type="hidden" name="action" value="createMeter">
            <input type="hidden" name="meterId" value="<%=meterId%>">
            <input type="submit" value="Create New Meter">
            <% } else if ("modifyMeter".equals(action)) {
            %>
            <input type="hidden" name="action" value="modifyMeter">
            <input type="hidden" name="meterId" value="<%=meterId%>">
            <input type="submit" value="Modify Meter">
            <% }%>
        </form>
        <form action="ListMeters.jsp">
            <input type="submit" value="Cancel and Return">
        </form>
    </body>
</html>
