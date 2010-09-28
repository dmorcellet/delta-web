package delta.common.framework.web;

import delta.common.utils.Parameters;
import delta.common.utils.ParametersNode;

/**
 * A simple web request
 * (replacement for the HTTP servlet requests-based one in the unit tests).
 * @author DAM
 */
public class SimpleRequest extends Parameters implements WebRequest
{
  /**
   * Constructor.
   * @param userContext User context.
   */
  public SimpleRequest(WebUserContext userContext)
  {
    super(userContext);
  }

  /* (non-Javadoc)
   * @see delta.common.framework.web.WebRequest#getParameters()
   */
  public ParametersNode getParameters()
  {
    return this;
  }
}
