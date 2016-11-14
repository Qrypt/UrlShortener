package qrypt.work;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    String home() {
        return "home";
    }

    @RequestMapping("/login")
    String login() {
        return "login";
    }

}
