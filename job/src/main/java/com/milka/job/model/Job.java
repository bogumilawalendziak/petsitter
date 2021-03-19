package com.milka.job.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long jobId;
    @Future
    @NotNull
    LocalDate startDate;
    @Future
    @NotNull
    LocalDate endDate;
    @NotNull
    @Min(0)
    Double price;
    @NotBlank
    String city;
    @NotNull
    long petsitterId;
    @NotNull
    long userId;
    @NotNull
    Status status;


}
