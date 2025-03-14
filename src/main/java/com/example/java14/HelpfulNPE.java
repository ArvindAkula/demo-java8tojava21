package com.example.java14;

/**
 * Demonstrates Helpful NullPointerExceptions introduced in Java 14.
 * 
 * This feature improves the diagnostic information for NullPointerExceptions
 * by precisely indicating which variable was null when a NPE is thrown.
 * 
 * Note: This class only describes the feature but doesn't demonstrate it directly
 * since the improved error messages are not visible in the code, only at runtime.
 */
public class HelpfulNPE {

    public static void main(String[] args) {
        System.out.println("Helpful NullPointerExceptions (Java 14)");
        System.out.println("--------------------------------------");
        
        // This feature doesn't change the code, but improves the error messages
        // at runtime when a NullPointerException occurs.
        
        exampleScenarios();
        improvements();
        comparisonWithOldNPE();
        enableDisable();
        limitations();
    }
    
    private static void exampleScenarios() {
        System.out.println("\n1. Example Scenarios");
        System.out.println("-------------------");
        
        System.out.println("Example 1: Method Call on Null Object");
        System.out.println("  String str = null;");
        System.out.println("  str.length(); // NPE");
        System.out.println("  Old message: 'NullPointerException'");
        System.out.println("  New message: 'NullPointerException: Cannot invoke \"String.length()\" because \"str\" is null'");
        
        System.out.println("\nExample 2: Array Access on Null Array");
        System.out.println("  int[] array = null;");
        System.out.println("  int value = array[0]; // NPE");
        System.out.println("  Old message: 'NullPointerException'");
        System.out.println("  New message: 'NullPointerException: Cannot read the array length because \"array\" is null'");
        
        System.out.println("\nExample 3: Field Access on Null Object");
        System.out.println("  Person person = null;");
        System.out.println("  String name = person.name; // NPE");
        System.out.println("  Old message: 'NullPointerException'");
        System.out.println("  New message: 'NullPointerException: Cannot read field \"name\" because \"person\" is null'");
        
        System.out.println("\nExample 4: Nested Null Field Access");
        System.out.println("  Order order = new Order();");
        System.out.println("  order.customer = null;");
        System.out.println("  String customerName = order.customer.name; // NPE");
        System.out.println("  Old message: 'NullPointerException'");
        System.out.println("  New message: 'NullPointerException: Cannot read field \"name\" because \"order.customer\" is null'");
    }
    
    private static void improvements() {
        System.out.println("\n2. Improvements");
        System.out.println("--------------");
        
        System.out.println("The helpful NPE messages provide:");
        System.out.println("- The exact variable or expression that was null");
        System.out.println("- The operation that couldn't be performed due to the null value");
        System.out.println("- For method chains, which part of the chain was null");
        System.out.println("- For multi-dimensional arrays, which dimension was null");
        System.out.println("- For complex expressions, which sub-expression was null");
    }
    
    private static void comparisonWithOldNPE() {
        System.out.println("\n3. Comparison with Old NPE Messages");
        System.out.println("----------------------------------");
        
        System.out.println("Before Java 14:");
        System.out.println("  - Generic 'java.lang.NullPointerException' message");
        System.out.println("  - No indication of which variable was null");
        System.out.println("  - Required manual debugging to identify null values");
        System.out.println("  - Often needed to add explicit null checks to isolate the problem");
        
        System.out.println("\nAfter Java 14:");
        System.out.println("  - Detailed messages showing exactly what was null");
        System.out.println("  - The type of operation attempted on the null reference");
        System.out.println("  - Faster debugging and problem identification");
        System.out.println("  - Especially helpful for beginners and in complex code bases");
    }
    
    private static void enableDisable() {
        System.out.println("\n4. How to Enable/Disable");
        System.out.println("------------------------");
        
        System.out.println("Enabling/disabling helpful NPE messages:");
        System.out.println("- Enabled by default in Java 14+");
        System.out.println("- Can be disabled with: -XX:-ShowCodeDetailsInExceptionMessages");
        System.out.println("- Can be explicitly enabled with: -XX:+ShowCodeDetailsInExceptionMessages");
        System.out.println("- Disabling might be useful if you have automation that parses stack traces");
    }
    
    private static void limitations() {
        System.out.println("\n5. Limitations");
        System.out.println("-------------");
        
        System.out.println("- Native methods don't provide helpful NPE messages");
        System.out.println("- Very complex expressions might have simplified error messages");
        System.out.println("- No change to how NPEs are thrown or caught, only to the message");
        System.out.println("- Additional information in the message has a minor performance impact");
        System.out.println("- Not available when bytecode is compiled with pre-Java 14 compilers");
    }
    
    // Example classes to illustrate NPE scenarios
    static class Person {
        String name;
        Address address;
    }
    
    static class Address {
        String street;
        String city;
    }
    
    static class Order {
        Customer customer;
        Product[] products;
    }
    
    static class Customer {
        String name;
        Address address;
    }
    
    static class Product {
        String name;
        double price;
    }
    
    // NOTE: To actually see the helpful NPE messages, you would need to run code like:
    /*
    public static void demonstrateActualNPE() {
        // This would show a helpful NPE if uncommented and run
        Person person = null;
        String name = person.name;
    }
    */
}
