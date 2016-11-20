package qrypt.work;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@ControllerAdvice
@RequestMapping("/error")
public class ErrorHandler {

    @RequestMapping(value = "/url", method = RequestMethod.GET)
    public String noSuchUrl(Model model) {
        model.addAttribute("message", "There is no such URL.");
        return "error";
    }
}
