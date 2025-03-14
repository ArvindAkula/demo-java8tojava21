package com.example.java11;

/**
 * Demonstrates Single-File Source-Code Programs introduced in Java 11.
 * 
 * This feature allows running Java source files directly without explicit compilation:
 * `java SourceFile.java`
 * 
 * Note: This example shows what the source file would look like, but the actual
 * feature is in how these files are executed, not in the code itself.
 */
public class SingleFileSourceExample {

    public static void main(String[] args) {
        System.out.println("Java 11 Single-File Source-Code Programs");
        System.out.println("---------------------------------------");
        
        System.out.println("This feature allows you to run Java source files directly:");
        System.out.println("  java HelloWorld.java");
        
        System.out.println("\nBenefits:");
        System.out.println("- Simplified execution of simple programs");
        System.out.println("- No explicit compilation step needed");
        System.out.println("- Great for scripts, utilities, and quick tests");
        System.out.println("- Makes Java more approachable for beginners");
        
        System.out.println("\nExample of a single-file program (HelloWorld.java):");
        printExample();
        
        System.out.println("\nShebang (#!) support for Java scripts:");
        printShebangExample();
        
        System.out.println("\nUsage examples:");
        System.out.println("- Run directly: java HelloWorld.java");
        System.out.println("- With arguments: java HelloWorld.java arg1 arg2");
        System.out.println("- With Java options: java -Dprop=value HelloWorld.java");
        
        System.out.println("\nLimitations:");
        System.out.println("- Works only for single source files");
        System.out.println("- Not suitable for multi-class projects");
        System.out.println("- Dependencies still need to be specified in the classpath");
    }
    
    private static void printExample() {
        String example = 
                "public class HelloWorld {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "        \n" +
                "        // Access command-line arguments\n" +
                "        if (args.length > 0) {\n" +
                "            System.out.println(\"Arguments:\");\n" +
                "            for (String arg : args) {\n" +
                "                System.out.println(\"- \" + arg);\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        
        System.out.println("```java");
        System.out.println(example);
        System.out.println("```");
    }
    
    private static void printShebangExample() {
        String shebangExample = 
                "#!/usr/bin/java --source 11\n" +
                "\n" +
                "public class HelloScript {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello from a Java script!\");\n" +
                "    }\n" +
                "}";
        
        System.out.println("```java");
        System.out.println(shebangExample);
        System.out.println("```");
        
        System.out.println("\nMake the file executable: chmod +x HelloScript.java");
        System.out.println("Run it directly: ./HelloScript.java");
    }
    
    // You would typically create a file like this:
    
    /*
     * Calculator.java - can be run with: java Calculator.java 5 + 3
     *
     * #!/usr/bin/java --source 11
     * 
     * public class Calculator {
     *     public static void main(String[] args) {
     *         if (args.length != 3) {
     *             System.out.println("Usage: Calculator number1 operation number2");
     *             System.out.println("Example: Calculator 5 + 3");
     *             return;
     *         }
     *         
     *         try {
     *             double num1 = Double.parseDouble(args[0]);
     *             String op = args[1];
     *             double num2 = Double.parseDouble(args[2]);
     *             
     *             double result = switch (op) {
     *                 case "+" -> num1 + num2;
     *                 case "-" -> num1 - num2;
     *                 case "*" -> num1 * num2;
     *                 case "/" -> num1 / num2;
     *                 default -> throw new IllegalArgumentException("Unknown operator: " + op);
     *             };
     *             
     *             System.out.printf("%.2f %s %.2f = %.2f%n", num1, op, num2, result);
     *         } catch (NumberFormatException e) {
     *             System.out.println("Error: Invalid number format");
     *         } catch (IllegalArgumentException e) {
     *             System.out.println("Error: " + e.getMessage());
     *         }
     *     }
     * }
     */
}
