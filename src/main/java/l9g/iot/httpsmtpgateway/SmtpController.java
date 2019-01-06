package l9g.iot.httpsmtpgateway;

//~--- non-JDK imports --------------------------------------------------------

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//~--- JDK imports ------------------------------------------------------------

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author th
 */
@Controller
public class SmtpController
{

  /** Field description */
  private static long threadCounter = 0;

  /** Field description */
  private final static Logger LOGGER = LoggerFactory.getLogger(
    SmtpController.class);

  /** Field description */
  private final static SimpleDateFormat TIMSTAMP_FORMAT = new SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss");

  //~--- methods --------------------------------------------------------------

  /**
   * Method description
   *
   *
   * @param from
   * @param to
   * @param subject
   * @param message
   * @param configurationId
   *
   * @return
   */
  @RequestMapping(
    value = "/sm",
    method = RequestMethod.GET,
    produces = "text/plain"
  )
  @ResponseBody
  public String sendmail(@RequestParam(name = "fr",
    required = false) final String from, @RequestParam(name = "to",
      required = false) final String to, @RequestParam(name = "su",
        required = false) final String subject, @RequestParam(name = "me",
          required = false) final String message, @RequestParam(name = "ci",
            required = false) final String configurationId)
  {
    Thread sender;

    sender = new Thread(
      () -> {
        try
        {
          Properties config = HttpSmtpGatewayApplication.getConfiguration();

          InternetAddress iaTo = new InternetAddress(
            HttpSmtpGatewayApplication.getValue("default.to",
              to));

          String mailFrom = HttpSmtpGatewayApplication.getValue("default.from",
            from);

          InternetAddress iaFrom = new InternetAddress(mailFrom + " <"
            + config.getProperty("mail.account.from") + ">");

          String mailSubject = HttpSmtpGatewayApplication.getValue(
            "default.subject", subject);

          if ("true".equalsIgnoreCase(config.getProperty(
            "default.subject.timestamp")))
          {
            mailSubject = "[" + TIMSTAMP_FORMAT.format(new Date()) + "] "
              + mailSubject;
          }

          String body = HttpSmtpGatewayApplication.getValue("default.message",
            message);

          LOGGER.info("mail from={}, to={}, subject={}, message={}",
            iaFrom.toString(), iaTo.toString(), subject, body);

          String user = config.getProperty("mail.account.user");
          String password = config.getProperty("mail.account.password");

          Session session = Session.getDefaultInstance(config);

          MimeMessage mimeMessage = new MimeMessage(session);

          mimeMessage.setContent(body, "text/plain");
          mimeMessage.setFrom(iaFrom);
          mimeMessage.setRecipient(Message.RecipientType.TO, iaTo);
          mimeMessage.setSubject(mailSubject);
          
          Transport.send(mimeMessage, user, password);

          LOGGER.info("message successfully send");
        }
        catch (AddressException ex)
        {
          LOGGER.error("Address error ", ex);
        }
        catch (MessagingException ex)
        {
          LOGGER.error("Messaging error ", ex);
        }
      });

    sender.setName("smtp-sender-" + getThreadCounter());
    sender.setDaemon(true);
    sender.start();

    return "OK";
  }

  //~--- get methods ----------------------------------------------------------

  /**
   * Method description
   *
   *
   * @return
   */
  private synchronized long getThreadCounter()
  {
    return ++threadCounter;
  }
}
