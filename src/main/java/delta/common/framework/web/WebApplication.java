package delta.common.framework.web;

import java.io.OutputStream;
import java.io.PrintWriter;

import delta.common.utils.ParameterFinder;
import delta.common.utils.Parameters;
import delta.common.utils.ParametersNode;
import delta.common.utils.time.chronometers.Chronometer;
import delta.common.utils.time.chronometers.ChronometerManager;

/**
 * Base class for web applications.
 * @author DAM
 */
public abstract class WebApplication
{
  private WebApplicationContext _context;
  private Parameters _parameters;
  private String _name;

  /**
   * Constructor.
   * @param name Name of the web application.
   */
  public WebApplication(String name)
  {
    _context=new WebApplicationContext();
    _parameters=new Parameters();
    _name=name;
  }

  /**
   * Get the application-wide parameters.
   * @return a parameters node. 
   */
  public ParametersNode getParameters()
  {
    return _parameters;
  }

  /**
   * Get the name of this application.
   * @return the name of this application.
   */
  public String getName()
  {
    return _name;
  }

  /**
   * Handle a contents request.
   * @param context User context.
   * @param request Source request.
   * @param response Stores the generated response.
   * @throws Exception if an error occurs.
   */
  public void handleRequest(WebUserContext context, WebRequest request, RequestResponse response)
  	throws Exception
  {
    WebRequestDispatcher dispatcher=getDispatcher();
    ParametersNode params=request.getParameters();
    String action=ParameterFinder.getStringParameter(params,WebRequest.ACTION_PARAM,null);
    if (action!=null)
    {
      WebPage page=dispatcher.buildPage(action);
      page.setApplication(this);
      page.setRequest(request);
      page.setUserContext(context);
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
    else
    {
      // todo generate some default contents.
    }
  }

  /**
   * Get the application's request dispatcher.
   * @return a dispatcher.
   */
  public abstract WebRequestDispatcher getDispatcher();

  /**
   * Build a new user context.
   * @return the newly built user context.
   */
  public WebUserContext buildUserContext()
  {
    return new WebUserContext(this);
  }

  /**
   * Implementation of the application's initialization.
   * @throws Exception
   */
  public abstract void initApplication() throws Exception;

  /**
   * Implementation of the application's termination.
   * @throws Exception
   */
  public abstract void closeApplication() throws Exception;

  /**
   * Get the application context.
   * @return the application context.
   */
  public WebApplicationContext getAppContext()
  {
    return _context;
  }

  protected void setAppContext(WebApplicationContext context)
  {
    _context=context;
  }
}
