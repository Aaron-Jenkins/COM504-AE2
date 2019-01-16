<%-- 
    Document   : ListEntities
    Created on : Nov 30, 2018, 11:17:02 PM
    Author     : cgallen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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
    if ("deleteMeter".equals(action)) {
        try {
            Integer meterId = Integer.parseInt(meterIdReq);
            serviceFacade.deleteMeter(meterId);
        } catch (Exception e) {
            errorMessage = "problem deleting Meter " + e.getMessage();
        }
    } else if ("modifyMeter".equals(action)) {
        try {
            Integer meterId = Integer.parseInt(meterIdReq);
            Meter meterTemplate = new Meter();
            meterTemplate.setId(meterId);
            meterTemplate.setLocation(meterField_AReq);
            //meterTemplate.setScheduleList(meterField_BReq);
            Meter meter = serviceFacade.updateMeter(meterTemplate);
            if (meter == null) {
                errorMessage = "problem modifying Meter. could not find meterId " + meterId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Meter " + e.getMessage();
        }
    } else if ("createMeter".equals(action)) {
        try {
            Meter meterTemplate = new Meter();
            meterTemplate.setLocation(meterField_AReq);
            //meterTemplate.setScheduleList(meterField_BReq);
            Meter meter = serviceFacade.createMeter(meterTemplate);
            if (meter == null) {
                errorMessage = "problem creating Meter. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Meter " + e.getMessage();
        }
    } 

    List<Meter> meterList = serviceFacade.retrieveAllMeters();

%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <title>Meter List</title>
    </head>
    <body>
        <!-- print error message if there is one -->
        <div style="color:red;"><%=errorMessage%></div>
        <h1>Meter List</h1>
        <table>
            <tr>
                <th>id</th>
                <th>location</th>
                <th>scheduleList</th>
                <th></th>
            </tr>
            <%  for (Meter meter : meterList) {
            %>
            <tr>
                <td><%=meter.getId()%></td>
                <td><%=meter.getLocation()%></td>
                <td>
                    <form action="ListSchedule.jsp">
                        <input type="hidden" name="action" value="listSchedule">
                        <input type="hidden" name="meterId" value="<%=meter.getId()%>">
                        <input type="submit" value="List Schedule">
                    </form>
                </td>
                <td>
                    <form action="AddOrModifyMeter.jsp">
                        <input type="hidden" name="action" value="modifyMeter">
                        <input type="hidden" name="meterId" value="<%=meter.getId()%>">
                        <input type="submit" value="Modify Meter">
                    </form>
                    <form action="ListMeters.jsp">
                        <input type="hidden" name="action" value="deleteMeter">
                        <input type="hidden" name="meterId" value="<%=meter.getId()%>">
                        <input type="submit" value="Delete Meter">
                    </form>
                </td>
            </tr>
            <% }%>

        </table> 
        <BR>
        <form action="AddOrModifyMeter.jsp">
            <input type="hidden" name="action" value="createMeter">
            <input type="submit" value="Create Meter">
        </form>
    </body>
</html>
