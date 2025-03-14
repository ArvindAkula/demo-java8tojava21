package com.example.java10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstrates Local Variable Type Inference (var) introduced in Java 10.
 * 
 * The 'var' keyword allows the type of local variables to be inferred by the compiler,
 * reducing verbosity while maintaining type safety.
 */
public class LocalVariableTypeInference {

    public static void main(String[] args) {
        basicUsage();
        limitationsOfVar();
        goodPracticesForVar();
        practicalExamples();
    }
    
    private static void basicUsage() {
        System.out.println("--- Basic Usage of var ---");
        
        // Before Java 10
        String name = "John";
        int age = 30;
        List<String> namesList = new ArrayList<>();
        
        // Java 10 with var
        var nameWithVar = "John";  // Inferred as String
        var ageWithVar = 30;       // Inferred as int
        var listWithVar = new ArrayList<String>(); // Inferred as ArrayList<String>
        
        System.out.println("nameWithVar is of type: " + nameWithVar.getClass().getName());
        System.out.println("ageWithVar is of type: " + ((Object)ageWithVar).getClass().getName());
        System.out.println("listWithVar is of type: " + listWithVar.getClass().getName());
        
        // var works with any expression that has a type
        var sum = 10 + 20;   // int
        var greeting = "Hello, " + nameWithVar;  // String
        var random = Math.random();  // double
        
        System.out.println("Sum: " + sum);
        System.out.println("Greeting: " + greeting);
        System.out.println("Random: " + random);
    }
    
    private static void limitationsOfVar() {
        System.out.println("\n--- Limitations of var ---");
        
        // var requires initialization
        var initialized = "This works";
        // var notInitialized; // Compilation error - cannot infer type
        
        // Cannot use with null literal by itself
        // var nothing = null; // Compilation error - cannot infer type
        
        // Solution: specify the type
        var nullableString = (String)null;
        
        // Cannot use with lambda expressions by themselves
        // var predicate = x -> x > 5; // Compilation error
        
        // Solution: provide explicit type
        Predicate<Integer> predicate = x -> x > 5;
        var predicateWithType = (Predicate<Integer>)(x -> x > 5);
        
        // Cannot use with method references by themselves
        // var methodRef = String::length; // Compilation error
        
        // Cannot use with array initializers
        // var numbers = {1, 2, 3}; // Compilation error
        
        // Solution: explicit array creation
        var numbers = new int[]{1, 2, 3};
        
        // Not for fields (class members)
        // Works only for local variables
        
        // Not for method parameters or return types
    }
    
    private static void goodPracticesForVar() {
        System.out.println("\n--- Good Practices for var ---");
        
        // GOOD: Type is obvious from the right side
        var list = new ArrayList<String>();
        var map = new HashMap<String, Integer>();
        
        // GOOD: Short scope, clearly named
        var items = List.of("Apple", "Banana", "Orange");
        for (var item : items) {
            System.out.println("Processing: " + item);
        }
        
        // GOOD: Type is clear from method name
        var count = countElements(items);
        System.out.println("Count: " + count);
        
        // GOOD: Improved readability by reducing duplication
        var customerService = new CustomerService();
        
        // BAD: Type is not obvious, poor variable name
        // var x = getResult();
        
        // BAD: Long chain of method calls
        // var result = obj.getA().getB().getC().process();
        
        // BAD: Diamond operator with var can be confusing
        // var confusing = new HashMap<>();
    }
    
    private static void practicalExamples() {
        System.out.println("\n--- Practical Examples ---");
        
        // Example 1: File I/O with try-with-resources
        System.out.println("Reading a file (simulated):");
        try {
            // Pre-Java 10
            // BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            
            // With var - More readable
            // var reader = new BufferedReader(new FileReader("file.txt"));
            
            // Simulated file content
            var content = "This is a simulated file content.\nIt has multiple lines.\nUsing var makes the code cleaner.";
            var lines = content.lines().collect(Collectors.toList());
            
            for (var line : lines) {
                System.out.println(line);
            }
            
        } catch (Exception e) {
            // Using var with exception handling
            System.out.println("File reading simulation done.");
        }
        
        // Example 2: Complex collections
        System.out.println("\nComplex collections example:");
        
        // Pre-Java 10
        // Map<String, List<Map<String, String>>> complexStructure = new HashMap<>();
        
        // With var - More readable
        var complexStructure = new HashMap<String, List<Map<String, String>>>();
        
        // Example 3: Intermediate values in computations
        System.out.println("\nIntermediate values example:");
        
        var salary = 50000;
        var taxRate = 0.2;
        var insurance = 3000;
        
        // Using var for intermediate calculations makes the code more readable
        var taxableIncome = salary - insurance;
        var incomeTax = taxableIncome * taxRate;
        var netIncome = salary - incomeTax;
        
        System.out.printf("Salary: $%.2f, Tax: $%.2f, Net Income: $%.2f%n", 
                (double)salary, incomeTax, netIncome);
    }
    
    // Helper class for demonstration
    static class CustomerService {
        public void processCustomer(String id) {
            System.out.println("Processing customer: " + id);
        }
    }
    
    // Helper method
    private static int countElements(List<?> list) {
        return list.size();
    }
}
