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
  private WebUserContext _context;
  protected WebRequest _request;

  void setApplication(WebApplication app)
  {
    _application=app;
  }

  void setUserContext(WebUserContext context)
  {
    _context=context;
  }

  void setRequest(WebRequest request)
  {
    _request=request;
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
    return _context;
  }

  /**
   * Implementation of the 'parse parameters' phase of page generation.
   * @throws Exception
   */
  public void parseParameters() throws Exception
  {
    // Nothing to do for a null web page
  }

  /**
   * Implementation of the 'fetch data' phase of page generation.
   * @throws Exception
   */
  public void fetchData() throws Exception
  {
    // Nothing to do for a null web page
  }

  /**
   * Get the MIME type of the generated contents.
   * @return a MIME type.
   */
  public String getMIMEType()
  {
    return "text/html; charset=ISO-8859-1";
  }

  /**
   * Indicates if this page generates some binary contents,
   * or not.
   * @return <code>true</code> for binary contents, <code>false</code> for
   * textual contents.
   */
  public boolean isBinary()
  {
    return false;
  }

  /**
   * Generate the contents of this page in the given writer.
   * @param pw Output writer.
   */
  public void generate(PrintWriter pw)
  {
    // Nothing to do for a null web page
  }

  /**
   * Generate the contents of this page in the given stream.
   * @param os Output stream.
   */
  public void generate(OutputStream os)
  {
    // Nothing to do for a null web page
  }
}
