package delta.common.framework.web;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import delta.common.framework.web.utils.WebLoggers;
import delta.common.utils.misc.ClassResolver;

public class MainTestWebServlet
{
  private static final Logger _logger=WebLoggers.getWebLogger();

  private String _action="NAME_INDEX";
  private WebApplication _app;

  public void go()
  {
    if (_app==null) return;
    try
    {
      _app.initApplication();
      WebUserContext context=_app.buildUserContext();
      WebRequestParameters p=new WebRequestParameters(context,null);
      PrintWriter pw=new PrintWriter(System.out);
      /**
       * @todo Handle binary/text pages
       */
      _app.handleAction(_action,p,null);
      _app.closeApplication();
      pw.flush();
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
