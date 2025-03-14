package com.example.java11;

import java.util.stream.Collectors;

/**
 * Demonstrates new String methods introduced in Java 11:
 * - isBlank()
 * - lines()
 * - strip(), stripLeading(), stripTrailing()
 * - repeat(int)
 */
public class StringMethodsExample {

    public static void main(String[] args) {
        demonstrateIsBlank();
        demonstrateLines();
        demonstrateStrip();
        demonstrateRepeat();
    }
    
    /**
     * isBlank() - Checks if a string is empty or contains only whitespace
     */
    private static void demonstrateIsBlank() {
        System.out.println("--- isBlank() Method ---");
        
        String empty = "";
        String blank = "   \t   \n   ";
        String notBlank = "Hello";
        
        System.out.println("Empty string is blank: " + empty.isBlank());        // true
        System.out.println("Whitespace string is blank: " + blank.isBlank());   // true
        System.out.println("Non-blank string is blank: " + notBlank.isBlank()); // false
        
        // Comparison with isEmpty()
        System.out.println("\nComparison with isEmpty():");
        System.out.println("Empty string isEmpty(): " + empty.isEmpty());       // true
        System.out.println("Whitespace string isEmpty(): " + blank.isEmpty());  // false
        
        // Practical example
        String userInput = "   ";
        if (userInput.isBlank()) {
            System.out.println("\nUser input is blank, please enter some text.");
        }
        
        System.out.println();
    }
    
    /**
     * lines() - Returns a stream of lines extracted from the string
     * (using line terminators \n, \r, or \r\n)
     */
    private static void demonstrateLines() {
        System.out.println("--- lines() Method ---");
        
        String multilineString = "Line 1\nLine 2\r\nLine 3\rLine 4";
        
        System.out.println("Original multiline string:");
        System.out.println(multilineString);
        
        System.out.println("\nLines extracted as a stream:");
        multilineString.lines()
                      .map(line -> "* " + line)
                      .forEach(System.out::println);
        
        // Count the lines
        long lineCount = multilineString.lines().count();
        System.out.println("\nNumber of lines: " + lineCount);
        
        // Process the lines
        String processed = multilineString.lines()
                                         .map(String::toUpperCase)
                                         .collect(Collectors.joining(" | "));
        System.out.println("\nProcessed lines: " + processed);
        
        // Practical example: Parse a CSV string
        String csvData = "id,name,email\n1,John,john@example.com\n2,Alice,alice@example.com";
        System.out.println("\nParsing CSV data:");
        csvData.lines()
              .skip(1) // Skip header
              .map(line -> line.split(","))
              .forEach(fields -> System.out.println("User: " + fields[1] + ", Email: " + fields[2]));
        
        System.out.println();
    }
    
    /**
     * strip(), stripLeading(), stripTrailing() - Remove whitespace from strings
     * These methods handle Unicode whitespace correctly, unlike trim()
     */
    private static void demonstrateStrip() {
        System.out.println("--- strip() Methods ---");
        
        String text = "  \u2000 Hello, World! \u2000  ";
        
        System.out.println("Original string: '" + text + "'");
        System.out.println("strip(): '" + text.strip() + "'");
        System.out.println("stripLeading(): '" + text.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + text.stripTrailing() + "'");
        
        // Comparison with trim()
        System.out.println("\nComparison with trim():");
        System.out.println("trim(): '" + text.trim() + "'");
        
        // Unicode whitespace handling differences
        String unicodeWhitespace = "\u2000Hello\u2000";
        System.out.println("\nUnicode whitespace handling:");
        System.out.println("Unicode string: '" + unicodeWhitespace + "'");
        System.out.println("strip(): '" + unicodeWhitespace.strip() + "'");
        System.out.println("trim(): '" + unicodeWhitespace.trim() + "'");
        
        // Practical application
        String userInput = "   user@example.com   ";
        String cleanedEmail = userInput.strip();
        System.out.println("\nCleaned user input: '" + cleanedEmail + "'");
        
        System.out.println();
    }
    
    /**
     * repeat(int) - Returns a string whose value is the concatenation
     * of the original string repeated n times
     */
    private static void demonstrateRepeat() {
        System.out.println("--- repeat() Method ---");
        
        String star = "*";
        String dash = "-";
        String hello = "Hello";
        
        System.out.println("star.repeat(5): " + star.repeat(5));
        System.out.println("dash.repeat(10): " + dash.repeat(10));
        System.out.println("hello.repeat(3): " + hello.repeat(3));
        
        // repeat(0) returns an empty string
        System.out.println("hello.repeat(0): '" + hello.repeat(0) + "'");
        
        // Practical examples
        
        // Create a separator line
        String separator = "=".repeat(40);
        System.out.println("\nSeparator line:");
        System.out.println(separator);
        
        // Create a simple text-based progress bar
        int progressPercentage = 60;
        int width = 20;
        int progress = width * progressPercentage / 100;
        
        String progressBar = "[" + 
                             "=".repeat(progress) + 
                             " ".repeat(width - progress) + 
                             "] " + progressPercentage + "%";
        
        System.out.println("\nText-based progress bar:");
        System.out.println(progressBar);
        
        // Create indentation for pretty printing
        String[] items = {"Apple", "Banana", "Cherry"};
        System.out.println("\nPretty printing list items:");
        for (int i = 0; i < items.length; i++) {
            String indentation = " ".repeat(4);
            System.out.println(indentation + (i + 1) + ". " + items[i]);
        }
        
        // Creating a simple tree structure
        System.out.println("\nSimple tree structure:");
        System.out.println("Root");
        System.out.println("│".repeat(1) + "└── Child 1");
        System.out.println("│".repeat(1) + "├── Child 2");
        System.out.println("│".repeat(1) + "│".repeat(1) + "└── Grandchild 1");
        System.out.println("│".repeat(1) + "│".repeat(1) + "└── Grandchild 2");
        System.out.println("│".repeat(1) + "└── Child 3");
    }
}
