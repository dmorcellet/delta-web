package delta.common.framework.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import delta.common.framework.application.Application;
import delta.common.framework.web.utils.WebLoggers;
import delta.common.utils.time.chronometers.Chronometer;
import delta.common.utils.time.chronometers.ChronometerManager;

public abstract class WebServlet extends HttpServlet
{
  private static final Logger _logger=WebLoggers.getWebLogger();

  private static final String USER_CONTEXT="USER_CONTEXT";
  private WebApplication _application;

  public WebServlet()
  {
    super();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  	throws IOException, ServletException
  {
    handleRequest(request,response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
  {
    handleRequest(request,response);
  }

  private void handleRequest(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException
  {
    ChronometerManager cm=ChronometerManager.getInstance();
    Chronometer c=cm.start("REQUEST");
    try
    {
      String action=request.getParameter("ACTION");
      if (action!=null)
      {
        WebUserContext context=getUserContext(request);
        WebRequestParameters parameters=new WebRequestParameters(context,request);
        context.useParameters(parameters);
        _application.handleAction(action,parameters,response);
      }
    }
    catch(IOException ioException)
    {
      throw ioException;
    }
    catch(Throwable t)
    {
      throw new ServletException(t);
    }
    cm.stop(c);
    cm.dump();
    //System.gc();
    //new StatsDumper.ResetDelta();
  }

  @Override
  public void init() throws ServletException
  {
    Application.getInstance().start();
    ChronometerManager cm=ChronometerManager.getInstance();
    Chronometer c=cm.start("INIT");
    try
    {
      _application=buildApplication();
      _application.initApplication();
    }
    catch(Throwable t)
    {
      throw new ServletException(t);
    }
    cm.stop(c);
    cm.dump();
  }

  private WebUserContext getUserContext(HttpServletRequest request)
  {
  	HttpSession session=request.getSession(true);
  	WebUserContext wuc=(WebUserContext)session.getAttribute(USER_CONTEXT);
  	if (wuc==null)
  	{
  		wuc=_application.buildUserContext();
      wuc.setHttpSession(session);
  		session.setAttribute(USER_CONTEXT,wuc);
  	}
    return wuc;
  }

  protected abstract WebApplication buildApplication() throws Exception;

  @Override
  public void destroy()
  {
    if (_application!=null)
    {
      try
      {
        _application.closeApplication();
      }
      catch(Throwable t)
      {
        _logger.error("",t);
      }
      _application=null;
    }
  }
}
