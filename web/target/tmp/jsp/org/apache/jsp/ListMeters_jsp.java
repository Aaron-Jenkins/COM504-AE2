package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import aaronjenkins.web.WebObjectFactory;
import aaronjenkins.model.ServiceFactory;
import aaronjenkins.model.ServiceFacade;
import aaronjenkins.model.Meter;

public final class ListMeters_jsp extends org.apache.jasper.runtime.HttpJspBase
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
            Meter meter = serviceFacade.retrieveMeter(meterId);
            meter.setLocation(meterField_AReq);
            serviceFacade.updateMeter(meter);
        } catch (Exception e) {
            errorMessage = "problem modifying Meter " + e.getMessage();
        }
    } else if ("createMeter".equals(action)) {
        try {
            Meter meterTemplate = new Meter();
            meterTemplate.setLocation(meterField_AReq);
            Meter meter = serviceFacade.createMeter(meterTemplate);
            if (meter == null) {
                errorMessage = "problem creating Meter. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Meter " + e.getMessage();
        }
    } 

    List<Meter> meterList = serviceFacade.retrieveAllMeters();


      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <title>Meter List</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- print error message if there is one -->\n");
      out.write("        <div style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</div>\n");
      out.write("        <h1>Meter List</h1>\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>id</th>\n");
      out.write("                <th>location</th>\n");
      out.write("                <th>scheduleList</th>\n");
      out.write("                <th></th>\n");
      out.write("            </tr>\n");
      out.write("            ");
  for (Meter meter : meterList) {
            
      out.write("\n");
      out.write("            <tr>\n");
      out.write("                <td>");
      out.print(meter.getId());
      out.write("</td>\n");
      out.write("                <td>");
      out.print(meter.getLocation());
      out.write("</td>\n");
      out.write("                <td>\n");
      out.write("                    <form action=\"ListSchedule.jsp\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"listSchedule\">\n");
      out.write("                        <input type=\"hidden\" name=\"meterId\" value=\"");
      out.print(meter.getId());
      out.write("\">\n");
      out.write("                        <input type=\"submit\" value=\"List Schedule\">\n");
      out.write("                    </form>\n");
      out.write("                </td>\n");
      out.write("                <td>\n");
      out.write("                    <form action=\"AddOrModifyMeter.jsp\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"modifyMeter\">\n");
      out.write("                        <input type=\"hidden\" name=\"meterId\" value=\"");
      out.print(meter.getId());
      out.write("\">\n");
      out.write("                        <input type=\"submit\" value=\"Modify Meter\">\n");
      out.write("                    </form>\n");
      out.write("                    <form action=\"ListMeters.jsp\">\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"deleteMeter\">\n");
      out.write("                        <input type=\"hidden\" name=\"meterId\" value=\"");
      out.print(meter.getId());
      out.write("\">\n");
      out.write("                        <input type=\"submit\" value=\"Delete Meter\">\n");
      out.write("                    </form>\n");
      out.write("                </td>\n");
      out.write("            </tr>\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("\n");
      out.write("        </table> \n");
      out.write("        <BR>\n");
      out.write("        <form action=\"AddOrModifyMeter.jsp\">\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createMeter\">\n");
      out.write("            <input type=\"submit\" value=\"Create Meter\">\n");
      out.write("        </form>\n");
      out.write("    </body>\n");
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
