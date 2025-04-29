package com.neosoft.exception;

public class UserNotFoundException extends RuntimeException{
    public static final String USER_NOT_FOUND = "User Not Found";
    public UserNotFoundException(){
        super(USER_NOT_FOUND);
    }
    public UserNotFoundException(String message){
        super(message);
    }
}
