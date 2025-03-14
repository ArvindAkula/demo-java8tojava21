package com.example.java8;

/**
 * Demonstrates Default Methods in Interfaces introduced in Java 8.
 */
public class DefaultMethods {

    public static void main(String[] args) {
        // Using DefaultVehicle implementation
        Vehicle car = new Car();
        car.startEngine();
        car.accelerate();
        car.brake();
        car.honk();
        car.turnOffEngine();
        
        System.out.println();
        
        // Using CustomVehicle implementation with overridden default methods
        Vehicle sportsCar = new SportsCar();
        sportsCar.startEngine();
        sportsCar.accelerate();
        sportsCar.brake();
        sportsCar.honk();
        sportsCar.turnOffEngine();
        
        System.out.println();
        
        // Multiple inheritance example
        ElectricCar electricCar = new ElectricCar();
        electricCar.charge();
        electricCar.startEngine(); // This method is implemented in the class 
                                  // because both interfaces have the same default method
        
        System.out.println();
        
        // Static method in interface
        String typeInfo = Vehicle.getVehicleTypeInfo();
        System.out.println("Vehicle type info: " + typeInfo);
    }

    // Interface with default and static methods
    interface Vehicle {
        // Abstract methods - must be implemented by all classes
        void accelerate();
        void brake();
        
        // Default methods - provide implementation in the interface
        default void startEngine() {
            System.out.println("Starting the engine...");
        }
        
        default void turnOffEngine() {
            System.out.println("Turning off the engine...");
        }
        
        default void honk() {
            System.out.println("Beep beep!");
        }
        
        // Static method in interface
        static String getVehicleTypeInfo() {
            return "This is a general vehicle interface that can be used for different types of vehicles.";
        }
    }
    
    // Another interface with a default method of the same name
    interface ElectricVehicle {
        void charge();
        
        default void startEngine() {
            System.out.println("Powering up electric motor...");
        }
    }
    
    // Basic implementation using default methods
    static class Car implements Vehicle {
        @Override
        public void accelerate() {
            System.out.println("Car is accelerating...");
        }
        
        @Override
        public void brake() {
            System.out.println("Car is braking...");
        }
    }
    
    // Implementation that overrides default methods
    static class SportsCar implements Vehicle {
        @Override
        public void accelerate() {
            System.out.println("Sports car is accelerating FAST!");
        }
        
        @Override
        public void brake() {
            System.out.println("Sports car is braking hard!");
        }
        
        // Overriding default methods
        @Override
        public void startEngine() {
            System.out.println("Sports car engine roars to life!");
        }
        
        @Override
        public void honk() {
            System.out.println("Sports car honks loudly: HOOOONK!");
        }
    }
    
    // Multiple inheritance of default methods
    static class ElectricCar implements Vehicle, ElectricVehicle {
        @Override
        public void accelerate() {
            System.out.println("Electric car accelerates silently...");
        }
        
        @Override
        public void brake() {
            System.out.println("Electric car is using regenerative braking...");
        }
        
        @Override
        public void charge() {
            System.out.println("Electric car is charging...");
        }
        
        // Must override startEngine as it exists in both interfaces
        @Override
        public void startEngine() {
            // We can choose to use one of the default implementations
            ElectricVehicle.super.startEngine();
            System.out.println("Ready to drive!");
        }
    }
}
