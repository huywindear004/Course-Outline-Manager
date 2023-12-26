package com.courseoutlinemanager.common.customexception;

public class AlreadyExistException extends Exception{
    public AlreadyExistException() {
        super("This is already exist!");
    }
}