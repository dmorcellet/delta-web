package delta.common.framework.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import delta.common.utils.time.chronometers.Chronometer;
import delta.common.utils.time.chronometers.ChronometerManager;

/**
 * Web servlet.
 * An HTTP servlet that uses a web application to handle HTTP requests.
 * @author DAM
 */
public abstract class WebServlet extends HttpServlet
{
  private static final Logger LOGGER=Logger.getLogger(WebServlet.class);

  private static final String USER_CONTEXT="USER_CONTEXT";
  private WebApplication _application;

  /**
   * Constructor.
   */
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

  private void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	throws IOException, ServletException
  {
    ChronometerManager cm=ChronometerManager.getInstance();
    Chronometer c=cm.start("REQUEST");
    try
    {
      WebUserContext context=getUserContext(httpServletRequest);
      WebRequest request=new HttpWebRequest(httpServletRequest,context);
      context.useParameters(request.getParameters());
      RequestResponse response=new ServletRequestResponse(httpServletResponse);
      _application.handleRequest(context,request,response);
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
  }

  @Override
  public void init() throws ServletException
  {
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
        LOGGER.error("",t);
      }
      _application=null;
    }
  }
}
