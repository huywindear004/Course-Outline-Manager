package com.courseoutlinemanager.common.customexception;

public class WrongFormatException extends Exception {
    public WrongFormatException() {
        super("Wrong format.");
    }

    public WrongFormatException(String message) {
        super(message);
    }
}

