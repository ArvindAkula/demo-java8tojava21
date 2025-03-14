package com.example.java12;

/**
 * Demonstrates Switch Expressions introduced in Java 12 as a preview feature.
 * (Finally standardized in Java 14)
 * 
 * Switch expressions make switch more powerful by:
 * - Allowing it to be used as an expression that returns a value
 * - Supporting a new lambda-like syntax with the arrow (->) operator
 * - Avoiding fall-through behavior with the new syntax
 * - Allowing multiple case labels in a single case
 */
public class SwitchExpressions {

    public static void main(String[] args) {
        traditionalSwitch();
        switchWithArrowSyntax();
        switchAsExpression();
        multiCaseLabels();
        advancedSwitchUsage();
    }
    
    /**
     * Traditional switch statement approach (pre-Java 12)
     */
    private static void traditionalSwitch() {
        System.out.println("1. Traditional Switch Statement");
        System.out.println("-----------------------------");
        
        DayOfWeek day = DayOfWeek.WEDNESDAY;
        
        String typeOfDay;
        // Traditional switch statement with break statements
        switch (day) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                typeOfDay = "Weekday";
                break;
            case SATURDAY:
            case SUNDAY:
                typeOfDay = "Weekend";
                break;
            default:
                typeOfDay = "Unknown";
                break;
        }
        
        System.out.println(day + " is a " + typeOfDay);
        
        // Common problems with traditional switch:
        int value = 2;
        String result;
        
        // 1. Forgetting break statements leads to fall-through
        switch (value) {
            case 1:
                result = "One";
                // Missing break - falls through to case 2!
            case 2:
                result = "Two";
                break;
            case 3:
                result = "Three";
                break;
            default:
                result = "Other";
        }
        
        System.out.println("value = " + value + ", result = " + result);
        // Output will be "Two" even though value matches both cases
        
        System.out.println();
    }
    
    /**
     * Switch with arrow syntax (Java 12 preview feature)
     */
    private static void switchWithArrowSyntax() {
        System.out.println("2. Switch with Arrow Syntax");
        System.out.println("--------------------------");
        
        DayOfWeek day = DayOfWeek.WEDNESDAY;
        
        // Arrow syntax switch statement - no break needed
        switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> System.out.println(day + " is a Weekday");
            case SATURDAY, SUNDAY -> System.out.println(day + " is a Weekend");
            default -> System.out.println(day + " is Unknown");
        }
        
        // Each case executes only the code to the right of the arrow
        // No fall-through happens with the arrow syntax
        int value = 2;
        
        switch (value) {
            case 1 -> System.out.println("One");
            case 2 -> System.out.println("Two");
            case 3 -> System.out.println("Three");
            default -> System.out.println("Other");
        }
        
        // Can use blocks for multiple statements
        switch (value) {
            case 1 -> {
                System.out.println("The value is One");
                System.out.println("It's the smallest positive integer");
            }
            case 2 -> {
                System.out.println("The value is Two");
                System.out.println("It's the only even prime number");
            }
            default -> System.out.println("Other value");
        }
        
        System.out.println();
    }
    
    /**
     * Switch as an expression that returns a value (Java 12 preview feature)
     */
    private static void switchAsExpression() {
        System.out.println("3. Switch as an Expression");
        System.out.println("-------------------------");
        
        DayOfWeek day = DayOfWeek.THURSDAY;
        
        // Switch as an expression with arrow syntax
        String typeOfDay = switch (day) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
            case SATURDAY, SUNDAY -> "Weekend";
            default -> "Unknown";
        };
        
        System.out.println(day + " is a " + typeOfDay);
        
        // Switch expression with code blocks must use 'yield' to return a value
        String description = switch (day) {
            case MONDAY -> {
                System.out.println("Processing Monday...");
                yield "Start of the work week";
            }
            case FRIDAY -> {
                System.out.println("Processing Friday...");
                yield "End of the work week";
            }
            case SATURDAY, SUNDAY -> {
                String result = "Weekend day";
                yield result;
            }
            default -> "Regular day";
        };
        
        System.out.println(day + " description: " + description);
        
        // Switch expressions must be exhaustive
        // Either cover all possible values or include a default case
        int numberOfLetters = switch (day) {
            case MONDAY -> 6;
            case TUESDAY -> 7;
            case WEDNESDAY -> 9;
            case THURSDAY -> 8;
            case FRIDAY -> 6;
            case SATURDAY -> 8;
            case SUNDAY -> 6;
            // No default needed here since we've covered all enum values
        };
        
        System.out.println(day + " has " + numberOfLetters + " letters");
        
        System.out.println();
    }
    
    /**
     * Multiple case labels in a single case
     */
    private static void multiCaseLabels() {
        System.out.println("4. Multiple Case Labels");
        System.out.println("----------------------");
        
        for (int month = 1; month <= 12; month++) {
            int daysInMonth = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                case 2 -> 28; // Simplified, ignoring leap years
                default -> throw new IllegalArgumentException("Invalid month: " + month);
            };
            
            System.out.println("Month " + month + " has " + daysInMonth + " days");
        }
        
        System.out.println();
    }
    
    /**
     * Some more advanced usage of switch expressions
     */
    private static void advancedSwitchUsage() {
        System.out.println("5. Advanced Switch Usage");
        System.out.println("-----------------------");
        
        // Using switch expressions in larger expressions
        DayOfWeek today = DayOfWeek.FRIDAY;
        boolean isWeekend = switch (today) {
            case SATURDAY, SUNDAY -> true;
            default -> false;
        };
        
        System.out.println("Is today a weekend? " + isWeekend);
        
        // Nested switch expressions
        DayOfWeek day = DayOfWeek.MONDAY;
        String schedule = switch (day) {
            case MONDAY, WEDNESDAY, FRIDAY -> "Gym day";
            case TUESDAY, THURSDAY -> switch (day) {
                case TUESDAY -> "Swimming day";
                case THURSDAY -> "Yoga day";
                default -> "Unexpected day"; // Never reaches here but needed for exhaustiveness
            };
            case SATURDAY, SUNDAY -> "Rest day";
        };
        
        System.out.println("Today's schedule: " + schedule);
        
        // Using with different types
        Object obj = "Hello";
        
        String type = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String s -> "String: " + s;
            case Double d -> "Double: " + d;
            case null -> "null value";
            default -> obj.getClass().getName();
        };
        
        System.out.println("Object type: " + type);
    }
    
    // Enum for day of week examples
    enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
