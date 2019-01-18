<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 16/01/19
  Time: 19:34
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="aaronjenkins.web.WebObjectFactory"%>
<%@page import="aaronjenkins.model.ServiceFactory"%>
<%@page import="aaronjenkins.model.ServiceFacade"%>
<%@page import="aaronjenkins.model.Meter"%>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="javax.ws.rs.core.MediaType" %>
<%@ page import="aaronjenkins.model.ReplyMessage" %>
<%@ page import="aaronjenkins.web.rest.client.RestClientJerseyImpl" %>

<%

/*
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
    String durationIdReq = (String) request.getParameter("duration");

    List<Meter> meterList = serviceFacade.retrieveAllMeters();


    if ("getPrice".equals(action)) {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);

    }
*/

    // get request values
    String action = (String) request.getParameter("action");
    String meterIdReq = (String) request.getParameter("meterId");
    String durationIdReq = (String) request.getParameter("duration");
    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;


    RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

    // try to retreive an unknown meter
    ReplyMessage replyMessage = restClient.retrieveEntity(Integer.SIZE);


    List<Meter> meterList = replyMessage.getMeterList().getMeters();

    if ("getPrice".equals(action)) {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String formattedDate=dateFormat.format(date);
    }


%>



<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>Meter Interface</title>
</head>
<body>
<h1>Meter Interface</h1>
<form>
    <p>Select Meter: <select name="meterId">
      <%
            for (int i = 0; i < meterList.size(); i++) {
        %>
        <option value="<%=meterList.get(i).getId()%>">Meter <%=meterList.get(i).getId()%></option>
        <%}%>

    </select></p>
    <p>Current Time: <input type="text" name="current time" value=""> Enter Duration: <input type="text" name="duration" value="1"></p>

    <input type="hidden" name="action" value="getPrice">
    <input type="submit" value="Get Price">
</form>
</body>
</html>
