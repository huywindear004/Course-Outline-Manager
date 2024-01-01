package com.courseoutlinemanager.common.customexception;

public class CancelInputException extends Exception{
    public CancelInputException() {
        super();
    }
    
    public CancelInputException(String message) {
        super(message);
    }
}