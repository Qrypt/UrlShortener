package qrypt.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/account")
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
    public String create(Model model, Account account) {

        if(accountRepository.findByUsername(account.getUsername()) != null) {
            model.addAttribute("message", "User already exists");
            return "account";
        }
        if(account.getUsername().equals("")) {
            model.addAttribute("message", "A username must be added");
            return "account";
        }
        if(account.getPassword().equals("")) {
            model.addAttribute("message", "A password must be added");
            return "account";
        }
        else {
            accountRepository.save(account);
            return "home";
        }
    }
}
