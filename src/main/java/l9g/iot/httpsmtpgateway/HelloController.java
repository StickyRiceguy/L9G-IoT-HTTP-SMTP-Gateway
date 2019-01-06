package l9g.iot.httpsmtpgateway;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from IoT HTTP to SMTP gateway.";
    }

}
