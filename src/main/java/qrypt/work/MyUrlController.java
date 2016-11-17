package qrypt.work;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping(value = "/myurl")
public class MyUrlController {
    /*
    Connect url för förkortning av url:

    https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyDnLjS_ypgz3omGtT3VKRBYGZKNl0XIf3E
     */

    private final String GOOGLE_API_KEY = "AIzaSyDnLjS_ypgz3omGtT3VKRBYGZKNl0XIf3E";

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
    public String create( MyUrl myUrl) {

        MyUrl tmpUrl = myUrl;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        tmpUrl.setAccountUsername(currentUser);

        try {
            tmpUrl.setShortened(getShortUrl(tmpUrl.getOriginal()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myUrlRepository.save(tmpUrl);
        return "redirect:/myurl";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST, params = {"urlId"})
    public String remove( @RequestParam(value = "urlId") Long myUrlid) {
        myUrlRepository.delete(myUrlid);
        return "redirect:/myurl";
    }

    private String getShortUrl(String longUrl) throws IOException {
        URL url = new URL("https://www.googleapis.com/urlshortener/v1/url?key=" + GOOGLE_API_KEY);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setDoInput(true);
        con.setDoOutput(true);
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        JSONObject urlData = new JSONObject();

        urlData.put("longUrl", longUrl);

        OutputStreamWriter writer =  new OutputStreamWriter(con.getOutputStream());
        writer.write(urlData.toString());
        writer.flush();


        StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if(HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));

            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        }

        JSONObject urlResponse = new JSONObject(sb.toString());
        String shortUrl = urlResponse.get("id").toString();

        System.out.println("SHORT URL: " + shortUrl);

        return shortUrl;
    }
}
