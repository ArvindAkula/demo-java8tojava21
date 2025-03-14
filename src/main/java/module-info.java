/**
 * Module definition introduced in Java 9.
 * 
 * This file demonstrates how to define a module and specify its dependencies.
 * A Java module encapsulates Java packages and controls what is exposed to other modules.
 */
module com.example.demo {
    // Export packages to make them accessible to other modules
    exports com.example.java8;
    exports com.example.java9;
    exports com.example.java10;
    exports com.example.java11;
    exports com.example.java12;
    exports com.example.java13;
    exports com.example.java14;
    exports com.example.java15;
    exports com.example.java16;
    exports com.example.java17;
    exports com.example.java18;
    exports com.example.java19;
    exports com.example.java20;
    exports com.example.java21;
    
    // Required dependencies
    requires java.base; // Always implicitly required, can be explicitly stated
    requires java.scripting; // For Nashorn examples
    
    // Other common dependencies that might be required
    requires jdk.jshell; // For JShell examples
    requires jdk.incubator.vector; // For Vector API (incubator module)
    requires java.net.http; // For HTTP Client API (Java 11+)
    
    // Open packages for reflection (if needed)
    // opens com.example.java9 to java.desktop;
}
