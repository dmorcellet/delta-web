package delta.common.framework.web.pages;

import java.io.PrintWriter;

import delta.common.framework.web.WebPage;
import delta.common.framework.web.WebPageTools;

/**
 * Basic login page.
 * @author DAM
 */
public class LoginPage extends WebPage
{
  public static final String LOGIN_ACTION="LOGIN";

  @Override
  public void generate(PrintWriter pw)
  {
    String title="Login";
    WebPageTools.generatePageHeader(title,pw);
    pw.println("<FORM METHOD=\"POST\" ACTION=\"genea?ACTION=PLACES\">");
    pw.println("<P> Login: <INPUT NAME = \"Login\" TYPE=\"TEXT\" SIZE=\"15\" MAXLENGTH=\"30\">");
    pw.println("<P> Password: <INPUT NAME = \"Password\" TYPE=\"TEXT\" SIZE=\"15\" MAXLENGTH=\"30\">");
    pw.println("<P><INPUT TYPE=\"SUBMIT\" VALUE=\"LOGIN\">");
    pw.println("<INPUT TYPE=\"RESET\">");
    pw.println("</FORM>");
    WebPageTools.generatePageFooter(pw);
  }
}
