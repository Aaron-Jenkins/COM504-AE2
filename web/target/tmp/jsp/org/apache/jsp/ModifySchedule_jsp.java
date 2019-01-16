package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import aaronjenkins.web.WebObjectFactory;
import aaronjenkins.model.*;
import java.util.List;

public final class ModifySchedule_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("    <title>Modify Schedule</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1>Modify Schedule</h1>\n");
      out.write("    <form>\n");
      out.write("<table>\n");
      out.write("    <tr>\n");
      out.write("        <th>\n");
      out.write("            Start Time\n");
      out.write("        </th>\n");
      out.write("        <th>\n");
      out.write("            Current Rate\n");
      out.write("        </th>\n");
      out.write("        <th>\n");
      out.write("            New Rate\n");
      out.write("        </th>\n");
      out.write("        <th>\n");
      out.write("\n");
      out.write("        </th>\n");
      out.write("    </tr>\n");
      out.write("    <tr>\n");
      out.write("\n");
      out.write("        <td>");
      out.print(schedules.get(i).getStartTime());
      out.write("</td>\n");
      out.write("        <td>");
      out.print(schedules.get(i).getRate());
      out.write("</td>\n");
      out.write("        <td><input type=\"text\" name=\"rate\" value =\"");
      out.print(schedules.get(i).getRate());
      out.write("\"></td>\n");
      out.write("        <td>\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"updateRate\">\n");
      out.write("            <input type=\"hidden\" name=\"meterId\" value=\"");
      out.print(meterId);
      out.write("\">\n");
      out.write("            <input type=\"hidden\" name=\"i\" value=\"");
      out.print(i);
      out.write("\">\n");
      out.write("            <input type=\"submit\" value=\"update Rate\">\n");
      out.write("        </td>\n");
      out.write("    </tr>\n");
      out.write("\n");
      out.write("</table>\n");
      out.write("    </form>\n");
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