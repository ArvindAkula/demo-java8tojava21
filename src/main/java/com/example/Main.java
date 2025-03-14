package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main driver class for the Java 8 to Java 21 Features Demo.
 * Provides a menu-based interface to navigate and run examples for different Java features.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            printMainMenu();
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                exit = processMainMenuChoice(choice, scanner);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
        
        scanner.close();
        System.out.println("Thank you for exploring Java features!");
    }
    
    private static void printMainMenu() {
        System.out.println("\n===== Java 8 to Java 21 Features Demo =====");
        System.out.println("Choose a Java version to explore:");
        System.out.println("1. Java 8 (2014)");
        System.out.println("2. Java 9 (2017)");
        System.out.println("3. Java 10 (2018)");
        System.out.println("4. Java 11 (2018, LTS)");
        System.out.println("5. Java 12 (2019)");
        System.out.println("6. Java 13 (2019)");
        System.out.println("7. Java 14 (2020)");
        System.out.println("8. Java 15 (2020)");
        System.out.println("9. Java 16-17 (2021)");
        System.out.println("10. Java 18-20 (2022-2023)");
        System.out.println("11. Java 21 (2023, LTS)");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static boolean processMainMenuChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                showJava8Menu(scanner);
                return false;
            case 2:
                showJava9Menu(scanner);
                return false;
            case 3:
                showJava10Menu(scanner);
                return false;
            case 4:
                showJava11Menu(scanner);
                return false;
            case 5:
                showJava12Menu(scanner);
                return false;
            case 6:
                showJava13Menu(scanner);
                return false;
            case 7:
                showJava14Menu(scanner);
                return false;
            case 8:
                showJava15Menu(scanner);
                return false;
            case 9:
                showJava16_17Menu(scanner);
                return false;
            case 10:
                showJava18_20Menu(scanner);
                return false;
            case 11:
                showJava21Menu(scanner);
                return false;
            case 0:
                return true; // Exit the program
            default:
                System.out.println("Invalid choice. Please try again.");
                return false;
        }
    }
    
    // Java 8 menu
    private static void showJava8Menu(Scanner scanner) {
        System.out.println("\n----- Java 8 Features -----");
        System.out.println("1. Lambda Expressions & Functional Interfaces");
        System.out.println("2. Streams API");
        System.out.println("3. Default Methods in Interfaces");
        System.out.println("4. Date/Time API");
        System.out.println("5. Optional Class");
        System.out.println("6. Nashorn JavaScript Engine");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java8.LambdaExpressions");
                    break;
                case 2:
                    runExample("com.example.java8.StreamsAPI");
                    break;
                case 3:
                    runExample("com.example.java8.DefaultMethods");
                    break;
                case 4:
                    runExample("com.example.java8.DateTimeAPI");
                    break;
                case 5:
                    runExample("com.example.java8.OptionalExample");
                    break;
                case 6:
                    runExample("com.example.java8.NashornExample");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 9 menu
    private static void showJava9Menu(Scanner scanner) {
        System.out.println("\n----- Java 9 Features -----");
        System.out.println("1. Module System (Project Jigsaw)");
        System.out.println("2. JShell (REPL)");
        System.out.println("3. Enhanced Process API");
        System.out.println("4. Private Methods in Interfaces");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("Module System is demonstrated in the module-info.java file.");
                    System.out.println("Check the project structure for details on Java modules.");
                    break;
                case 2:
                    runExample("com.example.java9.JShellExample");
                    break;
                case 3:
                    runExample("com.example.java9.EnhancedProcessAPI");
                    break;
                case 4:
                    runExample("com.example.java9.PrivateInterfaceMethods");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 10 menu
    private static void showJava10Menu(Scanner scanner) {
        System.out.println("\n----- Java 10 Features -----");
        System.out.println("1. Local-Variable Type Inference (var)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java10.LocalVariableTypeInference");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 11 menu
    private static void showJava11Menu(Scanner scanner) {
        System.out.println("\n----- Java 11 Features -----");
        System.out.println("1. HTTP Client API");
        System.out.println("2. New String Methods");
        System.out.println("3. Single-File Source-Code Programs");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java11.HttpClientExample");
                    break;
                case 2:
                    runExample("com.example.java11.StringMethodsExample");
                    break;
                case 3:
                    runExample("com.example.java11.SingleFileSourceExample");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 12 menu
    private static void showJava12Menu(Scanner scanner) {
        System.out.println("\n----- Java 12 Features -----");
        System.out.println("1. Switch Expressions (Preview)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java12.SwitchExpressions");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 13 menu
    private static void showJava13Menu(Scanner scanner) {
        System.out.println("\n----- Java 13 Features -----");
        System.out.println("1. Text Blocks (Preview)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java13.TextBlocks");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 14 menu
    private static void showJava14Menu(Scanner scanner) {
        System.out.println("\n----- Java 14 Features -----");
        System.out.println("1. Records (Preview)");
        System.out.println("2. Pattern Matching for instanceof (Preview)");
        System.out.println("3. Helpful NullPointerExceptions");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java14.RecordsExample");
                    break;
                case 2:
                    runExample("com.example.java14.PatternMatchingForInstanceOf");
                    break;
                case 3:
                    runExample("com.example.java14.HelpfulNPE");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 15 menu
    private static void showJava15Menu(Scanner scanner) {
        System.out.println("\n----- Java 15 Features -----");
        System.out.println("1. Sealed Classes (Preview)");
        System.out.println("2. Text Blocks (Standard)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java15.SealedClasses");
                    break;
                case 2:
                    System.out.println("Text Blocks were standardized in Java 15.");
                    System.out.println("See the Text Blocks example in Java 13.");
                    runExample("com.example.java13.TextBlocks");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 16-17 menu
    private static void showJava16_17Menu(Scanner scanner) {
        System.out.println("\n----- Java 16-17 Features -----");
        System.out.println("1. Records (Standard)");
        System.out.println("2. Pattern Matching for instanceof (Standard)");
        System.out.println("3. Sealed Classes (Standard)");
        System.out.println("4. Foreign Function & Memory API (Incubator)");
        System.out.println("5. Enhanced Random Number Generators");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("Records were standardized in Java 16.");
                    System.out.println("See the Records example in Java 14.");
                    runExample("com.example.java14.RecordsExample");
                    break;
                case 2:
                    System.out.println("Pattern Matching for instanceof was standardized in Java 16.");
                    System.out.println("See the Pattern Matching example in Java 14.");
                    runExample("com.example.java14.PatternMatchingForInstanceOf");
                    break;
                case 3:
                    System.out.println("Sealed Classes were standardized in Java 17.");
                    System.out.println("See the Sealed Classes example in Java 15.");
                    runExample("com.example.java15.SealedClasses");
                    break;
                case 4:
                    runExample("com.example.java17.ForeignFunctionMemoryAPI");
                    break;
                case 5:
                    runExample("com.example.java17.EnhancedRandomGenerators");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 18-20 menu
    private static void showJava18_20Menu(Scanner scanner) {
        System.out.println("\n----- Java 18-20 Features -----");
        System.out.println("1. Virtual Threads (Preview)");
        System.out.println("2. Structured Concurrency (Preview)");
        System.out.println("3. Pattern Matching (Preview Features)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.println("Virtual Threads were introduced as a preview in Java 19.");
                    System.out.println("See the Virtual Threads example in Java 21.");
                    runExample("com.example.java21.VirtualThreads");
                    break;
                case 2:
                    runExample("com.example.java19.StructuredConcurrency");
                    break;
                case 3:
                    System.out.println("Pattern Matching features were previewed across Java 19-20.");
                    System.out.println("See the Pattern Matching examples in Java 21.");
                    runExample("com.example.java21.PatternMatchingForSwitch");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Java 21 menu
    private static void showJava21Menu(Scanner scanner) {
        System.out.println("\n----- Java 21 Features -----");
        System.out.println("1. Virtual Threads (Standard)");
        System.out.println("2. Pattern Matching for switch (Standard)");
        System.out.println("3. Sequenced Collections");
        System.out.println("4. String Templates (Preview)");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    runExample("com.example.java21.VirtualThreads");
                    break;
                case 2:
                    runExample("com.example.java21.PatternMatchingForSwitch");
                    break;
                case 3:
                    runExample("com.example.java21.SequencedCollections");
                    break;
                case 4:
                    runExample("com.example.java21.StringTemplates");
                    break;
                case 0:
                    // Return to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear invalid input
        }
    }
    
    // Helper method to run an example by class name
    private static void runExample(String className) {
        System.out.println("\n----- Running " + className + " -----");
        System.out.println("Press Enter to continue after the example completes...");
        
        try {
            Class<?> clazz = Class.forName(className);
            java.lang.reflect.Method mainMethod = clazz.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[0]);
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Class " + className + " not found.");
        } catch (Exception e) {
            System.out.println("Error running example: " + e.getMessage());
        }
        
        // Wait for user to press Enter
        Scanner pauseScanner = new Scanner(System.in);
        pauseScanner.nextLine();
    }
}
