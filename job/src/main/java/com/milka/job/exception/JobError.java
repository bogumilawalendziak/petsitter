package com.milka.job.exception;

public enum JobError {

    INCORRECT_DATE("Choose correct date");

    private String message;

    JobError(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
