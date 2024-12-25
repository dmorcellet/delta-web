package delta.common.framework.web;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import delta.common.framework.web.pages.DebugPage;
import delta.common.framework.web.pages.LoginPage;

/**
 * Dispatcher for web requests.
 * @author DAM
 */
public class WebRequestDispatcher
{
  private static final Logger LOGGER=LoggerFactory.getLogger(WebRequestDispatcher.class);

  private HashMap<String,Class<? extends WebPage>> _actionToPage;

  /**
   * Constructor.
   */
  public WebRequestDispatcher()
  {
    _actionToPage=new HashMap<String,Class<? extends WebPage>>();
    addNewActionPage(LoginPage.LOGIN_ACTION,LoginPage.class);
    addNewActionPage(DebugPage.DEBUG_ACTION,DebugPage.class);
  }

  /**
   * Build a web page handle for the given action.
   * @param actionName Name of the action to use.
   * @return A new web page or <code>null</code> if not supported.
   */
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
      LOGGER.error("",e);
    }

    return ret;
  }

  /**
   * Register a new action page.
   * @param action Action code to use.
   * @param clazz Implementation class of the web page handler implementation.
   */
  public final void addNewActionPage(String action, Class<? extends WebPage> clazz)
  {
  	_actionToPage.put(action,clazz);
  }
}
