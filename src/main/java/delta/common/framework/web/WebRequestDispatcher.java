package delta.common.framework.web;

import java.util.HashMap;

import org.apache.log4j.Logger;

import delta.common.framework.web.pages.DebugPage;
import delta.common.framework.web.pages.LoginPage;
import delta.common.framework.web.utils.WebLoggers;

public class WebRequestDispatcher
{
  private static final Logger _logger=WebLoggers.getWebLogger();

  private HashMap<String,Class<? extends WebPage>> _actionToPage;

  public WebRequestDispatcher()
  {
    _actionToPage=new HashMap<String,Class<? extends WebPage>>();
    addNewActionPage(LoginPage.LOGIN_ACTION,LoginPage.class);
    addNewActionPage(DebugPage.DEBUG_ACTION,DebugPage.class);
  }

  public WebPage buildPage(String actionName)
  {
    WebPage ret=null;
    try
    {
      Class<? extends WebPage> pageClass=_actionToPage.get(actionName);
      if (pageClass!=null)
      {
        ret=pageClass.newInstance();
      }
    }
    catch(Exception e)
    {
      _logger.error("",e);
    }

    return ret;
  }

  public final void addNewActionPage(String action, Class<? extends WebPage> clazz)
  {
  	_actionToPage.put(action,clazz);
  }
}
