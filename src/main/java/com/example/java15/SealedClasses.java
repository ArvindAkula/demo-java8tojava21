package com.example.java15;

/**
 * Demonstrates Sealed Classes introduced in Java 15 as a preview feature.
 * (Standardized in Java 17)
 * 
 * Sealed classes restrict which other classes may extend or implement them,
 * providing better control over class hierarchies and enabling exhaustive
 * pattern matching.
 */
public class SealedClasses {

    public static void main(String[] args) {
        basicSealedClasses();
        sealedInterfaces();
        nestedHierarchies();
        sealedWithRecords();
        exhaustiveness();
        practicalExamples();
    }
    
    /**
     * Basic usage of sealed classes
     */
    private static void basicSealedClasses() {
        System.out.println("1. Basic Sealed Classes");
        System.out.println("----------------------");
        
        // Create instances of the permitted subclasses
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);
        
        // Use the instances
        printShapeArea(circle);
        printShapeArea(rectangle);
        printShapeArea(triangle);
        
        // Attempting to create a non-permitted subclass would result in a compilation error
        // class UnsupportedShape extends Shape { } // Won't compile
        
        System.out.println();
    }
    
    /**
     * Sealed interfaces
     */
    private static void sealedInterfaces() {
        System.out.println("2. Sealed Interfaces");
        System.out.println("-------------------");
        
        // Create instances of classes implementing the sealed interface
        Fruit apple = new Apple("Red Delicious", true);
        Fruit orange = new Orange(11);
        Fruit banana = new Banana(8.5);
        
        // Use the instances
        printFruitDetails(apple);
        printFruitDetails(orange);
        printFruitDetails(banana);
        
        // Attempting to create a non-permitted implementation would result in a compilation error
        // class Watermelon implements Fruit { } // Won't compile
        
        System.out.println();
    }
    
    /**
     * Nested hierarchies with sealed classes
     */
    private static void nestedHierarchies() {
        System.out.println("3. Nested Hierarchies");
        System.out.println("--------------------");
        
        // Create instances of the nested hierarchy
        Vehicle car = new Car("Toyota", "Camry", 4);
        Vehicle truck = new Truck("Ford", "F-150", 2500);
        Vehicle motorcycle = new Motorcycle("Honda", "CBR600", false);
        
        printVehicleDetails(car);
        printVehicleDetails(truck);
        printVehicleDetails(motorcycle);
        
        // Sedan extends Car (which extends Vehicle)
        Vehicle sedan = new Sedan("Honda", "Accord", 4, true);
        printVehicleDetails(sedan);
        
        System.out.println();
    }
    
    /**
     * Sealed classes with records
     */
    private static void sealedWithRecords() {
        System.out.println("4. Sealed Classes with Records");
        System.out.println("-----------------------------");
        
        // Create instances of the record implementations
        BinaryTree tree1 = new Leaf<>(42);
        BinaryTree tree2 = new Node<>(
                new Leaf<>(10),
                new Leaf<>(30)
        );
        BinaryTree tree3 = new Node<>(
                new Node<>(new Leaf<>(1), new Leaf<>(2)),
                new Leaf<>(3)
        );
        
        System.out.println("Tree 1: " + tree1);
        System.out.println("Tree 2: " + tree2);
        System.out.println("Tree 3: " + tree3);
        
        System.out.println("\nTree 1 count: " + countNodes(tree1));
        System.out.println("Tree 2 count: " + countNodes(tree2));
        System.out.println("Tree 3 count: " + countNodes(tree3));
        
        System.out.println();
    }
    
    /**
     * Exhaustiveness in pattern matching with sealed types
     */
    private static void exhaustiveness() {
        System.out.println("5. Exhaustiveness in Pattern Matching");
        System.out.println("------------------------------------");
        
        // Create instances of HTTP response types
        HttpResponse success = new SuccessResponse(200, "OK", "{\"data\": \"example\"}");
        HttpResponse redirect = new RedirectResponse(302, "Found", "/new-location");
        HttpResponse error = new ErrorResponse(404, "Not Found", "The requested resource was not found");
        
        System.out.println("Success response: " + getResponseDescription(success));
        System.out.println("Redirect response: " + getResponseDescription(redirect));
        System.out.println("Error response: " + getResponseDescription(error));
        
        System.out.println();
    }
    
    /**
     * Practical examples of sealed classes
     */
    private static void practicalExamples() {
        System.out.println("6. Practical Examples");
        System.out.println("--------------------");
        
        // Example 1: Command pattern with sealed classes
        Command addCommand = new AddCommand(10);
        Command subtractCommand = new SubtractCommand(5);
        Command resetCommand = new ResetCommand();
        
        int result = 0;
        result = executeCommand(result, addCommand);      // 0 + 10 = 10
        result = executeCommand(result, subtractCommand); // 10 - 5 = 5
        result = executeCommand(result, addCommand);      // 5 + 10 = 15
        result = executeCommand(result, resetCommand);    // 0
        
        System.out.println("Final result after commands: " + result);
        
        // Example 2: Expression tree with sealed classes
        Expression expr = new Addition(
            new Multiplication(
                new Constant(5),
                new Constant(3)
            ),
            new Constant(2)
        );
        
        System.out.println("\nExpression: 5 * 3 + 2");
        System.out.println("Evaluation result: " + evaluateExpression(expr));
        
        // Example 3: Event handling system
        Event clickEvent = new MouseClickEvent(100, 150);
        Event keyEvent = new KeyPressEvent('A', true);
        Event windowEvent = new WindowResizeEvent(800, 600);
        
        System.out.println("\nEvent handling:");
        handleEvent(clickEvent);
        handleEvent(keyEvent);
        handleEvent(windowEvent);
    }
    
    // Helper methods for examples
    
    private static void printShapeArea(Shape shape) {
        String shapeType = shape.getClass().getSimpleName();
        System.out.println(shapeType + " area: " + shape.calculateArea());
    }
    
    private static void printFruitDetails(Fruit fruit) {
        if (fruit instanceof Apple apple) {
            System.out.println("Apple variety: " + apple.variety() + 
                    (apple.organic() ? " (Organic)" : ""));
        } else if (fruit instanceof Orange orange) {
            System.out.println("Orange with " + orange.segments() + " segments");
        } else if (fruit instanceof Banana banana) {
            System.out.println("Banana with ripeness level: " + banana.ripeness());
        }
    }
    
    private static void printVehicleDetails(Vehicle vehicle) {
        System.out.print(vehicle.getMake() + " " + vehicle.getModel());
        
        if (vehicle instanceof Car car) {
            System.out.println(" with " + car.getDoors() + " doors");
            
            if (car instanceof Sedan sedan) {
                System.out.println("  Sedan with" + (sedan.hasLeatherSeats() ? "" : "out") + 
                        " leather seats");
            }
        } else if (vehicle instanceof Truck truck) {
            System.out.println(" with payload capacity: " + truck.getPayloadCapacity() + " lbs");
        } else if (vehicle instanceof Motorcycle motorcycle) {
            System.out.println(" with" + (motorcycle.hasSideCar() ? "" : "out") + " sidecar");
        }
    }
    
    private static <T> int countNodes(BinaryTree tree) {
        if (tree instanceof Leaf<?>) {
            return 1;
        } else if (tree instanceof Node<?> node) {
            return 1 + countNodes(node.left()) + countNodes(node.right());
        }
        return 0; // Never reached due to sealed nature
    }
    
    private static String getResponseDescription(HttpResponse response) {
        // Pattern matching with exhaustive case analysis
        return switch (response) {
            case SuccessResponse success -> 
                "Success (" + success.statusCode() + "): " + success.body();
            case RedirectResponse redirect -> 
                "Redirect to " + redirect.location();
            case ErrorResponse error -> 
                "Error (" + error.statusCode() + "): " + error.errorMessage();
        };
        // No default case needed - compiler knows the pattern match is exhaustive
    }
    
    private static int executeCommand(int currentValue, Command command) {
        return switch (command) {
            case AddCommand add -> currentValue + add.value();
            case SubtractCommand subtract -> currentValue - subtract.value();
            case ResetCommand reset -> 0;
        };
    }
    
    private static int evaluateExpression(Expression expression) {
        return switch (expression) {
            case Constant constant -> constant.value();
            case Addition addition -> 
                evaluateExpression(addition.left()) + evaluateExpression(addition.right());
            case Multiplication multiplication -> 
                evaluateExpression(multiplication.left()) * evaluateExpression(multiplication.right());
        };
    }
    
    private static void handleEvent(Event event) {
        switch (event) {
            case MouseClickEvent click -> 
                System.out.println("Mouse clicked at coordinates: (" + 
                        click.x() + ", " + click.y() + ")");
            case KeyPressEvent key -> 
                System.out.println("Key pressed: " + key.keyChar() + 
                        (key.withShift() ? " with SHIFT" : ""));
            case WindowResizeEvent resize -> 
                System.out.println("Window resized to: " + 
                        resize.width() + "x" + resize.height());
        }
    }
}

// Basic sealed class example
sealed abstract class Shape permits Circle, Rectangle, Triangle {
    public abstract double calculateArea();
}

final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

final class Rectangle extends Shape {
    private final double length;
    private final double width;
    
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        return length * width;
    }
}

final class Triangle extends Shape {
    private final double a;
    private final double b;
    private final double c;
    
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public double calculateArea() {
        // Heron's formula
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

// Sealed interface example
sealed interface Fruit permits Apple, Orange, Banana {}

record Apple(String variety, boolean organic) implements Fruit {}
record Orange(int segments) implements Fruit {}
record Banana(double ripeness) implements Fruit {}

// Nested hierarchy example
sealed abstract class Vehicle permits Car, Truck, Motorcycle {
    private final String make;
    private final String model;
    
    protected Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }
    
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
}

// Car allows further extension
sealed class Car extends Vehicle permits Sedan, SportsCar {
    private final int doors;
    
    public Car(String make, String model, int doors) {
        super(make, model);
        this.doors = doors;
    }
    
    public int getDoors() {
        return doors;
    }
}

// Sedan is final - cannot be extended further
final class Sedan extends Car {
    private final boolean hasLeatherSeats;
    
    public Sedan(String make, String model, int doors, boolean hasLeatherSeats) {
        super(make, model, doors);
        this.hasLeatherSeats = hasLeatherSeats;
    }
    
    public boolean hasLeatherSeats() {
        return hasLeatherSeats;
    }
}

// Non-existent but added to make the example complete
final class SportsCar extends Car {
    private final boolean convertible;
    
    public SportsCar(String make, String model, int doors, boolean convertible) {
        super(make, model, doors);
        this.convertible = convertible;
    }
    
    public boolean isConvertible() {
        return convertible;
    }
}

// Truck is final
final class Truck extends Vehicle {
    private final int payloadCapacity;
    
    public Truck(String make, String model, int payloadCapacity) {
        super(make, model);
        this.payloadCapacity = payloadCapacity;
    }
    
    public int getPayloadCapacity() {
        return payloadCapacity;
    }
}

// Motorcycle is final
final class Motorcycle extends Vehicle {
    private final boolean hasSideCar;
    
    public Motorcycle(String make, String model, boolean hasSideCar) {
        super(make, model);
        this.hasSideCar = hasSideCar;
    }
    
    public boolean hasSideCar() {
        return hasSideCar;
    }
}

// Sealed with records example
sealed interface BinaryTree<T> permits Node, Leaf {}

record Node<T>(BinaryTree<T> left, BinaryTree<T> right) implements BinaryTree<T> {}
record Leaf<T>(T value) implements BinaryTree<T> {}

// HTTP response hierarchy example
sealed interface HttpResponse permits SuccessResponse, RedirectResponse, ErrorResponse {}

record SuccessResponse(int statusCode, String statusText, String body) implements HttpResponse {}
record RedirectResponse(int statusCode, String statusText, String location) implements HttpResponse {}
record ErrorResponse(int statusCode, String statusText, String errorMessage) implements HttpResponse {}

// Command pattern example
sealed interface Command permits AddCommand, SubtractCommand, ResetCommand {}

record AddCommand(int value) implements Command {}
record SubtractCommand(int value) implements Command {}
record ResetCommand() implements Command {}

// Expression tree example
sealed interface Expression permits Constant, Addition, Multiplication {}

record Constant(int value) implements Expression {}
record Addition(Expression left, Expression right) implements Expression {}
record Multiplication(Expression left, Expression right) implements Expression {}

// Event handling example
sealed interface Event permits MouseClickEvent, KeyPressEvent, WindowResizeEvent {}

record MouseClickEvent(int x, int y) implements Event {}
record KeyPressEvent(char keyChar, boolean withShift) implements Event {}
record WindowResizeEvent(int width, int height) implements Event {}
