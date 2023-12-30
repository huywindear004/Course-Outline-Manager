import com.courseoutlinemanager.common.ProcessString;

import java.util.ArrayList;

public class test {
    private static final int WIDTH = 50;
    private static final int INDENT_SPACE = 4;

    public static void printLabel(String label, String character) {
        int halfLineLength = (WIDTH - label.length()) / 2;
        String horizontialLine = character.repeat(halfLineLength);
        System.out.println(horizontialLine + label + horizontialLine);
    }

    public static void printLine(String text, int indent) {
        int remainLength = WIDTH - 2 - text.length() - indent * INDENT_SPACE;
        String indentString = " ".repeat(INDENT_SPACE * indent);
        System.out.println("|"+ indentString + text + " ".repeat(remainLength)+"|");
    }

    public static void main(String[] args) {
        printLabel("De cuong mon hoc", "=");
        printLine("Course name: OOP",0);
        printLine("Course code:  ITEC2504",0);
        printLine("Mon hoc truoc: ",0);
        printLine("Ky thuat lap trinh",1);
        printLine("Ky thuat lap trinh",2);
        printLine("Ky thuat lap trinh",3);
        printLine("Ky thuat lap trinh",0);
        printLabel("","=");

    }
}
