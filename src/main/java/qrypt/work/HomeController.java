package qrypt.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    private AccountRepository accountRepository;
    private MyUrlRepository myUrlRepository;


    //går detta att göra lokalt i AccountController resp. MyUrlController?
    @Autowired
    public HomeController(AccountRepository accountRepository, MyUrlRepository myUrlRepository) {
        this.accountRepository = accountRepository;
        this.myUrlRepository = myUrlRepository;
    }

    @RequestMapping(value = {"/", "/home"})
    String home() {
        return "home";
    }

    @RequestMapping(value = "/login")
    String login() {
        return "login";
    }


    @RequestMapping(value = "/welcome")
    String welcome() {
        return "welcome";
    }



    //Gör det möjlig att göra anrop mot Account-objekt i account.html
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountForm(Model model) {
        model.addAttribute("account", new Account());
        return "account";
    }

    @RequestMapping(value = "/myurl", method = RequestMethod.GET)
    public String myUrlForm(Model model) {
        model.addAttribute("myurl", new MyUrl());
        model.addAttribute("myurllist", myUrlRepository.findAll());
        return "myurl";
    }
}
