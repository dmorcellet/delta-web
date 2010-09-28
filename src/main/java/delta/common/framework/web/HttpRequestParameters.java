package delta.common.framework.web;

import javax.servlet.http.HttpServletRequest;

import delta.common.utils.ParametersNode;

/**
 * A parameters node that wraps an HTTP servlet request.
 * @author DAM
 */
public class HttpRequestParameters implements ParametersNode
{
  /**
   * Parameter name of the underlying request. 
   */
  public static final String HTTP_REQUEST_PARAM="HTTP_REQUEST";
  private HttpServletRequest _request;
  private ParametersNode _parent;

  /**
   * Constructor.
   * @param request Underlying HTTP servlet request.
   * @param parent Parent node.
   */
  public HttpRequestParameters(HttpServletRequest request, ParametersNode parent)
  {
    _request=request;
    _parent=parent;
  }

  public Object getParameter(String name)
  {
    Object ret=null;
    if (HTTP_REQUEST_PARAM.equals(name))
    {
      return _request;
    }
    if (_request!=null)
    {
      ret=_request.getParameter(name);
    }
    return ret;
  }

  public ParametersNode getParent()
  {
    return _parent;
  }
}
