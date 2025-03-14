package com.example.java9;

/**
 * Information about JShell (REPL - Read-Eval-Print Loop) introduced in Java 9.
 * 
 * Note: JShell is an interactive tool, so we can't directly demonstrate it in code.
 * This class provides information about JShell and examples of commands you can run.
 */
public class JShellExample {

    public static void main(String[] args) {
        System.out.println("JShell: Java's Interactive REPL (Java 9)");
        System.out.println("----------------------------------------");
        System.out.println("JShell is a command-line tool that provides an interactive");
        System.out.println("shell for quickly prototyping, exploring, and testing Java code.");
        System.out.println();
        System.out.println("To use JShell, open a terminal and type:");
        System.out.println("  jshell");
        System.out.println();
        System.out.println("Here are some examples of what you can do in JShell:");
        
        printExample("Basic expressions",
                "2 + 2",
                "Math.sqrt(16)",
                "\"Hello\".length()"
        );
        
        printExample("Variable declarations",
                "var x = 10",
                "String name = \"Java\"",
                "List<Integer> numbers = List.of(1, 2, 3)"
        );
        
        printExample("Method declarations",
                "int sum(int a, int b) {",
                "    return a + b;",
                "}",
                "",
                "sum(5, 7)"
        );
        
        printExample("Class declarations",
                "class Person {",
                "    private String name;",
                "    public Person(String name) {",
                "        this.name = name;",
                "    }",
                "    public String greet() {",
                "        return \"Hello, \" + name;",
                "    }",
                "}",
                "",
                "var person = new Person(\"John\")",
                "person.greet()"
        );
        
        printExample("Imports",
                "/import java.util.stream.IntStream",
                "",
                "IntStream.range(1, 5).sum()"
        );
        
        printExample("JShell Commands",
                "/list            // List all snippets",
                "/vars            // List all variables",
                "/methods         // List all methods",
                "/types           // List all types",
                "/imports         // List all imports",
                "/edit            // Edit a snippet",
                "/drop            // Delete a snippet",
                "/save file.jsh   // Save snippets to a file",
                "/open file.jsh   // Load snippets from a file",
                "/exit            // Exit JShell"
        );
        
        System.out.println();
        System.out.println("JShell Advantages:");
        System.out.println("- Quick experimentation with Java code");
        System.out.println("- No need for full class/method declarations");
        System.out.println("- Instant feedback");
        System.out.println("- Easy exploration of APIs");
        System.out.println("- Great for learning Java");
        System.out.println();
        System.out.println("Try JShell for yourself to experience its capabilities!");
    }
    
    private static void printExample(String title, String... lines) {
        System.out.println();
        System.out.println("# " + title);
        System.out.println("```");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("```");
    }
}
