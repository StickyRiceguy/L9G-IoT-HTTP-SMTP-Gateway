package l9g.iot.httpsmtpgateway;

//~--- non-JDK imports --------------------------------------------------------

import com.google.common.base.Strings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//~--- JDK imports ------------------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;

/**
 * Class description
 *
 *
 * @version        $version$, 19/01/06
 * @author         Dr. Thorsten Ludewig <t.ludewig@gmail.com>
 */
@SpringBootApplication
public class HttpSmtpGatewayApplication
{

  /** Field description */
  private final static Properties CONFIG = new Properties();

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param args
   *
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void main(String[] args)
    throws FileNotFoundException, IOException
  {
    CONFIG.load(new FileInputStream("config/mail.properties"));
    SpringApplication.run(HttpSmtpGatewayApplication.class, args);
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  public static Properties getConfiguration()
  {
    return CONFIG;
  }

  /**
   * Method description
   *
   *
   * @param key
   * @param value
   *
   * @return
   */
  public static String getValue(String key, String value)
  {
    return (Strings.isNullOrEmpty(value))
      ? CONFIG.getProperty(key)
      : value;
  }
}
