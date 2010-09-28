package delta.common.framework.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Interface of a request response.
 * @author DAM
 */
public interface RequestResponse
{
  /**
   * Get the MIME type of the response.
   * @param mimeType MIME type to use. 
   */
  void setContentType(String mimeType);
  
  /**
   * Get the output stream for the binary response.
   * @return an output stream.
   * @throws IOException
   */
  OutputStream getOutputStream() throws IOException;

  /**
   * Get the writer for the text response.
   * @return a writer.
   * @throws IOException
   */
  PrintWriter getWriter() throws IOException;
}
