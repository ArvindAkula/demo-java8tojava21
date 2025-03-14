package com.example.java9;

/**
 * Demonstrates private methods in interfaces introduced in Java 9.
 * 
 * Java 8 added default methods to interfaces, but there was no way to share code
 * between those methods without making it part of the public API.
 * Java 9 allows private methods in interfaces to enable code reuse without
 * expanding the interface's public API.
 */
public class PrivateInterfaceMethods {

    public static void main(String[] args) {
        // Create an instance of the implementation
        Logger consoleLogger = new ConsoleLogger("APP");
        
        // Use the interface methods
        consoleLogger.logInfo("User logged in");
        consoleLogger.logWarning("Low memory detected");
        consoleLogger.logError("Connection failed");
        
        System.out.println();
        
        // Another implementation
        Logger jsonLogger = new JsonLogger("SYSTEM");
        jsonLogger.logInfo("System started");
        jsonLogger.logWarning("High CPU usage");
        jsonLogger.logError("Disk failure detected");
    }
    
    /**
     * Logger interface with default methods that use private methods for code reuse.
     */
    interface Logger {
        // Abstract method - must be implemented
        String getLoggerName();
        
        // Default methods with implementation
        default void logInfo(String message) {
            log("INFO", message);
        }
        
        default void logWarning(String message) {
            log("WARNING", message);
        }
        
        default void logError(String message) {
            log("ERROR", message);
            // Add stack trace for errors
            logStackTrace();
        }
        
        // Private method - can be used by default methods but not accessible outside
        private void log(String level, String message) {
            System.out.println(formatLogMessage(level, message));
        }
        
        // Private method with implementation details
        private String formatLogMessage(String level, String message) {
            return String.format("[%s] %s: %s", getLoggerName(), level, message);
        }
        
        // Private static method
        private static void logStackTrace() {
            System.out.println("  Stack trace would be logged here");
        }
    }
    
    /**
     * Implementation of the Logger interface.
     */
    static class ConsoleLogger implements Logger {
        private final String name;
        
        public ConsoleLogger(String name) {
            this.name = name;
        }
        
        @Override
        public String getLoggerName() {
            return name;
        }
    }
    
    /**
     * Alternative implementation of the Logger interface.
     */
    static class JsonLogger implements Logger {
        private final String name;
        
        public JsonLogger(String name) {
            this.name = name;
        }
        
        @Override
        public String getLoggerName() {
            return name;
        }
        
        // Override a default method to customize behavior
        @Override
        public void logInfo(String message) {
            String json = String.format(
                    "{ \"logger\": \"%s\", \"level\": \"INFO\", \"message\": \"%s\" }",
                    getLoggerName(), message);
            System.out.println(json);
        }
    }
}
