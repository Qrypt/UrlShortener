package qrypt.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AccountController {
    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Account> getAll() {
        return accountRepository.findAll();
    }

    @RequestMapping(value = "/finduser/{username}", method = RequestMethod.GET)
    public @ResponseBody Account findByUserName(@PathVariable("username") String username) {
        return accountRepository.findByUsername(username);
    }

    @RequestMapping(value = ("/create"), method = RequestMethod.POST)
    public @ResponseBody List<Account> create(@RequestBody Account account) {
        accountRepository.save(account);
        return accountRepository.findAll();
    }

    @GetMapping("/")
    public String loginForm(Model model) {
        model.addAttribute("account", new Account());
        return "login";
    }

    @PostMapping("/")
    public String loginSubmit(@ModelAttribute Account account) {

        if(account.getUsername().equals("Fredrik") && account.getPassword().equals("hej")) {
            return "urlshortener";
        }

        return "loginFail";
    }

}
