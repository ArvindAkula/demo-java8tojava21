package com.example.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Demonstrates Lambda Expressions and Functional Interfaces introduced in Java 8.
 */
public class LambdaExpressions {

    public static void main(String[] args) {
        // Basic lambda expression
        Runnable runnable = () -> System.out.println("Hello, Lambda!");
        runnable.run();

        // Lambda with parameters
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // Using lambda with forEach
        System.out.println("Numbers:");
        numbers.forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Method reference (shorthand for lambdas)
        System.out.println("Using method reference:");
        numbers.forEach(System.out::println);
        
        // Examples with standard functional interfaces
        
        // Predicate - takes one argument and returns boolean
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));
        System.out.println("Is 7 even? " + isEven.test(7));
        
        // Function - takes one argument and returns a result
        Function<String, Integer> stringLength = s -> s.length();
        System.out.println("Length of 'Hello': " + stringLength.apply("Hello"));
        
        // Consumer - takes one argument and returns no result
        Consumer<String> printer = s -> System.out.println("Consuming: " + s);
        printer.accept("Hello, Consumer!");
        
        // Supplier - takes no arguments and returns a result
        Supplier<Double> randomValue = () -> Math.random();
        System.out.println("Random value: " + randomValue.get());
        
        // Combining predicates
        Predicate<Integer> isGreaterThan5 = n -> n > 5;
        Predicate<Integer> isEvenAndGreaterThan5 = isEven.and(isGreaterThan5);
        
        System.out.println("Even numbers greater than 5:");
        numbers.stream()
                .filter(isEvenAndGreaterThan5)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
        
        // Using lambdas with custom functional interfaces
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;
        
        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 - 5 = " + operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));
    }
    
    private static int operate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }
    
    // Custom functional interface
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }
}
