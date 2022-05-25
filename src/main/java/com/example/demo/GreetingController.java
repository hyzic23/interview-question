package com.example.demo;

import com.example.demo.persistence.BackBaseModel;
import com.example.demo.persistence.BackBaseService;
import com.example.demo.persistence.RecordNotFoundException;
import com.example.demo.utils.BackBaseUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

@RestController
public class GreetingController {

    @Autowired
    BackBaseUtil utils;

    @Autowired
    BackBaseService bbService;

    @Value("${backbase.my_secret:no yaml}")
    private String secret;

    @GetMapping("/getSecret")
    public String getSecret() {
        return String.format("Your secret is: %s", secret);
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name) {
        return "greeting " + name;
    }

    /**
     * This is API method takes in long url and converts it to short url
     * and saves it to database
     * @param url url is the parameter being passed
     * @return This returns a shortUrl
     */
    @GetMapping("/short")
    public String shortUrl(@RequestParam(name = "url", required = false) String url) {
            String shortUrl = utils.shortenURL(url);
            BackBaseModel request = new BackBaseModel();

            request.setUrl(url);
            request.setShortUrl(shortUrl);

            //request.setCreatedate(LocalDateTime.now());

            BackBaseModel model = bbService.createUrl(request);
            return shortUrl;
    }

    /**
     * This API method takes in short url and returns the original url
     * @param url url is the parameter being passed
     * @return This returns the original Url
     */
    @GetMapping("/long")
    public String longUrl(@RequestParam(name = "tiny", required = false) String url) {
        String value = utils.expandURL(url);
        return value;
    }

    /**
     * This API method takes in short url and if not found returns URL not found in DB
     * @param url shortUrl is the parameter being passed
     * @return This returns URL not found in DB if not exist
     */
    @GetMapping("/getByShortUrl")
    public String getByShortUrl(@RequestParam(name = "shortUrl", required = false) String url) throws RecordNotFoundException {
        String models = bbService.getByShortUrl(url);
        return models;
    }






}
