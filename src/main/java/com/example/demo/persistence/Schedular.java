package com.example.demo.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * This class is created for scheduling jobs
 */
@Component
public class Schedular {

    @Autowired
    BackBaseService service;

    //Logger logger = LoggerFactory.getLogger(Schedular.class);


    /**
     * This cronJobSch runs every 1minutes and runs the deleteAll method to delete
     * every records in the database that is greater than 1/2 hour
     */
    @Scheduled(cron = "0 * * * * *")
    public void cronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();

        service.deleteAll();

        //String strDate = sdf.format(now);
        //System.out.println("Running @ " + strDate);
    }


}
