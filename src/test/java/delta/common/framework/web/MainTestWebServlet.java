package delta.common.framework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.utils.misc.ClassResolver;

/**
 * Test class for the web servlets.
 * @author DAM
 */
public class MainTestWebServlet
{
  private static final Logger LOGGER=LoggerFactory.getLogger(MainTestWebServlet.class);

  private String _action="PLACES";
  private WebApplication _app;

  /**
   * Do it:
   * <ul>
   * <li>start application,
   * <li>handle a simple request,
   * <li>close application.
   * </ul>
   */
  public void go()
  {
    if (_app==null) return;
    try
    {
      _app.initApplication();
      WebUserContext userContext=_app.buildUserContext();
      SimpleRequest request=new SimpleRequest(userContext);
      request.putStringParameter(WebRequest.ACTION_PARAM,_action);
      SimpleRequestResponse response=new SimpleRequestResponse(null);
      try
      {
        _app.handleRequest(userContext,request,response);
      }
      catch(Exception e)
      {
        LOGGER.error("Page generation error",e);
      }
      String text=response.getTextResponse();
      System.out.println(text);
      _app.closeApplication();
    }
    catch(Exception e)
    {
      LOGGER.error("",e);
    }
  }

  private void buildWebApp(String name)
  {
    ClassResolver<WebApplication> resolver=new ClassResolver<WebApplication>(WebApplication.class);
    Class<? extends WebApplication> c=resolver.findClass(name);
    
    if (c!=null)
    {
      try
      {
        _app=c.newInstance();
      }
      catch(Exception e)
      {
        LOGGER.error("",e);
      }
    }
  }

  /**
   * Main method for this test.
   * @param args Fully qualified name of the web application class.
   */
  public static void main(String[] args)
  {
    if (args.length>=1)
    {
      String webAppClassName=args[0];
      MainTestWebServlet test=new MainTestWebServlet();
      test.buildWebApp(webAppClassName);
      test.go();
    }
  }
}
