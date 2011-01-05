package delta.common.framework.web;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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

  public String getApplicationName()
  {
    return _appname;
  }

  public void setParameter(String paramName, Object value)
  {
    _parameters.put(paramName,value);
  }

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

  public String getAction()
  {
    return "NONE";
  }
}
