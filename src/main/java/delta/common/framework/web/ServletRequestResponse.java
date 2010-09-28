package delta.common.framework.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Request response that uses an underlying HTTP servlet response.
 * @author DAM
 */
public class ServletRequestResponse implements RequestResponse
{
  private HttpServletResponse _httpResponse;

  /**
   * Constructor.
   * @param httpResponse Underlying HTTP servlet response.
   */
  public ServletRequestResponse(HttpServletResponse httpResponse)
  {
    _httpResponse=httpResponse;
  }

  public void setContentType(String mimeType)
  {
    _httpResponse.setContentType(mimeType);
  }

  public OutputStream getOutputStream() throws IOException
  {
    return _httpResponse.getOutputStream();
  }

  public PrintWriter getWriter() throws IOException
  {
    return _httpResponse.getWriter();
  }
}
