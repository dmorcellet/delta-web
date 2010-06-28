package delta.common.framework.web.utils;

import org.apache.log4j.Logger;

import delta.common.utils.traces.LoggersRegistry;

/**
 * Management class for all Web loggers.
 * @author DAM
 */
public abstract class WebLoggers
{
  /**
   * Name of the "WEB" logger.
   */
  public static final String WEB="FRAMEWORK.WEB";

  private static final Logger _webLogger=LoggersRegistry.getLogger(WEB);

  /**
   * Get the logger used for Web (WEB).
   * @return the logger used for Web.
   */
  public static Logger getWebLogger()
  {
    return _webLogger;
  }
}
