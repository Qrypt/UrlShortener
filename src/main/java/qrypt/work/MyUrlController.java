package qrypt.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping(value = "/myurl")
public class MyUrlController {
    private MyUrlRepository myUrlRepository;

    @Autowired
    public MyUrlController(MyUrlRepository myUrlRepository) {
        this.myUrlRepository = myUrlRepository;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<MyUrl> getAll() {
        return myUrlRepository.findAll();
    }

    @RequestMapping(value = "/findbyid/{urlId}", method = RequestMethod.GET)
    public @ResponseBody MyUrl findByUrlId(@PathVariable("urlId") Long id) {
        return myUrlRepository.findById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(MyUrl myUrl) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        MyUrl tmpUrl = myUrl;

        boolean httpCheck = tmpUrl.getOriginal().startsWith("http://");
        boolean httpsCheck = tmpUrl.getOriginal().startsWith("https://");
        StringBuilder possibleProtocol = new StringBuilder(tmpUrl.getOriginal());
        if(!httpCheck && !httpsCheck) {
            possibleProtocol.insert(0, "http://");
            tmpUrl.setOriginal(possibleProtocol.toString());
        }

        tmpUrl.setAccountUsername(currentUser);
        tmpUrl.setShortened("temp");

        myUrlRepository.save(tmpUrl);

        Long id = tmpUrl.getId();
        byte[] idAsBytes = longToBytes(id);
        String encodedId = Base64.getUrlEncoder().encodeToString(idAsBytes);
        tmpUrl.setShortened(encodedId);

        myUrlRepository.save(tmpUrl);
        return "redirect:/myurl";
    }

    @RequestMapping(value = ("/go/{shorturl}"), method = RequestMethod.GET)
    public String gotToUrl(@PathVariable("shorturl") String shorturl) {
        MyUrl tmpUrl = myUrlRepository.findByShortened(shorturl);

        if(tmpUrl == null) {
            return "redirect:/error/url";
        }
        return "redirect:" + tmpUrl.getOriginal();
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, params = {"urlId"})
    public String remove( @RequestParam(value = "urlId") Long myUrlid) {
        myUrlRepository.delete(myUrlid);
        return "redirect:/myurl";
    }

    private byte[] longToBytes(long x) {
        ByteBuffer buffer;
        if(x < Byte.MAX_VALUE) {
            buffer = ByteBuffer.allocate(Byte.BYTES);
            buffer.put((byte) x);
        }
        else if(x < Short.MAX_VALUE) {
            buffer = ByteBuffer.allocate(Short.BYTES);
            buffer.putShort((short) x);
        }
        else if(x < Integer.MAX_VALUE) {
            buffer = ByteBuffer.allocate(Integer.BYTES);
            buffer.putInt((int) x);
        }
        else {
            buffer = ByteBuffer.allocate(Long.BYTES);
            buffer.putLong(x);
        }
        return buffer.array();
    }
}
