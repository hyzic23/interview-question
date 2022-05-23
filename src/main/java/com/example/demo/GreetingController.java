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


    @GetMapping("/short")
    public String shortUrl(@RequestParam(name = "url", required = false) String url) throws RecordNotFoundException {
           String shortUrl = utils.shortenURL(url);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            System.out.println("Timestamp " + timestamp);

            BackBaseModel request = new BackBaseModel();
            request.setUrl(url);
            request.setShortUrl(shortUrl);
            request.setCreatedate(timestamp);
            BackBaseModel model = bbService.createUrl(request);
            return shortUrl;
    }

    @GetMapping("/long")
    public String longUrl(@RequestParam(name = "tiny", required = false) String url) {
        String value = utils.expandURL(url);
        System.out.println("Long URL is " + value);
        return value;
    }

    @GetMapping("/getByShortUrl")
    public String getByShortUrl(@RequestParam(name = "shortUrl", required = false) String url) throws RecordNotFoundException {
        String models = bbService.getByShortUrl(url);
        System.out.println("Long URL is " + models);
        return models;
    }






}
