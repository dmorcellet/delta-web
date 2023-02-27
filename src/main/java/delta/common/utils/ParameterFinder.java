package delta.common.utils;

import org.apache.log4j.Logger;

/**
 * Find parameters values in a chain of parameter nodes.
 * @author DAM
 */
public class ParameterFinder
{
  private static final Logger LOGGER=Logger.getLogger(ParameterFinder.class);

  /**
   * Get the value of an integer parameter.
   * @param node Node to search.
   * @param name Name of the parameter.
   * @param defaultValue Default value, returned if the node does not contain a value. 
   * @return An integer value.
   */
  public static int getIntParameter(ParametersNode node, String name, int defaultValue)
  {
    int ret=defaultValue;
    Object pValue=getParameter(node,name,true);
    if (pValue!=null)
    {
      if (pValue instanceof Integer)
      {
        ret=((Integer)pValue).intValue();
      }
      else if (pValue instanceof String)
      {
        try
        {
          ret=Integer.parseInt((String)pValue);
        }
        catch(NumberFormatException nfe)
        {
          LOGGER.error("",nfe);
        }
      }
      else
      {
        LOGGER.warn("Type not supported for int conversion : "+pValue.getClass().getName());
      }
    }
    return ret;
  }

  /**
   * Get the value of a long parameter.
   * @param node Node to search.
   * @param name Name of the parameter.
   * @param defaultValue Default value, returned if the node does not contain a value. 
   * @return A long value.
   */
  public static Long getLongParameter(ParametersNode node, String name, Long defaultValue)
  {
    return getLongParameter(node,name,defaultValue,true);
  }

  /**
   * Get the value of a long parameter.
   * @param node Node to search.
   * @param name Name of the parameter.
   * @param defaultValue Default value, returned if the node does not contain a value.
   * @param useParent Use parent nodes if any, or not. 
   * @return A long value.
   */
  public static Long getLongParameter(ParametersNode node, String name, Long defaultValue, boolean useParent)
  {
    Long ret=defaultValue;
    Object pValue=getParameter(node,name,true);
    if (pValue!=null)
    {
      if (pValue instanceof Long)
      {
        ret=(Long)pValue;
      }
      else if (pValue instanceof String)
      {
        try
        {
          ret=Long.valueOf((String)pValue);
        }
        catch(NumberFormatException nfe)
        {
          LOGGER.error("",nfe);
        }
      }
      else
      {
        LOGGER.warn("Type not supported for long conversion : "+pValue.getClass().getName());
      }
    }
    return ret;
  }

  /**
   * Get the value of a boolean parameter.
   * @param node Node to search.
   * @param name Name of the parameter.
   * @param defaultValue Default value, returned if the node does not contain a value.
   * @return A boolean value.
   */
  public static boolean getBooleanParameter(ParametersNode node, String name, boolean defaultValue)
  {
    boolean ret=defaultValue;
    Object pValue=getParameter(node,name,true);
    if (pValue!=null)
    {
      if (pValue instanceof Boolean)
      {
        ret=((Boolean)pValue).booleanValue();
      }
      else if (pValue instanceof String)
      {
        String value=(String)pValue;
        if (value.equalsIgnoreCase("true"))
          ret=true;
        else if (value.equalsIgnoreCase("false"))
          ret=false;
        if (value.equals("1"))
          ret=true;
        else if (value.equals("0"))
          ret=false;
      }
      else
      {
        LOGGER.warn("Type not supported for boolean conversion : "+pValue.getClass().getName());
      }
    }
    return ret;
  }

  /**
   * Get the value of a string parameter.
   * @param node Node to search.
   * @param name Name of the parameter.
   * @param defaultValue Default value, returned if the node does not contain a value.
   * @return A string value.
   */
  public static String getStringParameter(ParametersNode node, String name, String defaultValue)
  {
    String ret=defaultValue;
    Object pValue=getParameter(node,name,true);
    if (pValue!=null)
    {
      if (pValue instanceof String)
        ret=(String)pValue;
    }
    return ret;
  }

  /**
   * Get the value of a parameter.
   * @param node Node to search.
   * @param name Name of the parameter.
   * @param useParent Use parent nodes if any, or not. 
   * @return A value or <code>null</code> if no value found.
   */
  public static Object getParameter(ParametersNode node, String name, boolean useParent)
  {
    Object ret=node.getParameter(name);
    if (ret==null)
    {
      if (useParent)
      {
        ParametersNode parentNode=node.getParent();
        if (parentNode!=null)
        {
          ret=getParameter(parentNode,name,useParent);
        }
      }
    }
    return ret;
  }
}
