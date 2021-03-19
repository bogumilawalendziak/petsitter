package com.milka.job.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class WriteJob {

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
    long userId;
    @NotNull
    Status status;

    public WriteJob(@Future @NotNull LocalDate startDate, @Future @NotNull LocalDate endDate, @NotNull @Min(0) Double price, @NotBlank String city, @NotNull long userId, @NotNull Status status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.city = city;
        this.userId = userId;
        this.status = status;
    }

    // z góry określić status
}
