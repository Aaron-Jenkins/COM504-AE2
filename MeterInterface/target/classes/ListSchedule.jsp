<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 15/01/19
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aaronjenkins.web.WebObjectFactory"%>
<%@ page import="aaronjenkins.model.*" %>
<%@ page import="java.util.List" %>


<%

    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String meterIdReq = (String) request.getParameter("meterId");


    Integer meterId = Integer.parseInt(meterIdReq);
    Meter meter = serviceFacade.retrieveMeter(meterId);
    List<Schedule> schedules = meter.getScheduleList().getSchedules();



/*    Schedule schedule1 = new Schedule();
    schedule1.setStartTime("13:00");
    schedule1.setRate(5.00);
    meter.addSchedule(schedule1);

    Schedule schedule2 = new Schedule();
    schedule2.setStartTime("13:30");
    schedule2.setRate(2.50);
    meter.addSchedule(schedule2);*/

%>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Schedule <%=meter.getId()%></title>
</head>
<body>
<h1>Schedule List  : Meter <%=meter.getId()%></h1>
<table>
    <tr>
        <th>start time </th>
        <th>rate</th>
        <th></th>
    </tr>
    <%  for (int i = 0; i < schedules.size(); i++) { %>
        <tr>
            <td><%=schedules.get(i).getStartTime()%></td>
            <td><%=schedules.get(i).getRate()%></td>
            <td>
                <form action="ModifySchedule.jsp">
                    <input type="hidden" name="action" value="modifyMeter">
                    <input type="hidden" name="meterId" value="<%=meter.getId()%>">
                    <input type="hidden" name="i" value="<%=i%>">
                    <input type="submit" value="Modify Rate">
                </form>
            </td>
        </tr>
    <% }%>
</table>
</body>
</html>
