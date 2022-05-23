package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BackBaseService {

    @Autowired
    BackBaseRepository repository;


    //This is method is used to fetch/get all records in the database table
    public List<BackBaseModel> getAllEmployees()
    {
        List<BackBaseModel> backBaseUrlList = repository.findAll();

        if(backBaseUrlList.size() > 0) {
            return backBaseUrlList;
        } else {
            return new ArrayList<BackBaseModel>();
        }
    }

    //This method is used to get record using id
    public BackBaseModel getBackBaseUrlById(Long id) throws RecordNotFoundException
    {
        Optional<BackBaseModel> backBaseUrl = repository.findById(id);

        if(backBaseUrl.isPresent()) {
            return backBaseUrl.get();
        } else {
            throw new RecordNotFoundException("No URL record exist for given id");
        }
    }

    //This method is used to insert into Database table
    public BackBaseModel createUrl(BackBaseModel request) throws RecordNotFoundException
    {
        BackBaseModel model = repository.save(request);
        return model;
    }


    //Method to check get shortUrl from Database table
    public String getByShortUrl(String shortUrl) throws RecordNotFoundException
    {
        BackBaseModel model = repository.getByShortUrl(shortUrl);
        if(model != null){
            return model.getUrl();
        } else {
            return "URL not found in DB";
        }
    }



//    public List<BackBaseModel> getByShortUrl(String shortUrl) throws RecordNotFoundException
//    {
//        List<BackBaseModel> model = repository.getByShortUrl(shortUrl);
//        if(model.size() > 0){
//            //return (List<BackBaseModel>) model.get(0);
//            return model;
//        } else {
//            throw new RecordNotFoundException("URL not found in DB");
//        }
//    }



}
