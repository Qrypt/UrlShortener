package qrypt.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public String create(Account account) {
        accountRepository.save(account);
        return "home";
    }
}
