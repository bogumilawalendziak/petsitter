package com.milka.job.exception;

public enum JobError {

    INCORRECT_DATE("Choose correct date"),
    JOB_NOT_EXIST("Job with given id not exist");

    private String message;

    JobError(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
