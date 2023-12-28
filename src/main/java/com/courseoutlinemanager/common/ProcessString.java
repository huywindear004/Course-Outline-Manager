package com.courseoutlinemanager.common;

public class ProcessString{
    /**
     * Compare all char of 2 String
     */
    public static boolean equalsByAlphabet(String s1, String s2) {
        s1 = s1.replaceAll("[\\s-_.;,]+", "");
        s2 = s2.replaceAll("[\\s-_.;,]+", "");
        return s1.compareToIgnoreCase(s2) == 0;
    }
}
