package com.btc.thewayhome;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Controller
public class HomeController {

//    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @GetMapping({"", "/"})
    public String home() {
//        System.out.println("[HomeController] home()");
//        log.trace("[HomeController] home()");
//        log.debug("[HomeController] home()");
        log.info("[HomeController] home()");
//        log.warn("[HomeController] home()");
//        log.error("[HomeController] home()");

        return "home";
    }

}
