package delta.common.framework.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Base class for page parameters.
 * @author DAM
 */
public class PageParameters
{
  private static final String ACTION="ACTION";
  protected HashMap<String,Object> _parameters;
  private String _appname;

  protected PageParameters()
  {
    _parameters=new HashMap<String,Object>();
    _appname="webapp";
  }

  protected PageParameters(String appName)
  {
    _parameters=new HashMap<String,Object>();
    _appname=appName;
  }

  /**
   * Get the application name.
   * @return the application name.
   */
  public String getApplicationName()
  {
    return _appname;
  }

  /**
   * Set the value of a parameter.
   * @param paramName Name of the parameter to set.
   * @param value Value to set.
   */
  public void setParameter(String paramName, Object value)
  {
    _parameters.put(paramName,value);
  }

  /**
   * Build an URL part for this set of parameters.
   * @return an URL string.
   */
  public String build()
  {
    StringBuilder sb=new StringBuilder(getApplicationName());
    sb.append('?');
    sb.append(ACTION);
    sb.append('=');
    sb.append(getAction());

    Map.Entry<String,Object> entry;
    Iterator<Map.Entry<String,Object>> it;
    Set<Map.Entry<String,Object>> set=_parameters.entrySet();
    for(it=set.iterator();it.hasNext();)
    {
      entry=it.next();
      sb.append("&amp;");
      sb.append(entry.getKey());
      sb.append('=');
      sb.append(entry.getValue());
    }
    return sb.toString();
  }

  /**
   * Get the action for this page.
   * @return an action code.
   */
  public String getAction()
  {
    return "NONE";
  }
}
