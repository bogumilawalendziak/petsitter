package com.milka.job.exception;

public class JobErrorInfo {
   private String message;

    public JobErrorInfo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
