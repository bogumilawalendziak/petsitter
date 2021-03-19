package com.milka.job.exception;

import lombok.Getter;

@Getter
public class JobException extends RuntimeException{
    private JobError jobError;


   public JobException(JobError jobError){
       this.jobError = jobError;
   }

}
