package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * BaskBaseService is a class where the business logic is being implemented
 */
@Service
public class BackBaseService {

    @Autowired
    BackBaseRepository repository;

    /**
     * This method is used to fetch/get all records in the database table
     * @return This returns a List of BackBaseModel
     */
    public List<BackBaseModel> getAllEmployees()
    {
        List<BackBaseModel> backBaseUrlList = repository.findAll();

        if(backBaseUrlList.size() > 0) {
            return backBaseUrlList;
        } else {
            return new ArrayList<BackBaseModel>();
        }
    }

    /**
     * This method is used to get record from database using id as criteria
     * @param id id is used as criteria to get record
     * @return This returns record in BackBaseModel entity
     * @throws RecordNotFoundException If record doesnot exist it returns RecordNotFoundException
     */
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


    /**
     * This method is use to add/save BackBaseModel entity to database
     * @param request This is the payload being inserted to the database
     * @return
     */
    public BackBaseModel createUrl(BackBaseModel request)
    {
        BackBaseModel model = repository.save(request);
        return model;
    }

    /**
     * This method is used to fetch record from database passing shortUrl as criteria
     * @param shortUrl is a param using as criteria to fetch database
     * @return returns record as a String
     * @throws RecordNotFoundException If record doesnot exist it returns RecordNotFoundException
     */
    public String getByShortUrl(String shortUrl) throws RecordNotFoundException
    {
        BackBaseModel model = repository.getByShortUrl(shortUrl);
        if(model != null){
            return model.getUrl();
        } else {
            return "URL not found in DB";
        }
    }

    /**
     * This calls the deleteAll method from the BackBaseRepository interface
     */
    @Transactional
    public void deleteAll(){
        //LocalDateTime thirtyDates = LocalDateTime.now().plusMinutes(-30);
        //repository.deleteAll(thirtyDates);
        repository.deleteAll();
    }






}
