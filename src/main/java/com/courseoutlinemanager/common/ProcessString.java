package com.courseoutlinemanager.common;

import static com.courseoutlinemanager.common.output.ConsoleOutput.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ProcessString {
    /**
     * Compare all letters of 2 String
     */
    public static boolean equalsByAlphabet(String s1, String s2) {
        s1 = s1.replaceAll("[\\s-_.;,:]+", "");
        s2 = s2.replaceAll("[\\s-_.;,:]+", "");
        return s1.compareToIgnoreCase(s2) == 0;
    }

    public static boolean containsByAlphabet(String containString, String toCheck) {
        containString = containString.replaceAll("[\\s-_.;,:]+", "").toLowerCase();
        toCheck = toCheck.replaceAll("[\\s-_.;,:]+", "").toLowerCase();
        return containString.contains(toCheck);
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
        int length = label.length();
        int halfLineLength = (maxLength - length) / 2;
        String horizontialLine = character.repeat(halfLineLength);
        if (length % 2 != 0)
            return horizontialLine + label + horizontialLine + character;
        return horizontialLine + label + horizontialLine;
    }

    public static String printLabel(String label, String character) {
        return printLabel(label, character, getWidth());
    }

    public static String printLine(String text, int indent, int maxLength, int indentSpace) {
        String indentString = " ".repeat(indentSpace * indent);
        ArrayList<String> parts = ProcessString.splitStringToParts(text, maxLength - 2 - indent * indentSpace);
        ArrayList<String> res = new ArrayList<>();
        for (String part : parts) {
            int remainLength = maxLength - 2 - part.length() - indentString.length();
            res.add("|" + indentString + part + " ".repeat(remainLength) + "|");
        }
        return stringConcat(res);
    }

    public static String printLine(String text, int indent) {
        return printLine(text, indent, getWidth(), getIndentSpace());
    }

    public static String printTable(String startText, String endText, int indent, int maxLength, int indentSpace) {
        String indentString = " ".repeat(indentSpace * indent);
        int remainLength = maxLength - 2 - startText.length() - indentString.length() - endText.length();
        return "|" + indentString + startText + " ".repeat(remainLength) + endText + "|";
    }

    public static String printTable(String startText, String midText, String endText, int indent, int maxLength,
            int indentSpace) {
        String indentString = " ".repeat(indentSpace * indent);
        int spaceBetween = (maxLength - 2 - startText.length() - midText.length() - endText.length()
                - indentString.length()) / 2;
        return "|" + indentString + startText + " ".repeat(spaceBetween) + midText + " ".repeat(spaceBetween)
                + endText + "|";
    }

    public static String printTable(String startText, String endText, int indent) {
        return printTable(startText, endText, indent, getWidth(), getIndentSpace());
    }

    public static String printTable(String startText, String midText, String endText, int indent) {
        return printTable(startText, midText, endText, indent, getWidth(), getIndentSpace());
    }// làm cái 4 tham số nữa à
    
    public static String toPascalCase(String text) {
        if (text == null || text.isEmpty())
            return "";
        String[] words = text.trim().split("[\\s_-]+");
        StringBuilder res = new StringBuilder();
        for (String word : words) {
            res.append(Character.toUpperCase(word.charAt(0)));
            res.append(word.substring(1).toLowerCase());
        }
        return res.toString();
    }
    

    public static String stringConcat(ArrayList<String> strings) {
        StringBuilder temp = new StringBuilder();
        if (strings.size() == 1)
            return strings.get(0);
        else
            for (String line : strings)
                temp.append(line + "\n");
        return temp.toString().trim();
    }

    public static ArrayList<String> getFileNames(String path, String... excludedString) {
        File folder = new File(path);
        ArrayList<String> excludedStrings = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        excludedStrings.addAll(Arrays.asList(excludedString));
        String[] temp = folder.list();
        for (String i : temp) {
            boolean isExcluded = false;
            for (String exclStr : excludedStrings) {
                if (equalsByAlphabet(i, exclStr)) {
                    isExcluded = true;
                    break;
                }
            }
            if (!isExcluded)
                res.add(i);
        }
        return res;
    }

    public static String orderedList(ArrayList<String> texts, int indents) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < texts.size(); i++)
            res.append(printLine((i + 1) + ". " + texts.get(i), indents)).append("\n");
        return res.toString();
    }

    public static String orderedList(String[] texts, int indents) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < texts.length; i++)
            res.append(printLine((i + 1) + ". " + texts[i], indents)).append("\n");
        return res.toString();
    }

    public static String unorderedList(ArrayList<String> texts, int indents) {
        StringBuilder res = new StringBuilder();
        for (String line : texts)
            res.append(printLine("- " + line, indents)).append("\n");
        return res.toString();
    }
}
