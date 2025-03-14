package com.example.java21;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Demonstrates String Templates introduced as a preview feature in Java 21.
 * 
 * String Templates provide a more flexible and readable way to create formatted
 * strings by embedding expressions directly within string literals.
 * 
 * Note: This is a preview feature in Java 21 and requires the --enable-preview flag
 * to run.
 */
public class StringTemplates {

    public static void main(String[] args) {
        introduceStringTemplates();
        comparisonWithOlderApproaches();
        basicTemplateExpressions();
        multilineTemplates();
        nestedTemplates();
        templateProcessors();
        bestPractices();
    }
    
    private static void introduceStringTemplates() {
        System.out.println("String Templates (Preview in Java 21)");
        System.out.println("------------------------------------");
        
        System.out.println("String Templates allow:");
        System.out.println("- Embedding expressions directly in string literals");
        System.out.println("- Improved readability compared to concatenation or String.format()");
        System.out.println("- Flexibility through template processors");
        System.out.println("- Reduced risk of formatting errors");
        
        System.out.println("\nBasic syntax: STR.\"Value of x is \{x}\"");
        System.out.println("Where STR is a template processor (or can be omitted for the default)");
        
        System.out.println();
    }
    
    private static void comparisonWithOlderApproaches() {
        System.out.println("Comparison with Older Approaches");
        System.out.println("--------------------------------");
        
        String name = "Alice";
        int age = 30;
        
        // Original concatenation approach
        System.out.println("String concatenation:");
        System.out.println("Hello, " + name + "! You are " + age + " years old.");
        
        // String.format approach
        System.out.println("\nString.format():");
        System.out.println(String.format("Hello, %s! You are %d years old.", name, age));
        
        // String.formatted approach (Java 15+)
        System.out.println("\nString.formatted():");
        System.out.println("Hello, %s! You are %d years old.".formatted(name, age));
        
        // MessageFormat approach
        System.out.println("\njava.text.MessageFormat:");
        System.out.println(java.text.MessageFormat.format("Hello, {0}! You are {1} years old.", name, age));
        
        // String template approach (Java 21 preview)
        System.out.println("\nString template (Java 21):");
        // Using backticks in comments to show the syntax, since this preview feature requires special flags
        // String greeting = STR."Hello, \{name}! You are \{age} years old.";
        System.out.println("STR.\"Hello, \\{name}! You are \\{age} years old.\"");
        System.out.println("Which would output: Hello, Alice! You are 30 years old.");
        
        System.out.println();
    }
    
    private static void basicTemplateExpressions() {
        System.out.println("Basic Template Expressions");
        System.out.println("-------------------------");
        
        // Variables to use in templates
        String product = "Laptop";
        double price = 999.99;
        boolean inStock = true;
        
        System.out.println("Example expressions (shown with equivalent String.format):");
        
        // Simple variable interpolation
        System.out.println("STR.\"Product: \\{product}\"");
        System.out.println("-> Product: Laptop");
        
        // Expressions with arithmetic
        System.out.println("STR.\"Price with tax: $\\{price * 1.08}\"");
        System.out.println("-> Price with tax: $1079.9892");
        
        // Expressions with formatting
        System.out.println("STR.\"Formatted price: $\\{String.format(\"%.2f\", price)}\"");
        System.out.println("-> Formatted price: $999.99");
        
        // Conditional expressions
        System.out.println("STR.\"Status: \\{inStock ? \"Available\" : \"Out of stock\"}\"");
        System.out.println("-> Status: Available");
        
        // Method calls in expressions
        System.out.println("STR.\"Uppercase product: \\{product.toUpperCase()}\"");
        System.out.println("-> Uppercase product: LAPTOP");
        
        System.out.println("\nAdvantages over older approaches:");
        System.out.println("- No need to count or match format specifiers and arguments");
        System.out.println("- Type checking at compile time");
        System.out.println("- More readable with direct embedding of expressions");
        System.out.println("- Expressions can be any valid Java expressions");
        
        System.out.println();
    }
    
    private static void multilineTemplates() {
        System.out.println("Multiline Templates");
        System.out.println("-------------------");
        
        // Sample data
        String customerName = "John Smith";
        List<String> items = List.of("Laptop", "Mouse", "Keyboard");
        Map<String, Double> prices = Map.of(
            "Laptop", 999.99,
            "Mouse", 24.99,
            "Keyboard", 49.99
        );
        LocalDate orderDate = LocalDate.now();
        
        System.out.println("Example multiline template (using text blocks):");
        System.out.println("STR.\"\"\"\n" +
                        "    ORDER RECEIPT\n" +
                        "    =============\n" +
                        "    Customer: \\{customerName}\n" +
                        "    Date: \\{orderDate}\n" +
                        "    \n" +
                        "    Items:\n" +
                        "    \\{items.stream().map(item -> \n" +
                        "        \"    - \" + item + \" $\" + prices.get(item)\n" +
                        "    ).toList().join(\"\\n\")}\n" +
                        "    \n" +
                        "    Total: $\\{prices.values().stream().mapToDouble(Double::doubleValue).sum()}\n" +
                        "    \"\"\"");
        
        System.out.println("\nExample output:");
        System.out.println("""
            ORDER RECEIPT
            =============
            Customer: John Smith
            Date: 2025-03-14
            
            Items:
                - Laptop $999.99
                - Mouse $24.99
                - Keyboard $49.99
                
            Total: $1074.97
            """);
        
        System.out.println("\nBenefits for multiline output:");
        System.out.println("- Combines text blocks with expression interpolation");
        System.out.println("- Maintains formatting and readability");
        System.out.println("- Simplifies complex template generation");
        
        System.out.println();
    }
    
    private static void nestedTemplates() {
        System.out.println("Nested Templates");
        System.out.println("----------------");
        
        // Sample data
        record User(String name, int age) {}
        List<User> users = List.of(
            new User("Alice", 28),
            new User("Bob", 35),
            new User("Charlie", 42)
        );
        
        System.out.println("Example with nested templates (HTML generation):");
        System.out.println("STR.\"\"\"\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>User List</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>Registered Users</h1>\n" +
                "    <ul>\n" +
                "        \\{users.stream()\n" +
                "            .map(user -> STR.\"<li>\\{user.name()} (\\{user.age()})</li>\")\n" +
                "            .toList()\n" +
                "            .join(\"\\n        \")}\n" +
                "    </ul>\n" +
                "</body>\n" +
                "</html>\n" +
                "\"\"\"");
        
        System.out.println("\nExample output:");
        System.out.println("""
            <!DOCTYPE html>
            <html>
            <head>
                <title>User List</title>
            </head>
            <body>
                <h1>Registered Users</h1>
                <ul>
                    <li>Alice (28)</li>
                    <li>Bob (35)</li>
                    <li>Charlie (42)</li>
                </ul>
            </body>
            </html>
            """);
        
        System.out.println("\nBenefits of nested templates:");
        System.out.println("- Composable template structures");
        System.out.println("- Clean separation of iteration and templating");
        System.out.println("- Improved readability for complex templates");
        
        System.out.println();
    }
    
    private static void templateProcessors() {
        System.out.println("Template Processors");
        System.out.println("-------------------");
        
        System.out.println("Template processors modify how templates are processed:");
        System.out.println("- STR: The default processor (just interpolates values)");
        System.out.println("- FMT: Provides String.format() functionality");
        System.out.println("- RAW: No processing, returns the StringTemplate object");
        System.out.println("- Custom processors can be created");
        
        // Examples of built-in processors
        String name = "World";
        double pi = Math.PI;
        
        System.out.println("\nExample with STR processor (default):");
        System.out.println("STR.\"Hello, \\{name}! Pi is \\{pi}\"");
        System.out.println("-> Hello, World! Pi is 3.141592653589793");
        
        System.out.println("\nExample with FMT processor:");
        System.out.println("FMT.\"Hello, %s! Pi is %.2f\\{name, pi}\"");
        System.out.println("-> Hello, World! Pi is 3.14");
        
        System.out.println("\nExample with RAW processor:");
        System.out.println("RAW.\"Hello, \\{name}! Pi is \\{pi}\"");
        System.out.println("-> Returns a StringTemplate object that can be manually processed");
        
        System.out.println("\nCreating a custom processor example:");
        System.out.println("""
            static StringTemplate.Processor<String> JSON = template -> {
                StringBuilder json = new StringBuilder("{");
                var fragments = template.fragments().toArray(String[]::new);
                var values = template.values().toArray();
                
                for (int i = 0; i < values.length; i++) {
                    String key = fragments[i].substring(fragments[i].lastIndexOf("\"") + 1);
                    json.append("\\"").append(key).append("\\":");
                    if (values[i] instanceof String) {
                        json.append("\\"").append(values[i]).append("\\"");
                    } else {
                        json.append(values[i]);
                    }
                    if (i < values.length - 1) json.append(",");
                }
                
                return json.append("}").toString();
            };
            
            // Usage:
            JSON."name:"\\{name},"pi":\\{pi}"
            // Output: {"name":"World","pi":3.141592653589793}
            """);
        
        System.out.println();
    }
    
    private static void bestPractices() {
        System.out.println("Best Practices");
        System.out.println("--------------");
        
        System.out.println("When to use String Templates:");
        System.out.println("- For readable text with interpolated values");
        System.out.println("- For complex templating with embedded expressions");
        System.out.println("- When generating structured output like HTML, JSON, etc.");
        System.out.println("- To replace complex string concatenation or formatting logic");
        
        System.out.println("\nWhen to use traditional approaches:");
        System.out.println("- For simple string concatenation (+ operator may be clearer)");
        System.out.println("- When precise formatting control is needed (String.format)");
        System.out.println("- For internationalization (ResourceBundle/MessageFormat)");
        System.out.println("- When backward compatibility with earlier Java versions is required");
        
        System.out.println("\nTips for String Templates:");
        System.out.println("- Use descriptive variable names for clarity");
        System.out.println("- Keep expressions simple within templates");
        System.out.println("- Extract complex logic outside the template");
        System.out.println("- Consider custom processors for specialized formatting needs");
        System.out.println("- Combine with text blocks for multi-line templates");
        
        System.out.println("\nRunning code with string templates (Java 21 Preview):");
        System.out.println("java --enable-preview -jar your-application.jar");
    }
}
