<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 15/01/19
  Time: 23:27
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
    String action = (String) request.getParameter("action");
    String meterIdReq = (String) request.getParameter("meterId");
    String iIdReq = (String) request.getParameter("i");
    String rateIdReq = (String) request.getParameter("rate");
    String errorMessage = "";


    Integer meterId = Integer.valueOf(meterIdReq);
    Integer i = Integer.valueOf(iIdReq);
    Meter meter = serviceFacade.retrieveMeter(meterId);
    List<Schedule> schedules = meter.getScheduleList().getSchedules();

    if ("updateRate".equals(action)) {
        schedules.get(i).setRate(Double.valueOf(rateIdReq));
        serviceFacade.updateMeter(meter);
        String url = "http://localhost:8680/ListMeters.jsp";
        response.sendRedirect(url);
    }
%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Modify Schedule</title>
</head>
<body>
    <h1>Modify Schedule</h1>
    <form>
<table>
    <tr>
        <th>
            Start Time
        </th>
        <th>
            Current Rate
        </th>
        <th>
            New Rate
        </th>
        <th>

        </th>
    </tr>
    <tr>

        <td><%=schedules.get(i).getStartTime()%></td>
        <td><%=schedules.get(i).getRate()%></td>
        <td><input type="text" name="rate" value ="<%=schedules.get(i).getRate()%>"></td>
        <td>
            <input type="hidden" name="action" value="updateRate">
            <input type="hidden" name="meterId" value="<%=meterId%>">
            <input type="hidden" name="i" value="<%=i%>">
            <input type="submit" value="update Rate">
        </td>
    </tr>

</table>
    </form>
</body>
</html>
