package qrypt.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {
    private MyUrlRepository myUrlRepository;

    @Autowired
    public HomeController(MyUrlRepository myUrlRepository) {
        this.myUrlRepository = myUrlRepository;
    }

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    String home() {
        return "home";
    }

    @RequestMapping(value = {"/myurl/go", }, method = RequestMethod.GET)
    public String goToUrl() {
        return "myurl/go";
    }

    @RequestMapping(value = "/login")
    String login() {
        return "login";
    }


    @RequestMapping(value = "/welcome")
    String welcome() {
        return "welcome";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountForm(Model model) {
        model.addAttribute("account", new Account());
        return "account";
    }

    @RequestMapping(value = "/myurl", method = RequestMethod.GET)
    public String myUrlForm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        model.addAttribute("myurl", new MyUrl());

        List<MyUrl> myUrlList = myUrlRepository.findByAccountUsername(currentUser);
        Collections.sort(myUrlList, (o1, o2) -> o1.getId().compareTo(o2.getId()));

        model.addAttribute("myurllist", myUrlList);

        return "myurl";
    }
}
