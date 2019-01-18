package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import aaronjenkins.web.WebObjectFactory;
import aaronjenkins.model.ServiceFactory;
import aaronjenkins.model.ServiceFacade;
import aaronjenkins.model.Meter;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.ws.rs.core.MediaType;
import aaronjenkins.model.ReplyMessage;
import aaronjenkins.web.rest.client.RestClientJerseyImpl;

public final class MeterInterface_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");


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



      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("    <title>Meter Interface</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<h1>Meter Interface</h1>\n");
      out.write("<form>\n");
      out.write("    <p>Select Meter: <select name=\"meterId\">\n");
      out.write("      ");

            for (int i = 0; i < meterList.size(); i++) {
        
      out.write("\n");
      out.write("        <option value=\"");
      out.print(meterList.get(i).getId());
      out.write("\">Meter ");
      out.print(meterList.get(i).getId());
      out.write("</option>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("\n");
      out.write("    </select></p>\n");
      out.write("    <p>Current Time: <input type=\"text\" name=\"current time\" value=\"\"> Enter Duration: <input type=\"text\" name=\"duration\" value=\"1\"></p>\n");
      out.write("\n");
      out.write("    <input type=\"hidden\" name=\"action\" value=\"getPrice\">\n");
      out.write("    <input type=\"submit\" value=\"Get Price\">\n");
      out.write("</form>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
