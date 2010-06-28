package delta.common.framework.web;

import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import delta.common.utils.Parameters;
import delta.common.utils.time.chronometers.Chronometer;
import delta.common.utils.time.chronometers.ChronometerManager;

public abstract class WebApplication
{
  private WebApplicationContext _context;
  private Parameters _parameters;
  private String _name;

  public WebApplication(String name)
  {
    _context=new WebApplicationContext();
    _parameters=new Parameters();
    _name=name;
  }

  public Parameters getParameters()
  {
    return _parameters;
  }

  public String getName()
  {
    return _name;
  }

  public void handleAction(String action, WebRequestParameters parameters, HttpServletResponse response)
  	throws Exception
  {
    // Test
    if ("REDIRECT".equals(action))
    {
      response.sendRedirect("genea?ACTION=PLACES");
      return;
    }
    // END test
    WebRequestDispatcher dispatcher=getDispatcher();
    WebPage page=dispatcher.buildPage(action);
    page.setApplication(this);
    page.setParameters(parameters);
    page.parseParameters();
    ChronometerManager cm=ChronometerManager.getInstance();
    Chronometer c=cm.start("FETCH DATA");
    page.fetchData();
    cm.stop(c);
    c=cm.start("BUILD HTML");
    response.setContentType(page.getMIMEType());
    if (page.isBinary())
    {
      OutputStream os=response.getOutputStream();
      page.generate(os);
      os.flush();
    }
    else
    {
      PrintWriter pw=response.getWriter();
      page.generate(pw);
      pw.flush();
    }
    cm.stop(c);
  }

  public abstract WebRequestDispatcher getDispatcher();

  public WebUserContext buildUserContext()
  {
    return new WebUserContext(this);
  }

  public abstract void initApplication() throws Exception;
  public abstract void closeApplication() throws Exception;

  public WebApplicationContext getAppContext()
  {
    return _context;
  }

  protected void setAppContext(WebApplicationContext context)
  {
    _context=context;
  }
}
