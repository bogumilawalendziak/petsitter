package com.milka.users.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private UserError userError;

    public UserException(UserError userError){
        this.userError=userError;
    }


}
