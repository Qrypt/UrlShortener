package qrypt.work;

/*
Google api fuckar upp tomcat av någon anledning...
 */

//import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
//import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.urlshortener.Urlshortener;
//import com.google.api.services.urlshortener.UrlshortenerScopes;
//import com.google.api.services.urlshortener.model.Url;
//import com.google.api.services.urlshortener.model.UrlHistory;

import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MyUrlController {
    private final String GOOGLE_API_KEY = "AIzaSyDnLjS_ypgz3omGtT3VKRBYGZKNl0XIf3E";

    private MyUrlRepository myUrlRepository;

    @Autowired
    public MyUrlController(MyUrlRepository myUrlRepository) {
        this.myUrlRepository = myUrlRepository;
    }

    @RequestMapping(value = "/myurl/all", method = RequestMethod.GET)
    public @ResponseBody List<MyUrl> getAll() {
        return myUrlRepository.findAll();
    }

    @RequestMapping(value = "/myurl/findbyid/{urlId}", method = RequestMethod.GET)
    public @ResponseBody MyUrl findByUrlId(@PathVariable("urlId") Long id) {
        return myUrlRepository.findById(id);
    }

    @RequestMapping(value = "/myurl/create", method = RequestMethod.POST)
    public @ResponseBody List<MyUrl> create(@RequestBody MyUrl myUrl) {
        myUrlRepository.save(myUrl);
        return myUrlRepository.findAll();
    }

    @GetMapping("/urlshortener")
    public String urlForm(Model model) {
        model.addAttribute("urlshortener", new MyUrl());
        return "urlshortener";
    }

    @PostMapping("/urlshortener")
    public String urlSubmit(@ModelAttribute MyUrl myUrl) {

        String urlOriginal = myUrl.getOriginal();

//        try {
//            ArrayList<String> urls = test(urlOriginal);
//            System.out.println();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "urlshortener";
    }

//    private Urlshortener newUrlshortener() {
//        AppIdentityCredential credential =
//                new AppIdentityCredential(Arrays.asList(UrlshortenerScopes.URLSHORTENER));
//        return new Urlshortener.Builder(new UrlFetchTransport(), new JacksonFactory(), credential)
//                .build();
//    }
//
//    private ArrayList<String> test(String longUrl) throws IOException {
//        Urlshortener shortener = newUrlshortener();
//        UrlHistory history = shortener.url().list().execute();
//        ArrayList<String> shortUrls = new ArrayList<>();
//
//        if (history.getItems() != null) {
//            for (Url oneShortened : history.getItems()) {
//                String shorter = oneShortened.getId();
//                shortUrls.add(shorter);
//            }
//        }
//        return shortUrls;
//    }
}
