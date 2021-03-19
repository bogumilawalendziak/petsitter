package com.milka.users.exception;

import lombok.Getter;

@Getter
public class ErrorInfo {

    private String message;

    public ErrorInfo(String message) {
        this.message = message;
    }
}
