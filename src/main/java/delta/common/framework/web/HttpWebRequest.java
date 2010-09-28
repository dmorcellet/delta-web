package delta.common.framework.web;

import javax.servlet.http.HttpServletRequest;

import delta.common.utils.ParametersNode;

/**
 * A web request that wraps an HTTP servlet request.
 * @author DAM
 */
public class HttpWebRequest implements WebRequest
{
  private ParametersNode _params;
  private WebUserContext _userContext;

  /**
   * Constructor.
   * @param request Underlying HTTP servlet request.
   * @param context User context.
   */
  public HttpWebRequest(HttpServletRequest request, WebUserContext context)
  {
    _params=new HttpRequestParameters(request,context);
    _userContext=context;
  }
  
  /**
   * Get the user context.
   * @return the user context.
   */
  public WebUserContext getUserContext()
  {
    return _userContext;
  }

  public ParametersNode getParameters()
  {
    return _params;
  }

  public Object getParameter(String name)
  {
    return _params.getParameter(name);
  }

  public ParametersNode getParent()
  {
    return _userContext;
  }
}