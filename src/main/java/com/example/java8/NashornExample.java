package com.example.java8;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * Demonstrates the Nashorn JavaScript Engine introduced in Java 8.
 * Note: Nashorn was deprecated in Java 11 and removed in Java 15.
 * This example is for historical completeness but may not work in
 * Java versions 15+.
 */
public class NashornExample {

    public static void main(String[] args) {
        try {
            System.out.println("Note: Nashorn was deprecated in Java 11 and removed in Java 15.");
            System.out.println("This example won't work if you're running Java 15+.");
            
            // Get the JavaScript engine
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            
            if (engine == null) {
                System.out.println("JavaScript engine not found. This may be because you're running Java 15+.");
                return;
            }
            
            // Basic JavaScript evaluation
            System.out.println("\n--- Basic JavaScript Evaluation ---");
            engine.eval("print('Hello from JavaScript!');");
            
            // Evaluating expressions
            Object result = engine.eval("10 + 20");
            System.out.println("10 + 20 = " + result);
            
            // Using variables
            engine.put("name", "John");
            engine.eval("print('Hello, ' + name);");
            
            // Get variables from JavaScript
            engine.eval("var x = 42;");
            Object x = engine.get("x");
            System.out.println("x = " + x);
            
            // Define and call JavaScript functions
            System.out.println("\n--- JavaScript Functions ---");
            engine.eval("function greet(name) { return 'Hello, ' + name + '!'; }");
            Invocable invocable = (Invocable) engine;
            Object greetResult = invocable.invokeFunction("greet", "Bob");
            System.out.println(greetResult);
            
            // Execute multi-line script
            System.out.println("\n--- Multi-line Script ---");
            engine.eval(
                "var user = {\n" +
                "    name: 'Alice',\n" +
                "    age: 30,\n" +
                "    toString: function() {\n" +
                "        return this.name + ' is ' + this.age + ' years old';\n" +
                "    }\n" +
                "};\n" +
                "print(user.toString());"
            );
            
            // Access JavaScript object properties
            engine.eval("var user = { name: 'Alice', age: 30 };");
            engine.eval("var userName = user.name;");
            Object userName = engine.get("userName");
            System.out.println("User name: " + userName);
            
            // Pass Java objects to JavaScript
            System.out.println("\n--- Passing Java Objects to JavaScript ---");
            engine.put("javaDate", new Date());
            engine.eval("print('Date from Java: ' + javaDate.toString());");
            
            // Call Java methods from JavaScript
            System.out.println("\n--- Calling Java Methods from JavaScript ---");
            engine.put("javaObject", new JavaScriptConnector());
            engine.eval("javaObject.sayHello('World');");
            engine.eval("var result = javaObject.multiply(5, 7);");
            engine.eval("print('5 * 7 = ' + result);");
            
            // Implement JavaScript interface in Java
            System.out.println("\n--- Implementing JavaScript Interface in Java ---");
            engine.eval(
                "var greeter = {\n" +
                "    sayHello: function(name) {\n" +
                "        print('Hello, ' + name + '!');\n" +
                "        return 'Greeting sent to ' + name;\n" +
                "    }\n" +
                "};"
            );
            
            Object jsGreeter = engine.get("greeter");
            Greeter greeterImpl = invocable.getInterface(jsGreeter, Greeter.class);
            String greetingResult = greeterImpl.sayHello("Charlie");
            System.out.println("Result: " + greetingResult);
            
            // Loading external script from file
            System.out.println("\n--- Attempting to Load External Script ---");
            try {
                // Note: This requires a script.js file in the project root
                // The file would contain JavaScript code
                engine.eval(new FileReader("script.js"));
                System.out.println("Script loaded successfully");
            } catch (IOException e) {
                System.out.println("Could not load external script: " + e.getMessage());
                System.out.println("This is expected if you don't have a script.js file");
            }
            
        } catch (ScriptException e) {
            System.out.println("Script execution error: " + e.getMessage());
        } catch (NoSuchMethodException e) {
            System.out.println("Method not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
    
    // Interface to be implemented by JavaScript code
    interface Greeter {
        String sayHello(String name);
    }
    
    // Java class to be used from JavaScript
    static class JavaScriptConnector {
        public void sayHello(String name) {
            System.out.println("Hello, " + name + " from Java!");
        }
        
        public int multiply(int a, int b) {
            return a * b;
        }
    }
}
