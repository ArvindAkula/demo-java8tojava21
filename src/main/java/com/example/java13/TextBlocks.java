package com.example.java13;

/**
 * Demonstrates Text Blocks introduced in Java 13 as a preview feature.
 * (Standardized in Java 15)
 * 
 * Text blocks simplify writing multi-line strings without escape characters
 * and make it easier to represent formatted text in a more readable way.
 */
public class TextBlocks {

    public static void main(String[] args) {
        traditionalStrings();
        basicTextBlocks();
        textBlockFeatures();
        practicalExamples();
    }
    
    /**
     * Traditional multi-line strings (pre-Java 13)
     */
    private static void traditionalStrings() {
        System.out.println("1. Traditional String Handling");
        System.out.println("----------------------------");
        
        // Multi-line string with concatenation
        String multiline1 = "This is a multi-line string\n" +
                           "that requires explicit newline characters\n" +
                           "and concatenation operators.";
        System.out.println(multiline1);
        
        // HTML with escape characters
        String html = "<html>\n" +
                      "    <body>\n" +
                      "        <p>Hello, World!</p>\n" +
                      "    </body>\n" +
                      "</html>";
        System.out.println("\nHTML with traditional strings:");
        System.out.println(html);
        
        // JSON with escape characters
        String json = "{\n" +
                      "    \"name\": \"John Doe\",\n" +
                      "    \"age\": 30,\n" +
                      "    \"address\": {\n" +
                      "        \"street\": \"123 Main St\",\n" +
                      "        \"city\": \"Anytown\"\n" +
                      "    }\n" +
                      "}";
        System.out.println("\nJSON with traditional strings:");
        System.out.println(json);
        
        // SQL query with escape characters
        String sql = "SELECT id, first_name, last_name\n" +
                     "FROM employees\n" +
                     "WHERE department = 'Engineering'\n" +
                     "ORDER BY last_name ASC;";
        System.out.println("\nSQL with traditional strings:");
        System.out.println(sql);
        
        System.out.println();
    }
    
    /**
     * Basic usage of text blocks
     */
    private static void basicTextBlocks() {
        System.out.println("2. Basic Text Blocks");
        System.out.println("-------------------");
        
        // Simple text block (the opening """ must be followed by a line break)
        String textBlock = """
                This is a text block.
                It spans multiple lines.
                No need for explicit newline characters.
                """;
        System.out.println(textBlock);
        
        // HTML with text block
        String html = """
                <html>
                    <body>
                        <p>Hello, World!</p>
                    </body>
                </html>
                """;
        System.out.println("\nHTML with text block:");
        System.out.println(html);
        
        // JSON with text block
        String json = """
                {
                    "name": "John Doe",
                    "age": 30,
                    "address": {
                        "street": "123 Main St",
                        "city": "Anytown"
                    }
                }
                """;
        System.out.println("\nJSON with text block:");
        System.out.println(json);
        
        // SQL query with text block
        String sql = """
                SELECT id, first_name, last_name
                FROM employees
                WHERE department = 'Engineering'
                ORDER BY last_name ASC;
                """;
        System.out.println("\nSQL with text block:");
        System.out.println(sql);
        
        System.out.println();
    }
    
    /**
     * Special features and behaviors of text blocks
     */
    private static void textBlockFeatures() {
        System.out.println("3. Text Block Features");
        System.out.println("---------------------");
        
        // Indentation is preserved but common indentation is removed
        String indented = """
                This is the first line.
                    This line has 4 spaces of indentation.
                        This line has 8 spaces of indentation.
                Back to the first column.
                """;
        System.out.println("Indentation example:");
        System.out.println(indented);
        
        // Trailing whitespace is removed by default
        String trailing = """
                Line with trailing spaces:        
                Another line.
                """;
        System.out.println("\nTrailing whitespace example (spaces not visible):");
        System.out.println(trailing);
        
        // Escape sequences are processed
        String escaped = """
                Tab:\tTabbed
                Newline within block:\nForced new line
                Unicode: \u2022 Bullet point
                """;
        System.out.println("\nEscape sequences example:");
        System.out.println(escaped);
        
        // Prevent line break at the end
        String noFinalNewline = """
                This text block ends exactly \
                at the end of this line without a newline.""";
        System.out.println("\nNo final newline example:");
        System.out.println(noFinalNewline);
        
        // Escaping quotes
        String quotes = """
                "Double quotes" don't need to be escaped in a text block.
                Triple quotes need escaping: \"""
                Or can be split: ""
                "
                """;
        System.out.println("\nEscaping quotes example:");
        System.out.println(quotes);
        
        // String positioning using explicit new lines
        String positioned = """
                Column 1    Column 2    Column 3
                Value 1     Value 2     Value 3
                """;
        System.out.println("\nColumnar positioning example:");
        System.out.println(positioned);
        
        System.out.println();
    }
    
    /**
     * Practical examples of text blocks
     */
    private static void practicalExamples() {
        System.out.println("4. Practical Examples");
        System.out.println("--------------------");
        
        // String interpolation with placeholders
        String name = "Alice";
        int age = 30;
        
        String message = """
                Hello, %s!
                You are %d years old.
                Welcome to our service.
                """.formatted(name, age);
        System.out.println("String interpolation example:");
        System.out.println(message);
        
        // HTML email template
        String email = """
                <html>
                <head>
                    <title>Welcome Email</title>
                </head>
                <body>
                    <h1>Welcome, %s!</h1>
                    <p>Thank you for signing up to our service.</p>
                    <p>Here are your account details:</p>
                    <ul>
                        <li>Username: %s</li>
                        <li>Email: %s</li>
                    </ul>
                </body>
                </html>
                """.formatted(name, name.toLowerCase(), name.toLowerCase() + "@example.com");
        System.out.println("\nHTML email template example:");
        System.out.println(email);
        
        // Regex pattern (no need to escape backslashes)
        String regexPattern = """
                \\b(\\w+)\\b
                |(\\d{3})-(\\d{2})-(\\d{4})
                |[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}
                """;
        System.out.println("\nRegex pattern example:");
        System.out.println(regexPattern);
        
        // Code generation
        String javaCode = """
                public class GeneratedClass {
                    private String name;
                    private int value;
                    
                    public GeneratedClass(String name, int value) {
                        this.name = name;
                        this.value = value;
                    }
                    
                    @Override
                    public String toString() {
                        return "GeneratedClass{" +
                               "name='" + name + "'" +
                               ", value=" + value +
                               '}';
                    }
                }
                """;
        System.out.println("\nCode generation example:");
        System.out.println(javaCode);
        
        // Mixing text blocks with other String operations
        String mixed = """
                <div>
                    <h1>%s</h1>
                    <p>%s</p>
                </div>
                """.formatted("Title", "Content")
                   .replace("div", "section")
                   .toUpperCase();
        System.out.println("\nMixed operations example:");
        System.out.println(mixed);
        
        // Text block with variable substitution using string concatenation
        String city = "New York";
        String country = "USA";
        
        String address = """
                123 Broadway
                """ + city + """
                , """ + country + """
                10001
                """;
        System.out.println("\nVariable substitution with concatenation:");
        System.out.println(address);
    }
}
