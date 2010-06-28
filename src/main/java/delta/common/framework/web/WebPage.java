package delta.common.framework.web;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Base class for web page builders.
 * @author DAM
 */
public abstract class WebPage
{
  private WebApplication _application;
  protected WebRequestParameters _parameters;

  void setApplication(WebApplication app)
  {
    _application=app;
  }

  void setParameters(WebRequestParameters parameters)
  {
    _parameters=parameters;
  }

  /**
   * Get the associated web application context.
   * @return the associated web application context.
   */
  public WebApplicationContext getAppContext()
  {
    return _application.getAppContext();
  }

  /**
   * Get the associated user context.
   * @return the associated user context.
   */
  public WebUserContext getUserContext()
  {
    return _parameters.getUserContext();
  }

  public void parseParameters() throws Exception
  {
    // Nothing to do for a null web page
  }

  public void fetchData() throws Exception
  {
    // Nothing to do for a null web page
  }

  public String getMIMEType()
  {
    return "text/html";
  }

  public boolean isBinary()
  {
    return false;
  }

  public void generate(PrintWriter pw)
  {
    // Nothing to do for a null web page
  }

  public void generate(OutputStream os)
  {
    // Nothing to do for a null web page
  }
}
