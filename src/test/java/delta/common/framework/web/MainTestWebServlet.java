package delta.common.framework.web;

import org.apache.log4j.Logger;

import delta.common.framework.web.utils.WebLoggers;
import delta.common.utils.misc.ClassResolver;

public class MainTestWebServlet
{
  private static final Logger _logger=WebLoggers.getWebLogger();

  private String _action="PLACES";
  private WebApplication _app;

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
        _logger.error("Page generation error",e);
      }
      String text=response.getTextResponse();
      System.out.println(text);
      _app.closeApplication();
    }
    catch(Exception e)
    {
      _logger.error("",e);
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
        _logger.error("",e);
      }
    }
  }
  
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
