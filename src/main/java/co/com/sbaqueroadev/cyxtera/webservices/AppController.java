package co.com.sbaqueroadev.cyxtera.webservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String usuarios(){
        logger.info("New browser user session page request");
        return "index";
    }
}
