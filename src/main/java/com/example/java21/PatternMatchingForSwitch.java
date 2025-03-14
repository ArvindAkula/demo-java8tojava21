package com.example.java21;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Demonstrates Pattern Matching for switch introduced in Java 21.
 * 
 * This feature allows switch statements and expressions to test if a value
 * matches a certain pattern, making it easier to work with complex data structures
 * and reducing the need for nested if-else statements.
 */
public class PatternMatchingForSwitch {

    public static void main(String[] args) {
        traditionalSwitchLimitations();
        typePatternMatching();
        nullHandling();
        recordPatternMatching();
        guardedPatterns();
        whenCombiningWithSealedClasses();
        realWorldExamples();
    }
    
    /**
     * Traditional switch limitations
     */
    private static void traditionalSwitchLimitations() {
        System.out.println("1. Traditional Switch Limitations");
        System.out.println("--------------------------------");
        
        // Traditional switch cases are limited to:
        // - Primitive types and their wrappers (int, Integer, etc.)
        // - Strings
        // - Enums
        
        Object obj = 42;
        
        // Pre-Java 21 approach
        System.out.println("Pre-Java 21 approach:");
        if (obj instanceof Integer i) {
            System.out.println("Integer: " + i);
        } else if (obj instanceof String s) {
            System.out.println("String: " + s);
        } else if (obj instanceof Double d) {
            System.out.println("Double: " + d);
        } else {
            System.out.println("Other type: " + obj.getClass().getSimpleName());
        }
        
        System.out.println();
    }
    
    /**
     * Type pattern matching with switch
     */
    private static void typePatternMatching() {
        System.out.println("2. Type Pattern Matching");
        System.out.println("------------------------");
        
        Object[] objects = { 42, "Hello", 3.14, true, new ArrayList<>() };
        
        System.out.println("Processing different object types:");
        for (Object obj : objects) {
            String result = switch (obj) {
                case Integer i -> "Integer: " + i;
                case String s -> "String: " + s;
                case Double d -> "Double: " + d;
                case Boolean b -> "Boolean: " + b;
                case List<?> list -> "List with " + list.size() + " elements";
                default -> "Other type: " + obj.getClass().getSimpleName();
            };
            System.out.println(result);
        }
        
        // Complex hierarchical type pattern matching
        Object shape = new Circle(5.0);
        
        String description = switch (shape) {
            case Circle c -> "Circle with radius " + c.radius;
            case Rectangle r -> "Rectangle with dimensions " + r.width + "x" + r.height;
            case Triangle t -> "Triangle with sides " + t.a + ", " + t.b + ", " + t.c;
            default -> "Unknown shape";
        };
        
        System.out.println("\nShape description: " + description);
        
        System.out.println();
    }
    
    /**
     * Null handling in pattern matching
     */
    private static void nullHandling() {
        System.out.println("3. Null Handling");
        System.out.println("---------------");
        
        Object[] objects = { "Hello", null, 42 };
        
        System.out.println("Processing objects including null:");
        for (Object obj : objects) {
            String result = switch (obj) {
                case null -> "It's null!";
                case String s -> "String: " + s;
                case Integer i -> "Integer: " + i;
                default -> "Other type";
            };
            System.out.println(result);
        }
        
        // Working with Optional
        System.out.println("\nWorking with Optional:");
        
        List<Optional<String>> optionals = List.of(
                Optional.of("Value"),
                Optional.empty(),
                null
        );
        
        for (Optional<String> opt : optionals) {
            String result = switch (opt) {
                case null -> "Null Optional reference";
                case Optional.empty() -> "Empty Optional";
                case Optional<?> o -> "Optional with value: " + o.get();
            };
            System.out.println(result);
        }
        
        System.out.println();
    }
    
    /**
     * Record pattern matching
     */
    private static void recordPatternMatching() {
        System.out.println("4. Record Pattern Matching");
        System.out.println("--------------------------");
        
        // Create some records to test with
        record Point(int x, int y) {}
        record Rectangle(Point topLeft, Point bottomRight) {}
        record Circle(Point center, double radius) {}
        
        Object[] shapes = {
            new Rectangle(new Point(0, 0), new Point(10, 10)),
            new Circle(new Point(5, 5), 5.0),
            new Point(1, 2)
        };
        
        System.out.println("Processing nested records:");
        for (Object shape : shapes) {
            String description = switch (shape) {
                case Rectangle(Point(var x1, var y1), Point(var x2, var y2)) -> 
                    "Rectangle from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")" +
                    " with width " + (x2 - x1) + " and height " + (y2 - y1);
                case Circle(Point(var x, var y), var r) -> 
                    "Circle at (" + x + "," + y + ") with radius " + r;
                case Point(var x, var y) -> 
                    "Point at (" + x + "," + y + ")";
                default -> 
                    "Unknown shape";
            };
            System.out.println(description);
        }
        
        System.out.println();
    }
    
    /**
     * Guarded patterns (pattern matching with conditions)
     */
    private static void guardedPatterns() {
        System.out.println("5. Guarded Patterns");
        System.out.println("------------------");
        
        record Person(String name, int age) {}
        
        List<Person> people = List.of(
            new Person("Alice", 25),
            new Person("Bob", 17),
            new Person("Charlie", 65),
            new Person("", 30)
        );
        
        System.out.println("Categorizing people with guarded patterns:");
        for (Person person : people) {
            String category = switch (person) {
                case Person p when p.name().isEmpty() -> 
                    "Person with empty name";
                case Person p when p.age() < 18 -> 
                    p.name() + " is a minor";
                case Person p when p.age() >= 65 -> 
                    p.name() + " is a senior";
                case Person p when p.age() >= 18 && p.age() < 65 -> 
                    p.name() + " is an adult";
                default -> 
                    "Unknown category";
            };
            System.out.println(category);
        }
        
        // Another example with different data types
        Object[] values = { 42, -10, "hello", "x", List.of(1, 2, 3), List.of() };
        
        System.out.println("\nProcessing different values with guards:");
        for (Object value : values) {
            String description = switch (value) {
                case Integer i when i > 0 -> 
                    "Positive integer: " + i;
                case Integer i when i < 0 -> 
                    "Negative integer: " + i;
                case Integer i -> 
                    "Zero integer";
                case String s when s.length() == 1 -> 
                    "Single character: " + s;
                case String s -> 
                    "String of length " + s.length() + ": " + s;
                case List<?> list when list.isEmpty() -> 
                    "Empty list";
                case List<?> list -> 
                    "List with " + list.size() + " elements";
                default -> 
                    "Other type";
            };
            System.out.println(description);
        }
        
        System.out.println();
    }
    
    /**
     * Pattern matching with sealed classes
     */
    private static void whenCombiningWithSealedClasses() {
        System.out.println("6. Pattern Matching with Sealed Classes");
        System.out.println("-------------------------------------");
        
        // Create some expressions for testing
        Expression expr1 = new Constant(10);
        Expression expr2 = new Addition(new Constant(5), new Constant(7));
        Expression expr3 = new Multiplication(new Constant(3), new Addition(new Constant(1), new Constant(2)));
        
        List<Expression> expressions = List.of(expr1, expr2, expr3);
        
        System.out.println("Evaluating expressions:");
        for (Expression expr : expressions) {
            int result = evalExpression(expr);
            System.out.println(expr + " = " + result);
        }
        
        // The exhaustiveness of switch gets validated at compile-time with sealed classes
        // We cannot forget a case and we don't need a default case
        
        System.out.println("\nCombining pattern matching with sealed classes enables:");
        System.out.println("- Exhaustive pattern matching without default cases");
        System.out.println("- Compile-time safety for all possible subclasses");
        System.out.println("- More maintainable code as new subclasses require handling");
        System.out.println("- Better IDE support for detecting missing cases");
        
        System.out.println();
    }
    
    /**
     * Real world examples of pattern matching
     */
    private static void realWorldExamples() {
        System.out.println("7. Real-World Examples");
        System.out.println("---------------------");
        
        // Example 1: HTTP request handling
        System.out.println("Example 1: HTTP request handling");
        
        record HttpRequest(String method, String path, Map<String, String> headers) {}
        
        HttpRequest[] requests = {
            new HttpRequest("GET", "/api/users", Map.of("Accept", "application/json")),
            new HttpRequest("POST", "/api/users", Map.of("Content-Type", "application/json")),
            new HttpRequest("GET", "/api/admin", Map.of("Authorization", "Bearer token"))
        };
        
        for (HttpRequest request : requests) {
            String response = switch (request) {
                case HttpRequest(String method, String path, var headers)
                        when method.equals("GET") && path.equals("/api/users") ->
                    "200 OK - Returning list of users";
                
                case HttpRequest(String method, String path, var headers)
                        when method.equals("POST") && path.equals("/api/users") ->
                    "201 Created - User created successfully";
                
                case HttpRequest(var method, String path, var headers)
                        when path.startsWith("/api/admin") && !headers.containsKey("Authorization") ->
                    "401 Unauthorized - Missing authorization";
                
                case HttpRequest(var method, String path, var headers)
                        when path.startsWith("/api/admin") ->
                    "200 OK - Admin API access granted";
                
                default ->
                    "404 Not Found - Unknown endpoint";
            };
            
            System.out.println("Request: " + request.method() + " " + request.path() + 
                    " -> Response: " + response);
        }
        
        // Example 2: JSON parsing
        System.out.println("\nExample 2: Working with JSON-like data");
        
        // Simplified JSON value hierarchy
        record JsonObject(Map<String, JsonValue> properties) implements JsonValue {}
        record JsonArray(List<JsonValue> elements) implements JsonValue {}
        record JsonString(String value) implements JsonValue {}
        record JsonNumber(double value) implements JsonValue {}
        record JsonBoolean(boolean value) implements JsonValue {}
        record JsonNull() implements JsonValue {}
        
        // Create a sample JSON structure
        JsonValue json = new JsonObject(Map.of(
            "name", new JsonString("Alice"),
            "age", new JsonNumber(30),
            "address", new JsonObject(Map.of(
                "street", new JsonString("123 Main St"),
                "city", new JsonString("New York")
            )),
            "hobbies", new JsonArray(List.of(
                new JsonString("reading"),
                new JsonString("hiking")
            )),
            "isEmployed", new JsonBoolean(true),
            "spouse", new JsonNull()
        ));
        
        // Extract values using pattern matching
        Object result = switch (json) {
            case JsonObject obj when obj.properties().containsKey("name") && 
                                    obj.properties().get("name") instanceof JsonString name ->
                "User: " + name.value();
                
            case JsonObject obj when obj.properties().containsKey("error") ->
                "Error response";
                
            case JsonArray arr when !arr.elements().isEmpty() ->
                "Non-empty array with " + arr.elements().size() + " elements";
                
            case JsonString str ->
                "String value: " + str.value();
                
            case JsonNumber num ->
                "Number value: " + num.value();
                
            case JsonBoolean bool ->
                "Boolean value: " + bool.value();
                
            case JsonNull _ ->
                "Null value";
                
            default ->
                "Unknown JSON structure";
        };
        
        System.out.println("JSON processing result: " + result);
        
        System.out.println("\nBenefits in real-world applications:");
        System.out.println("- Type-safe data processing");
        System.out.println("- Reduced boilerplate code");
        System.out.println("- Improved code readability");
        System.out.println("- Better handling of complex data structures");
        System.out.println("- More maintainable code");
    }
    
    // Helper method to evaluate expressions
    private static int evalExpression(Expression expr) {
        return switch (expr) {
            case Constant c -> c.value();
            case Addition a -> evalExpression(a.left()) + evalExpression(a.right());
            case Multiplication m -> evalExpression(m.left()) * evalExpression(m.right());
        };
    }
}

// Classes for examples

// Shapes for type pattern example
class Circle {
    double radius;
    
    Circle(double radius) {
        this.radius = radius;
    }
}

class Rectangle {
    double width;
    double height;
    
    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
}

class Triangle {
    double a;
    double b;
    double c;
    
    Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

// Expression hierarchy for sealed classes example
sealed interface Expression permits Constant, Addition, Multiplication {}

record Constant(int value) implements Expression {
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

record Addition(Expression left, Expression right) implements Expression {
    @Override
    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}

record Multiplication(Expression left, Expression right) implements Expression {
    @Override
    public String toString() {
        return "(" + left + " * " + right + ")";
    }
}

// JSON interface for example
interface JsonValue {}
