package delta.common.framework.web.pages;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import delta.common.framework.web.WebPage;
import delta.common.framework.web.WebPageTools;
import delta.common.framework.web.WebUserContext;

/**
 * Debug page.
 * @author DAM
 */
public class DebugPage extends WebPage
{
  public static final String DEBUG_ACTION="DEBUG";

  @Override
  public void generate(PrintWriter pw)
  {
    String title="Debug";
    WebPageTools.generatePageHeader(title,pw);

    WebUserContext context=getUserContext();
    HttpSession session=context.getHttpSession();

    // Print session info
    {
      pw.println("<H1>Session</H1>");
      pw.println("<div>");
      Date created=new Date(session.getCreationTime());
      Date accessed=new Date(session.getLastAccessedTime());
      pw.println("ID "+session.getId());pw.println("<br>");
      pw.println("Created: "+created);pw.println("<br>");
      pw.println("Last Accessed: "+accessed);pw.println("<br>");

      Enumeration<?> e=session.getAttributeNames();
      while (e.hasMoreElements())
      {
        String name=(String)e.nextElement();
        String value=session.getAttribute(name).toString();
        pw.println(name+" = "+value);pw.println("<br>");
      }
      pw.println("</div>");
    }

    ServletRequest request=_parameters.getRequest();
    HttpServletRequest httpRequest=null;
    if (request instanceof HttpServletRequest)
    {
      httpRequest=(HttpServletRequest)request;
    }

    // Request headers
    if (httpRequest!=null)
    {
      pw.println("<H1>Request info</H1>");
      pw.println("<div>");
      pw.println("Method: "+httpRequest.getMethod());pw.println("<br>");
      pw.println("Request URI: "+httpRequest.getRequestURI());pw.println("<br>");
      pw.println("Protocol: "+httpRequest.getProtocol());pw.println("<br>");
      pw.println("PathInfo: "+httpRequest.getPathInfo());pw.println("<br>");
      pw.println("Remote Address: "+httpRequest.getRemoteAddr());pw.println("<br>");
      pw.println("</div>");

      Enumeration<?> e=httpRequest.getHeaderNames();
      if (e.hasMoreElements())
      {
        pw.println("<H1>Request headers</H1>");
        pw.println("<div>");
        while (e.hasMoreElements())
        {
          String name=(String)e.nextElement();
          String value=httpRequest.getHeader(name);
          pw.println(name+" = "+value);pw.println("<br>");
        }
        pw.println("</div>");
      }

      Cookie[] cookies=httpRequest.getCookies();
      if (cookies!=null)
      {
        pw.println("<H1>Cookies</H1>");
        pw.println("<div>");
        for(int i=0;i<cookies.length;i++)
        {
          Cookie c=cookies[i];
          String name=c.getName();
          String value=c.getValue();
          pw.println(name+" = "+value);pw.println("<br>");
        }
        pw.println("</div>");
      }
    }

    WebPageTools.generatePageFooter(pw);
  }
}
