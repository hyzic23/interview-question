package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BackBaseRepository extends JpaRepository<BackBaseModel, Long> {


    @Query("SELECT t FROM BackBaseModel t WHERE t.shortUrl = ?1")
    BackBaseModel getByShortUrl(String shortUrl);
    //List<BackBaseModel> getByShortUrl(String shortUrl);

    //@Query("DELETE FROM BackBaseModel t WHERE t.createdate(MINUTE,session_time,NOW()) > 30")
    //@Query("DELETE FROM BackBaseModel t WHERE DATEPART(MINUTE, ClockOut)(MINUTE,t.createdate,NOW()) > 30")
    //void deleteAll(@Param("date") Date date);





}
