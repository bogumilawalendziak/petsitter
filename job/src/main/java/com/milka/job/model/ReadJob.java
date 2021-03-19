package com.milka.job.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ReadJob {

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
    String employerName;

}
