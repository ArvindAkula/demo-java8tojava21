package com.example.java14;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * Demonstrates Records introduced in Java 14 as a preview feature.
 * (Standardized in Java 16)
 * 
 * Records are a special kind of class that act as transparent carriers for immutable data.
 * They automatically provide:
 * - Constructor for all fields
 * - Accessor methods for all fields
 * - equals() and hashCode() methods
 * - toString() method
 */
public class RecordsExample {

    public static void main(String[] args) {
        traditionalValueClasses();
        basicRecordUsage();
        recordConstructors();
        recordMethods();
        recordRestrictions();
        practicalExamples();
    }
    
    /**
     * Traditional approach to value classes before records
     */
    private static void traditionalValueClasses() {
        System.out.println("1. Traditional Value Classes");
        System.out.println("---------------------------");
        
        // Create instances of traditional value classes
        TraditionalPoint point1 = new TraditionalPoint(10, 20);
        TraditionalPoint point2 = new TraditionalPoint(10, 20);
        TraditionalPoint point3 = new TraditionalPoint(15, 25);
        
        // Use the instances
        System.out.println("point1: " + point1);
        System.out.println("point1 equals point2: " + point1.equals(point2));
        System.out.println("point1 equals point3: " + point1.equals(point3));
        System.out.println("point1 x: " + point1.getX() + ", y: " + point1.getY());
        
        System.out.println();
    }
    
    /**
     * Basic usage of records
     */
    private static void basicRecordUsage() {
        System.out.println("2. Basic Record Usage");
        System.out.println("--------------------");
        
        // Create instances of record
        Point point1 = new Point(10, 20);
        Point point2 = new Point(10, 20);
        Point point3 = new Point(15, 25);
        
        // Use the instances - behavior is similar to traditional class
        System.out.println("point1: " + point1);
        System.out.println("point1 equals point2: " + point1.equals(point2));
        System.out.println("point1 equals point3: " + point1.equals(point3));
        System.out.println("point1 x: " + point1.x() + ", y: " + point1.y());
        
        // Records are immutable - no setters
        // point1.x = 30; // This would cause a compilation error
        
        // You can create a new record with modified values
        Point updatedPoint = new Point(point1.x() + 5, point1.y() + 10);
        System.out.println("updatedPoint: " + updatedPoint);
        
        System.out.println();
    }
    
    /**
     * Different types of record constructors
     */
    private static void recordConstructors() {
        System.out.println("3. Record Constructors");
        System.out.println("---------------------");
        
        // Default canonical constructor
        Employee emp1 = new Employee("John Doe", "Engineering", 75000);
        System.out.println("Employee (default constructor): " + emp1);
        
        // Custom canonical constructor with validation
        try {
            Employee invalidEmp = new Employee("", "Engineering", -5000);
            System.out.println("This should not print - validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation in canonical constructor: " + e.getMessage());
        }
        
        // Compact constructor
        Range range1 = new Range(10, 20);
        System.out.println("Range (compact constructor): " + range1);
        
        try {
            Range invalidRange = new Range(20, 10);
            System.out.println("This should not print - validation failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation in compact constructor: " + e.getMessage());
        }
        
        // Additional constructor
        Person person1 = new Person("John", "Doe");
        System.out.println("Person (additional constructor): " + person1);
        
        Person person2 = new Person("Jane Doe");
        System.out.println("Person (canonical constructor): " + person2);
        
        System.out.println();
    }
    
    /**
     * Methods in records
     */
    private static void recordMethods() {
        System.out.println("4. Record Methods");
        System.out.println("-----------------");
        
        Circle circle = new Circle(10);
        
        // Call instance methods
        System.out.println("Circle: " + circle);
        System.out.println("Circle area: " + circle.area());
        System.out.println("Circle circumference: " + circle.circumference());
        
        // Static method in record
        System.out.println("Unit circle area: " + Circle.unitCircleArea());
        
        System.out.println();
    }
    
    /**
     * Restrictions on records
     */
    private static void recordRestrictions() {
        System.out.println("5. Record Restrictions");
        System.out.println("---------------------");
        
        System.out.println("Records have the following restrictions:");
        System.out.println("- Cannot extend other classes (implicitly extend java.lang.Record)");
        System.out.println("- Cannot declare instance fields outside the record header");
        System.out.println("- Cannot be abstract");
        System.out.println("- Cannot declare native methods");
        System.out.println("- Are implicitly final");
        
        System.out.println();
    }
    
    /**
     * Practical examples of records
     */
    private static void practicalExamples() {
        System.out.println("6. Practical Examples");
        System.out.println("--------------------");
        
        // DTOs (Data Transfer Objects)
        UserDTO user = new UserDTO(1, "johndoe", "john@example.com");
        System.out.println("UserDTO: " + user);
        
        // Value objects
        Money salary = new Money(5000, "USD");
        Money bonus = new Money(1000, "USD");
        System.out.println("Salary: " + salary);
        System.out.println("Bonus: " + bonus);
        
        // Tuple-like returns
        record Pair<T, U>(T first, U second) {}
        
        Pair<String, Integer> nameAndAge = new Pair<>("Alice", 30);
        System.out.println("Name: " + nameAndAge.first() + ", Age: " + nameAndAge.second());
        
        // Sorting with records
        List<Product> products = Arrays.asList(
                new Product("Laptop", 1200.0),
                new Product("Phone", 800.0),
                new Product("Tablet", 500.0)
        );
        
        products.sort(Comparator.comparing(Product::price));
        System.out.println("Products sorted by price:");
        products.forEach(System.out::println);
        
        // Nested records
        Address address = new Address("123 Main St", "Anytown", "12345");
        CustomerData customer = new CustomerData("Alice Smith", address);
        System.out.println("Customer with address: " + customer);
    }
    
    // Traditional point class (pre-records)
    static class TraditionalPoint {
        private final int x;
        private final int y;
        
        public TraditionalPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() {
            return x;
        }
        
        public int getY() {
            return y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TraditionalPoint that = (TraditionalPoint) o;
            return x == that.x && y == that.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public String toString() {
            return "TraditionalPoint{" +
                   "x=" + x +
                   ", y=" + y +
                   '}';
        }
    }
    
    // Basic record - replaces all boilerplate in TraditionalPoint
    record Point(int x, int y) {}
    
    // Record with custom canonical constructor
    record Employee(String name, String department, double salary) {
        // Canonical constructor with validation
        public Employee {
            Objects.requireNonNull(name, "Name cannot be null");
            if (name.isBlank()) {
                throw new IllegalArgumentException("Name cannot be blank");
            }
            Objects.requireNonNull(department, "Department cannot be null");
            if (salary < 0) {
                throw new IllegalArgumentException("Salary cannot be negative");
            }
        }
    }
    
    // Record with compact constructor
    record Range(int min, int max) {
        // Compact constructor - implicit parameters
        public Range {
            if (min > max) {
                throw new IllegalArgumentException("Min cannot be greater than max");
            }
        }
    }
    
    // Record with additional constructor
    record Person(String firstName, String lastName) {
        // Additional constructor
        public Person(String fullName) {
            this(
                fullName.contains(" ") ? fullName.substring(0, fullName.indexOf(" ")) : fullName,
                fullName.contains(" ") ? fullName.substring(fullName.indexOf(" ") + 1) : ""
            );
        }
    }
    
    // Record with instance methods
    record Circle(double radius) {
        public double area() {
            return Math.PI * radius * radius;
        }
        
        public double circumference() {
            return 2 * Math.PI * radius;
        }
        
        // Static method
        public static double unitCircleArea() {
            return Math.PI;
        }
    }
    
    // Example records for practical use cases
    
    // DTO (Data Transfer Object)
    record UserDTO(int id, String username, String email) {}
    
    // Value object
    record Money(double amount, String currency) {}
    
    // Domain object
    record Product(String name, double price) {}
    
    // Nested records
    record Address(String street, String city, String zipCode) {}
    record CustomerData(String name, Address address) {}
}
