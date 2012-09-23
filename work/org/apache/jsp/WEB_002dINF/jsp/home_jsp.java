package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/template/header.jsp");
    _jspx_dependants.add("/template/footer.jsp");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("\t<title>Play Life</title>\n");
      out.write("\t");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/jquery/jquery-ui-1.8.16.custom.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/jquery/jquery.jgrowl.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap/bootstrap.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/bootstrap/bootstrap-responsive.css\" />\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/main.css\" />\n");
      out.write("\n");
      out.write("<script src='/js/jquery/jquery-1.7.2.min.js'></script>\n");
      out.write("<script src='/js/jquery/jquery-ui-1.8.17.custom.min.js'></script>\n");
      out.write("<script src='/js/jquery/jquery.jgrowl_minimized.js'></script>\n");
      out.write("<script src='/js/jquery/jquery.blockUI.js'></script>\n");
      out.write("<script src='/js/jquery/jquery.form.js'></script>\n");
      out.write("<script src='/js/jquery/jquery.animate-shadow-min.js'></script>\n");
      out.write("<script src='/js/jquery/jquery.transform-0.6.2.min.js'></script>\n");
      out.write("<script src='/js/jquery/jquery.livevalidation.js'></script>\n");
      out.write("<script src='/template/locale.js'></script>\n");
      out.write("<script src='/js/bootstrap/bootstrap.min.js'></script>\n");
      out.write("<script src='/js/font/cufon-yui.js'></script>\n");
      out.write("<script src='/js/font/Needlework_Good.font.js'></script>\n");
      out.write("<script src='/js/main.js'></script>");
      out.write(" \n");
      out.write("    <script type='text/javascript' src='/js/home.js'></script>\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"/css/home.css\" />\n");
      out.write("    \n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<div class='container'>\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/template/userHeader", out, false);
      out.write("\n");
      out.write("\t\t\n");
      out.write("        <div class='well'>\n");
      out.write("        \thome\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        ");
      out.write("\n");
      out.write("    </div>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
