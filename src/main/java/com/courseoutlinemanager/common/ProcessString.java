package com.courseoutlinemanager.common;

public class ProcessString{
    /**
     * Compare all char of 2 String
     */
    public static boolean compare(String s1, String s2) {
        String newS1 = s1.replaceAll("[\\s_.;,]+", "").toLowerCase();
        String newS2 = s2.replaceAll("[\\s_.;,]+", "").toLowerCase();
        return newS1.equals(newS2);
    }
}
