package com.courseoutlinemanager.common.customexception;

public class AlreadyExistException extends Exception{
    public AlreadyExistException() {
        super("This is already exist!");
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}