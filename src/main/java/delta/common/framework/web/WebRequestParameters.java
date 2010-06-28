package delta.common.framework.web;

import javax.servlet.ServletRequest;

import delta.common.utils.ParametersNode;

public final class WebRequestParameters implements ParametersNode
{
  private WebUserContext _userContext;
  private ServletRequest _request;

  public WebRequestParameters(WebUserContext userContext,ServletRequest request)
  {
    _userContext=userContext;
    _request=request;
  }

  public WebUserContext getUserContext()
  {
    return _userContext;
  }

  public ServletRequest getRequest()
  {
    return _request;
  }

  public Object getParameter(String name, boolean useParent)
  {
    Object ret=null;
    if (_request!=null)
    {
      ret=_request.getParameter(name);
    }
    if ((ret==null) && (useParent) && (_userContext!=null))
    {
      ret=_userContext.getParameter(name,useParent);
    }
    return ret;
  }

  public ParametersNode getParent()
  {
    return _userContext;
  }
}
