package com.example.java14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Demonstrates Pattern Matching for instanceof introduced in Java 14 as a preview feature.
 * (Standardized in Java 16)
 * 
 * Pattern Matching for instanceof simplifies common instanceof-and-cast idioms
 * by allowing you to declare a variable in the condition, which is then bound
 * to the tested expression if the test succeeds.
 */
public class PatternMatchingForInstanceOf {

    public static void main(String[] args) {
        traditionalInstanceOf();
        patternMatchingBasics();
        multiplePatternMatching();
        patternMatchingWithNulls();
        practicalExamples();
    }
    
    /**
     * Traditional approach (pre-Java 14)
     */
    private static void traditionalInstanceOf() {
        System.out.println("1. Traditional instanceof Approach");
        System.out.println("--------------------------------");
        
        // Example objects
        Object obj1 = "Hello, World!";
        Object obj2 = Integer.valueOf(42);
        Object obj3 = new ArrayList<String>();
        
        // Traditional instanceof + cast pattern
        if (obj1 instanceof String) {
            // Need to cast after instanceof check
            String str = (String) obj1;
            System.out.println("String of length: " + str.length());
        }
        
        if (obj2 instanceof Integer) {
            Integer num = (Integer) obj2;
            System.out.println("Integer with value: " + num);
        }
        
        if (obj3 instanceof List) {
            List<?> list = (List<?>) obj3;
            System.out.println("List with size: " + list.size());
        }
        
        // Nested instanceof and casts can be very verbose
        Object nestedObj = Map.of("key", List.of("value"));
        
        if (nestedObj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) nestedObj;
            Object value = map.get("key");
            
            if (value instanceof List) {
                List<?> list = (List<?>) value;
                if (!list.isEmpty() && list.get(0) instanceof String) {
                    String str = (String) list.get(0);
                    System.out.println("Found string: " + str);
                }
            }
        }
        
        System.out.println();
    }
    
    /**
     * Basic pattern matching with instanceof
     */
    private static void patternMatchingBasics() {
        System.out.println("2. Pattern Matching Basics");
        System.out.println("-------------------------");
        
        // Example objects
        Object obj1 = "Hello, World!";
        Object obj2 = Integer.valueOf(42);
        Object obj3 = new ArrayList<String>();
        
        // Pattern matching with instanceof
        if (obj1 instanceof String str) {
            // str is automatically bound and scoped to this block
            System.out.println("String of length: " + str.length());
        }
        
        if (obj2 instanceof Integer num) {
            System.out.println("Integer with value: " + num);
        }
        
        if (obj3 instanceof List<?> list) {
            System.out.println("List with size: " + list.size());
        }
        
        // Scoping rules
        if (obj1 instanceof String s && s.length() > 5) {
            // s is available here
            System.out.println("String longer than 5 chars: " + s);
        }
        // s is not available here
        
        System.out.println();
    }
    
    /**
     * Multiple pattern matching in logical expressions
     */
    private static void multiplePatternMatching() {
        System.out.println("3. Multiple Pattern Matching");
        System.out.println("---------------------------");
        
        Object obj1 = "Test";
        Object obj2 = 123;
        
        // && (AND) - Short circuit behavior
        if (obj1 instanceof String s && s.length() > 2) {
            System.out.println("String with length > 2: " + s);
        }
        
        // || (OR) - The pattern variable is not in scope after ||
        if (obj1 instanceof String s && s.length() > 0 || obj2 instanceof Integer) {
            // Can't use s here due to OR
            System.out.println("Either a non-empty String or an Integer");
        }
        
        // Nested pattern matching
        Object nested = Map.of("key", List.of("value"));
        
        // Much simpler with pattern matching
        if (nested instanceof Map<?, ?> map && 
            map.get("key") instanceof List<?> list && 
            !list.isEmpty() && 
            list.get(0) instanceof String value) {
            
            System.out.println("Found string in nested structure: " + value);
        }
        
        System.out.println();
    }
    
    /**
     * Pattern matching with null handling
     */
    private static void patternMatchingWithNulls() {
        System.out.println("4. Pattern Matching with Nulls");
        System.out.println("-----------------------------");
        
        Object nullObj = null;
        Object nonNullObj = "Not null";
        
        // instanceof always returns false for null
        if (nullObj instanceof String s) {
            System.out.println("This won't print - null is not an instance of String");
        } else {
            System.out.println("nullObj is null or not a String");
        }
        
        // Combining with null check
        if (nonNullObj != null && nonNullObj instanceof String s) {
            System.out.println("Non-null String: " + s);
        }
        
        // Using with Optional
        Optional<String> opt = Optional.of("Optional value");
        if (opt.isPresent() && opt.get() instanceof String s && s.length() > 5) {
            System.out.println("Optional contains a String longer than 5 chars: " + s);
        }
        
        System.out.println();
    }
    
    /**
     * Practical examples of pattern matching with instanceof
     */
    private static void practicalExamples() {
        System.out.println("5. Practical Examples");
        System.out.println("--------------------");
        
        // Simplified polymorphic code
        Shape[] shapes = {
            new Circle(5.0),
            new Rectangle(4.0, 6.0),
            new Triangle(3.0, 4.0, 5.0),
            "Not a shape"  // Invalid input
        };
        
        double totalArea = 0.0;
        
        for (Object shape : shapes) {
            if (shape instanceof Circle c) {
                totalArea += Math.PI * c.radius * c.radius;
            } else if (shape instanceof Rectangle r) {
                totalArea += r.length * r.width;
            } else if (shape instanceof Triangle t) {
                // Heron's formula
                double s = (t.side1 + t.side2 + t.side3) / 2;
                totalArea += Math.sqrt(s * (s - t.side1) * (s - t.side2) * (s - t.side3));
            } else {
                System.out.println("Unknown shape: " + shape);
            }
        }
        
        System.out.println("Total area of all shapes: " + totalArea);
        
        // Exception handling
        try {
            Object obj = Integer.parseInt("abc");
        } catch (Exception e) {
            if (e instanceof NumberFormatException nfe) {
                System.out.println("Number format exception: " + nfe.getMessage());
            } else if (e instanceof IllegalArgumentException iae) {
                System.out.println("Illegal argument: " + iae.getMessage());
            }
        }
        
        // Visitor pattern simplified
        List<Node> nodes = List.of(
            new TextNode("Hello"),
            new ImageNode("image.jpg", 100, 200),
            new LinkNode("https://example.com", "Example")
        );
        
        for (Node node : nodes) {
            renderNode(node);
        }
    }
    
    // Helper method for visitor pattern example
    private static void renderNode(Node node) {
        if (node instanceof TextNode text) {
            System.out.println("Rendering text: " + text.content);
        } else if (node instanceof ImageNode img) {
            System.out.println("Rendering image: " + img.src + " (" + img.width + "x" + img.height + ")");
        } else if (node instanceof LinkNode link) {
            System.out.println("Rendering link: " + link.text + " -> " + link.url);
        } else {
            System.out.println("Unknown node type");
        }
    }
    
    // Helper classes for examples
    
    // Shape class hierarchy
    interface Shape {}
    
    static class Circle implements Shape {
        double radius;
        
        Circle(double radius) {
            this.radius = radius;
        }
    }
    
    static class Rectangle implements Shape {
        double length;
        double width;
        
        Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }
    }
    
    static class Triangle implements Shape {
        double side1;
        double side2;
        double side3;
        
        Triangle(double side1, double side2, double side3) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        }
    }
    
    // Node class hierarchy for visitor pattern example
    interface Node {}
    
    static class TextNode implements Node {
        String content;
        
        TextNode(String content) {
            this.content = content;
        }
    }
    
    static class ImageNode implements Node {
        String src;
        int width;
        int height;
        
        ImageNode(String src, int width, int height) {
            this.src = src;
            this.width = width;
            this.height = height;
        }
    }
    
    static class LinkNode implements Node {
        String url;
        String text;
        
        LinkNode(String url, String text) {
            this.url = url;
            this.text = text;
        }
    }
}
