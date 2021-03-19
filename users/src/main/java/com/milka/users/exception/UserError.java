package com.milka.users.exception;

import lombok.Getter;

@Getter

public enum UserError {
    USER_NOT_FOUND("user does not exist"),
    EMAIL_NOT_UNIQUE("email is not unique"),
    USER_IS_NOT_ACTIVE("user has status inactive");

    UserError(String message) {
        this.message = message;
    }

    private String message;

}
