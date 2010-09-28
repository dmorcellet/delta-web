package delta.common.framework.web;

import javax.servlet.http.HttpSession;

import delta.common.utils.Parameters;
import delta.common.utils.ParametersNode;

/**
 * Context for a Web application user.
 * @author DAM
 */
public class WebUserContext extends Parameters
{
  /**
   * Name of the parameter that stores the user context.
   */
  public static final String USER_CONTEXT_PARAM="USER_CONTEXT";
  private WebApplication _application;
  private HttpSession _httpSession;

  /**
   * Constructor.
   * @param app Associated web application.
   */
  public WebUserContext(WebApplication app)
  {
    super(app.getParameters());
    _application=app;
  }

  /**
   * Package protected setHttpSession.
   * @param session
   */
  void setHttpSession(HttpSession session)
  {
    _httpSession=session;
  }

  /**
   * Get the underlying HTTP session.
   * @return the underlying HTTP session.
   */
  public HttpSession getHttpSession()
  {
    return _httpSession;
  }

  /**
   * Get the associated web application.
   * @return the associated web application.
   */
  public WebApplication getApplication()
  {
    return _application;
  }

  /**
   * Use the parameters contained in the given request.
   * @param requestParameters Request parameters.
   */
  public void useParameters(ParametersNode requestParameters)
  {
    // Nothing special to do here
    // Override to handle request -> user context parameter setting.
  }
}
