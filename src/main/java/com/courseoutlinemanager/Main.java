package com.courseoutlinemanager;

import com.courseoutlinemanager.common.customexception.CancelInputException;
import com.courseoutlinemanager.common.customexception.NotFoundException;

public class Main {
    public static void main(String[] args) throws NotFoundException, CancelInputException {
        new MainManager().run();
        // cho phép ghi terminal kìa chạy ct xong cho phép rroi 

    }
}
