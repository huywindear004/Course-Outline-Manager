package com.courseoutlinemanager.common.customexception;

public class OutOfCapacityException extends Exception {
    public OutOfCapacityException() {
        super("Out of capacity.");
    }

    public OutOfCapacityException(String message) {
        super(message);
    }
}






