package com.courseoutlinemanager.common;

import java.util.ArrayList;

public class ProcessString{
    /**
     * Compare all char of 2 String
     */
    public static boolean equalsByAlphabet(String s1, String s2) {
        s1 = s1.replaceAll("[\\s-_.;,]+", "");
        s2 = s2.replaceAll("[\\s-_.;,]+", "");
        return s1.compareToIgnoreCase(s2) == 0;
    }

    public static ArrayList<String> splitStringToParts(String input, int maxLength) {
        ArrayList<String> resultList = new ArrayList<>();
        String[] words = input.split("\\s+");

        StringBuilder currentSubstring = new StringBuilder();
        for (String word : words) {
            if (currentSubstring.length() + word.length() <= maxLength) {
                currentSubstring.append(word).append(" ");
            } else {
                resultList.add(currentSubstring.toString().trim());
                currentSubstring = new StringBuilder(word + " ");
            }
        }
        if (!currentSubstring.isEmpty()) {
            resultList.add(currentSubstring.toString().trim());
        }
        return resultList;
    }
    
    public static String printLabel(String label, String character, int maxLength) {
        int halfLineLength = (maxLength - label.length()) / 2;
        String horizontialLine = character.repeat(halfLineLength);
        return horizontialLine + label + horizontialLine;
    }
    
    public static ArrayList<String> printLine(String text, int indent, int maxLength, int indentSpace) {
        String indentString = " ".repeat(indentSpace * indent);
        ArrayList<String> parts = ProcessString.splitStringToParts(text, maxLength - 2 - indent * indentSpace);
        ArrayList<String> res = new ArrayList<>();
        for (String part : parts) {
            int remainLength = maxLength- 2 - part.length() - indentString.length();
            res.add("|" + indentString + part + " ".repeat(remainLength) + "|");
        }
        return res;
    }
    public static String printTable(String startText, String endText, int indent, int maxLength, int indentSpace) {
        String indentString = " ".repeat(indentSpace * indent);
        int remainLength = maxLength - 2 - startText.length() - indentString.length() - endText.length();
        return "|" + indentString + startText + " ".repeat(remainLength) + endText + "|";
    }

    public static String printTable(String startText, String middleText, String endText, int indent, int maxLength, int indentSpace) {
        String indentString = " ".repeat(indentSpace * indent);
        int spaceBetween = (maxLength - 2 - startText.length() - middleText.length() - endText.length()
                - indentString.length()) / 2;
        return "|" + indentString + startText + " ".repeat(spaceBetween) + middleText + " ".repeat(spaceBetween)
                + endText + "|";
    } 
    
}
