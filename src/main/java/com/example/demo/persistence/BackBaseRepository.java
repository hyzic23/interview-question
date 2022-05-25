package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BackBaseRepository extends JpaRepository<BackBaseModel, Long> {

    /**
     * This getByShortUrl fetches/select record from database using shorturl as criteria
     *
     * @param shortUrl is the parameter being passed as criteria
     * @return returns a record in the BackBaseModel entity
     */
    @Query("SELECT t FROM BackBaseModel t WHERE t.shortUrl = ?1")
    BackBaseModel getByShortUrl(String shortUrl);

    /**
     * The deleteAll method deletes all records from the table where createdate is greater than 30 minutes
     */
    @Modifying
    @Query(
            value = "Delete FROM back_base_model t where DATEDIFF(MINUTE, t.createdate, NOW())  >= 30;",
            nativeQuery = true)
    void deleteAll();








}
