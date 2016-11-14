package qrypt.work;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller(value="account")
public class AccountController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("login", new Account());
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute Account account) {

        if(account.getUsername().equals("Fredrik") && account.getPassword().equals("hej")) {
            return "urlshortener";
        }

        return "loginFail";
    }

}
