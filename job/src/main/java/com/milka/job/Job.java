package com.milka.job;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long jobId;
    LocalDate date;
    Double price;
    String city;
    long petsitterId;
    long userId;


}
