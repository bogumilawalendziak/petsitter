package com.milka.job.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JobExceptionHandler {
    HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    @ExceptionHandler
    public ResponseEntity<JobErrorInfo> handlerException(JobException e) {
        if (JobError.INCORRECT_DATE.equals(e.getJobError())) {
            httpStatus = HttpStatus.CONFLICT;
        } else if (JobError.JOB_NOT_EXIST.equals(e.getJobError())) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(httpStatus).body(new JobErrorInfo(e.getJobError().getMessage()));
    }

}
