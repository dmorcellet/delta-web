package delta.common.framework.web;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import delta.common.framework.web.utils.WebLoggers;
import delta.common.utils.text.EncodingNames;

/**
 * Request response that uses a byte array to store request results.
 * @author DAM
 */
public class SimpleRequestResponse implements RequestResponse
{
  private static final Logger _logger=WebLoggers.getWebLogger();
  private ByteArrayOutputStream _bos;
  private PrintWriter _writer;
  private String _contentType;
  private String _encoding;

  /**
   * Constructor.
   * @param encoding Encoding to use (<code>null</code> to use the default encoding).
   */
  public SimpleRequestResponse(String encoding)
  {
    _bos=new ByteArrayOutputStream();
    OutputStreamWriter writer=null;
    if (encoding==null)
    {
      encoding=EncodingNames.DEFAULT_ENCODING;
    }
    try
    {
      writer=new OutputStreamWriter(_bos,encoding);
      _writer=new PrintWriter(writer);
      _encoding=encoding;
    }
    catch(Exception e)
    {
      _writer=new PrintWriter(_bos);
      _encoding=null;
      _logger.error("Invalid encoding ["+encoding+"]",e);
    }
    _contentType="text/html";
  }

  /**
   * Get the MIME type of the response.
   * @return the MIME type of the response.
   */
  public String getContentType()
  {
    return _contentType;
  }
  
  public void setContentType(String mimeType)
  {
    _contentType=mimeType;
  }

  public OutputStream getOutputStream()
  {
    return _bos;
  }

  public PrintWriter getWriter()
  {
    return _writer;
  }

  /**
   * Get the request response as a binary buffer.
   * @return a buffer.
   */
  public byte[] getBinaryResponse()
  {
    _writer.flush();
    return _bos.toByteArray();
  }

  /**
   * Get the request response as a string.
   * @return a string.
   */
  public String getTextResponse()
  {
    byte[] buffer=getBinaryResponse();
    String ret=null;
    try
    {
      ret=new String(buffer,_encoding);
    }
    catch(Exception e)
    {
      // Should not happen since the encoding has been validated.
      _logger.error("Cannot build text response string",e);
    }
    return ret;
  }
}
