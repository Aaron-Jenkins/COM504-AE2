package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import aaronjenkins.web.WebObjectFactory;
import aaronjenkins.model.ServiceFactory;
import aaronjenkins.model.ServiceFacade;
import aaronjenkins.model.Meter;

public final class AddOrModifyMeter_jsp extends org.apache.jasper.runtime.HttpJspBase
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



      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\n");
      out.write("        <title>Edit Meter</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
 if ("createMeter".equals(action)) {
        
      out.write("\n");
      out.write("        <h1>Add New Meter</h1>\n");
      out.write("        ");
 } else {
      out.write("\n");
      out.write("        <h1>Modify Meter ");
      out.print(meterId);
      out.write("</h1>\n");
      out.write("        ");
 }
            assert meter != null;
      out.write("\n");
      out.write("        <form action=\"ListMeters.jsp\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <th>Field</th>\n");
      out.write("                    <th>Current Value</th>\n");
      out.write("                    <th>New Value</th>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Meter Id</td>\n");
      out.write("                    <td>");
      out.print(meter.getId());
      out.write("</td>\n");
      out.write("                    <td></td>\n");
      out.write("                </tr>\n");
      out.write("                <tr>\n");
      out.write("                    <td>location</td>\n");
      out.write("                    <td>");
      out.print(meter.getLocation());
      out.write("</td>\n");
      out.write("                    <td><input type=\"text\" name=\"location\" value =\"");
      out.print(meter.getLocation());
      out.write("\"></td>\n");
      out.write("                </tr>\n");
      out.write("            </table> \n");
      out.write("            <BR>\n");
      out.write("            ");
 if ("createMeter".equals(action)) {
            
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createMeter\">\n");
      out.write("            <input type=\"hidden\" name=\"meterId\" value=\"");
      out.print(meterId);
      out.write("\">\n");
      out.write("            <input type=\"submit\" value=\"Create New Meter\">\n");
      out.write("            ");
 } else if ("modifyMeter".equals(action)) {
            
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"modifyMeter\">\n");
      out.write("            <input type=\"hidden\" name=\"meterId\" value=\"");
      out.print(meterId);
      out.write("\">\n");
      out.write("            <input type=\"submit\" value=\"Modify Meter\">\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("        </form>\n");
      out.write("        <form action=\"ListMeters.jsp\">\n");
      out.write("            <input type=\"submit\" value=\"Cancel and Return\">\n");
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
