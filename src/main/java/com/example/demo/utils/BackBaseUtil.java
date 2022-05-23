package com.example.demo.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class BackBaseUtil
{

    // storage for generated keys
    private HashMap<String, String> keyMap; // key-url map
    private HashMap<String, String> valueMap;// url-key map to quickly check
    // whether an url is
    // already entered in our system
    private String domain; // Use this attribute to generate urls for a custom
    // domain name defaults to http://fkt.in
    private char myChars[]; // This array is used for character to number
    // mapping
    private Random myRand; // Random object used to generate random integers
    private int keyLength; // the key length in URL defaults to 8

    // Default Constructor
    BackBaseUtil() {
        keyMap = new HashMap<String, String>();
        valueMap = new HashMap<String, String>();
        myRand = new Random();
        keyLength = 8;
        myChars = new char[62];
        for (int i = 0; i < 62; i++) {
            int j = 0;
            if (i < 10) {
                j = i + 48;
            } else if (i > 9 && i <= 35) {
                j = i + 55;
            } else {
                j = i + 61;
            }
            myChars[i] = (char) j;
        }
        // domain = "http://fkt.in";
    }

    // Constructor which enables you to define tiny URL key length and base URL
    // name

    BackBaseUtil(int length) {
        this();
        this.keyLength = length;
    }


//    URLShortener(int length, String newDomain) {
//        this();
//        this.keyLength = length;
//        if (!newDomain.isEmpty()) {
//            newDomain = sanitizeURL(newDomain);
//            domain = newDomain;
//        }
//    }

//    URLShortener(int length) {
//        //this();
//        this.keyLength = length;
//        if (!newDomain.isEmpty()) {
//            newDomain = sanitizeURL(newDomain);
//            domain = newDomain;
//        }
//    }

    // shortenURL
    // the public method which can be called to shorten a given URL
    public String shortenURL(String longURL) {
        String shortURL = "";
        if (validateURL(longURL)) {
            longURL = sanitizeURL(longURL);
            if (valueMap.containsKey(longURL)) {
                //shortURL = domain + "/" + valueMap.get(longURL);
                shortURL = valueMap.get(longURL);
            } else {
                //shortURL = domain + "/" + getKey(longURL);
                shortURL = getKey(longURL);
            }
        }
        // add http part
        return shortURL;
    }

    // expandURL
    // public method which returns back the original URL given the shortened url
    public String expandURL(String shortURL) {
        String longURL = "";
        String key = shortURL;
        //String key = shortURL.substring(domain.length() + 1);
        longURL = keyMap.get(key);
        return longURL;
    }

    // Validate URL
    // not implemented, but should be implemented to check whether the given URL
    // is valid or not
    boolean validateURL(String url) {
        if(!url.isEmpty())
            return true;
        return false;
    }

    // sanitizeURL
    // This method should take care various issues with a valid url
    // e.g. www.google.com,www.google.com/, http://www.google.com,
    // http://www.google.com/
    // all the above URL should point to same shortened URL
    // There could be several other cases like these.
    String sanitizeURL(String url) {
        if (url.substring(0, 7).equals("http://"))
            url = url.substring(7);

        if (url.substring(0, 8).equals("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

    /*
     * Get Key method
     */
    private String getKey(String longURL) {
        String key;
        key = generateKey();
        keyMap.put(key, longURL);
        valueMap.put(longURL, key);
        return key;
    }

    // generateKey
    private String generateKey() {
        String key = "";
        boolean flag = true;
        while (flag) {
            key = "";
            for (int i = 0; i <= keyLength; i++) {
                key += myChars[myRand.nextInt(62)];
            }
            // System.out.println("Iteration: "+ counter + "Key: "+ key);
            if (!keyMap.containsKey(key)) {
                flag = false;
            }
        }
        return key;
    }









//    private static final String ALGORITHM = "md5";
//    private static final String DIGEST_STRING = "HG58YZ3CR9";
//    private static final String CHARSET_UTF_8 = "utf-8";
//    private static final String SECRET_KEY_ALGORITHM = "DESede";
//    private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

    /* Encryption Method */
//    public byte[] encrypt(String message) throws Exception
//    {
//        final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
//        final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8));
//        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//        for (int j = 0, k = 16; j < 8;) {
//            keyBytes[k++] = keyBytes[j++];
//        }
//        System.out.println(new String(keyBytes));
//        final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
//        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//        final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING);
//        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//
//        final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8);
//        System.out.println(new String(plainTextBytes));
//        final byte[] cipherText = cipher.doFinal(plainTextBytes);
//
//        //BASE64Encoder base64encoder = new BASE64Encoder();
//        //return base64encoder.encode(cipherText);
//        return cipherText;
//    }



    /* Decryption Method */
//    public String decrypt(byte[] message) throws Exception {
//        final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
//        final byte[] digestOfPassword = md.digest(DIGEST_STRING.getBytes(CHARSET_UTF_8));
//        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//        for (int j = 0, k = 16; j < 8;) {
//            keyBytes[k++] = keyBytes[j++];
//        }
//        System.out.println(new String(keyBytes));
//        final SecretKey key = new SecretKeySpec(keyBytes, SECRET_KEY_ALGORITHM);
//        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//        final Cipher decipher = Cipher.getInstance(TRANSFORMATION_PADDING);
//        decipher.init(Cipher.DECRYPT_MODE, key, iv);
//
//        final byte[] plainText = decipher.doFinal(message);
//
//        return new String(plainText);
//    }




}