package delta.common.framework.web;

import java.io.PrintWriter;

public class WebPageTools
{
  protected PrintWriter _pw;

  public WebPageTools(PrintWriter pw)
  {
    _pw=pw;
  }

  public static void generatePageHeader(String title, PrintWriter pw)
  {
    // HTML 3.2
    //pw.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">");
    // HTML 4.01 strict
    pw.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/1999/REC-html401-19991224/strict.dtd\">");
    pw.println("<html>");
    pw.println("<head>");
    pw.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
    pw.print("<title>");
    pw.print(title);
    pw.println("</title>");
    pw.println("<link rel=\"stylesheet\" type=\"text/css\" media=\"screen\" href=\"ressources/genea.css\">");
    pw.println("</head>");
    pw.println("<body>");
    //pw.println("<body text=\"#000000\" bgcolor=\"#FFFFFF\" link=\"#0000EE\" vlink=\"#551A8B\" alink=\"#FF0000\" background=\"ressources/fond.gif\">");
  }

  public static void generateHorizontalRuler(PrintWriter pw)
  {
    pw.println("<hr>");
    //pw.println("<hr width=\"100%\">");
  }

  public static void generatePageFooter(PrintWriter pw)
  {
    generateHorizontalRuler(pw);
    pw.println("<div>");
    pw.print("<a href=\"http://validator.w3.org/check?uri=referer\">");
    // HTML 3.2
    //pw.print("<img src=\"ressources/valid-html32-blue.png\" HEIGHT=\"31\" WIDTH=\"88\" ALT=\"Valid HTML 3.2!\" />");
    // HTML 4.01 strict
    pw.print("<img src=\"ressources/valid-html401-blue.png\" height=\"31\" width=\"88\" alt=\"Valid HTML 4.01 Strict\" />");
    pw.println("</a>");
    pw.println("</div>");
    pw.println("</body>");
    pw.println("</html>");
  }
}
