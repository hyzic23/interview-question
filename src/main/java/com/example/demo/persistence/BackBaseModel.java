package com.example.demo.persistence;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class BackBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    private String shortUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdate;



}
