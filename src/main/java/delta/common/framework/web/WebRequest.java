package delta.common.framework.web;

import delta.common.utils.ParametersNode;

/**
 * Interface of a web request.
 * @author DAM
 */
public interface WebRequest extends ParametersNode
{
  // TODO remove ParametersNode extends clause.
  /**
   * Parameter name for the request's action.
   */
  public static final String ACTION_PARAM="ACTION";
  
  /**
   * Get the parameters of this request.
   * @return the parameters of this request.
   */
  public ParametersNode getParameters();
}
