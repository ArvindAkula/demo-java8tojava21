package com.example.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Demonstrates the Optional class introduced in Java 8 for better null handling.
 */
public class OptionalExample {

    public static void main(String[] args) {
        // Creating Optional objects
        System.out.println("--- Creating Optional Objects ---");
        
        // Empty Optional
        Optional<String> empty = Optional.empty();
        System.out.println("Empty Optional: " + empty);
        
        // Optional with non-null value
        Optional<String> nonEmpty = Optional.of("Hello");
        System.out.println("Non-empty Optional: " + nonEmpty);
        
        // Optional that may contain null
        String nullableValue = null;
        Optional<String> nullable = Optional.ofNullable(nullableValue);
        System.out.println("Optional with nullable value: " + nullable);
        
        String nonNullValue = "World";
        Optional<String> nonNullable = Optional.ofNullable(nonNullValue);
        System.out.println("Optional with non-null value: " + nonNullable);
        
        // Checking if value is present
        System.out.println("\n--- Checking for Value Presence ---");
        System.out.println("Is value present in empty? " + empty.isPresent());
        System.out.println("Is value present in nonEmpty? " + nonEmpty.isPresent());
        
        // Conditional action with ifPresent()
        System.out.println("\n--- Using ifPresent() ---");
        nonEmpty.ifPresent(value -> System.out.println("Value is present: " + value));
        empty.ifPresent(value -> System.out.println("This won't print"));
        
        // Getting value with default fallback
        System.out.println("\n--- Getting Value with Defaults ---");
        String valueFromNonEmpty = nonEmpty.orElse("Default");
        System.out.println("Value from non-empty or default: " + valueFromNonEmpty);
        
        String valueFromEmpty = empty.orElse("Default");
        System.out.println("Value from empty or default: " + valueFromEmpty);
        
        // Getting value with supplier function
        System.out.println("\n--- Getting Value with Supplier ---");
        String valueFromEmptyWithSupplier = empty.orElseGet(() -> "Generated Default");
        System.out.println("Value from empty or supplier: " + valueFromEmptyWithSupplier);
        
        // Throwing exception if value not present
        System.out.println("\n--- Throwing Exception if Empty ---");
        try {
            String value = empty.orElseThrow(() -> new IllegalStateException("Optional is empty"));
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        
        // Filtering values
        System.out.println("\n--- Filtering Values ---");
        Optional<String> filteredValue = nonEmpty.filter(value -> value.length() > 3);
        System.out.println("Filtered value (length > 3): " + filteredValue);
        
        Optional<String> emptyFiltered = nonEmpty.filter(value -> value.length() > 10);
        System.out.println("Filtered value (length > 10): " + emptyFiltered);
        
        // Transforming values with map()
        System.out.println("\n--- Transforming with map() ---");
        Optional<Integer> mappedValue = nonEmpty.map(String::length);
        System.out.println("Mapped value (string length): " + mappedValue);
        
        Optional<Integer> emptyMapped = empty.map(String::length);
        System.out.println("Mapped empty value: " + emptyMapped);
        
        // Transforming with flatMap()
        System.out.println("\n--- Transforming with flatMap() ---");
        Optional<String> flatMapped = nonEmpty.flatMap(value -> Optional.of(value.toUpperCase()));
        System.out.println("Flat mapped value: " + flatMapped);
        
        // Practical example: Avoid NullPointerException
        System.out.println("\n--- Practical Example ---");
        User user1 = new User("john@example.com");
        User user2 = new User(null);
        User nullUser = null;
        
        // Old way (prone to NullPointerException)
        try {
            System.out.println("Old way with potential NPE:");
            printEmailDomain_oldWay(user1); // Works fine
            printEmailDomain_oldWay(user2); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException");
        }
        
        // New way using Optional
        System.out.println("\nNew way with Optional:");
        printEmailDomain_newWay(user1); // Works fine
        printEmailDomain_newWay(user2); // Safely handles null email
        printEmailDomain_newWay(nullUser); // Safely handles null user
        
        // Chaining Optional operations
        System.out.println("\n--- Chaining Optional Operations ---");
        getUserEmailDomain(user1).ifPresent(domain -> System.out.println("User1 domain: " + domain));
        getUserEmailDomain(user2).ifPresent(domain -> System.out.println("User2 domain: " + domain));
        getUserEmailDomain(nullUser).ifPresent(domain -> System.out.println("NullUser domain: " + domain));
        
        // Using Optional with streams
        System.out.println("\n--- Optional with Streams ---");
        List<User> users = Arrays.asList(
                new User("alice@example.com"),
                new User(null),
                new User("bob@example.org"),
                null,
                new User("charlie@example.net")
        );
        
        // Filter out users with emails, extract domains and print them
        users.stream()
                .filter(u -> u != null)
                .map(User::getEmailOptional)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(email -> email.substring(email.indexOf('@') + 1))
                .distinct()
                .forEach(domain -> System.out.println("Domain: " + domain));
    }
    
    // Old way (prone to NullPointerException)
    private static void printEmailDomain_oldWay(User user) {
        String email = user.getEmail();
        String domain = email.substring(email.indexOf('@') + 1);
        System.out.println("Domain: " + domain);
    }
    
    // New way using Optional
    private static void printEmailDomain_newWay(User user) {
        Optional.ofNullable(user)
                .flatMap(User::getEmailOptional)
                .map(email -> email.substring(email.indexOf('@') + 1))
                .ifPresent(domain -> System.out.println("Domain: " + domain));
    }
    
    // Return Optional<String> from method
    private static Optional<String> getUserEmailDomain(User user) {
        return Optional.ofNullable(user)
                .flatMap(User::getEmailOptional)
                .map(email -> email.substring(email.indexOf('@') + 1));
    }
    
    // Simple User class for demonstration
    static class User {
        private final String email;
        
        User(String email) {
            this.email = email;
        }
        
        // Regular getter may return null
        public String getEmail() {
            return email;
        }
        
        // Optional-returning method
        public Optional<String> getEmailOptional() {
            return Optional.ofNullable(email);
        }
    }
}
