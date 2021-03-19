package com.milka.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<ErrorInfo> handleException(UserException e) {

        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (UserError.USER_NOT_FOUND.equals(e.getUserError())) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (UserError.EMAIL_NOT_UNIQUE.equals(e.getUserError())) {
            httpStatus = HttpStatus.CONFLICT;
        }
     else if (UserError.USER_IS_NOT_ACTIVE.equals(e.getUserError())) {
        httpStatus = HttpStatus.BAD_REQUEST;
    }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getUserError().getMessage()));
    }

}
