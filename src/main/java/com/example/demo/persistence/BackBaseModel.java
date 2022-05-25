package com.example.demo.persistence;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * BackBaseModel is an entity/model representing my table in the database
 *
 */


@Data
@Entity
public class BackBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private String shortUrl;

    @CreationTimestamp
    private LocalDateTime createdate;
    //@Temporal(TemporalType.TIMESTAMP)
    //private Timestamp createdate;
    //private LocalDateTime createdate;



}
