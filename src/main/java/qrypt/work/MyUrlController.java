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

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class MyUrlController {
    private final String GOOGLE_API_KEY = "AIzaSyDnLjS_ypgz3omGtT3VKRBYGZKNl0XIf3E";

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
