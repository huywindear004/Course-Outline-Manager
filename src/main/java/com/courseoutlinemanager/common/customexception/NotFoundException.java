package com.courseoutlinemanager.common.customexception;

public class NotFoundException extends Exception {
    public NotFoundException() {
        super("Not found!");
    }
}