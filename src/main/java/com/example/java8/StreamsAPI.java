package com.example.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Demonstrates the Stream API introduced in Java 8.
 */
public class StreamsAPI {

    public static void main(String[] args) {
        // Creating a stream
        Stream<String> streamFromValues = Stream.of("a", "b", "c");
        Stream<String> streamFromArray = Arrays.stream(new String[] {"a", "b", "c"});
        
        // Creating a stream from a list
        List<Person> persons = Arrays.asList(
                new Person("John", 25),
                new Person("Alice", 22),
                new Person("Bob", 30),
                new Person("Charlie", 25),
                new Person("David", 35),
                new Person("Eve", 22)
        );
        
        // Basic stream operations
        System.out.println("Names of people:");
        persons.stream()
                .map(Person::getName)
                .forEach(System.out::println);
                
        // Filtering
        System.out.println("\nPeople older than 25:");
        persons.stream()
                .filter(p -> p.getAge() > 25)
                .forEach(System.out::println);
                
        // Sorting
        System.out.println("\nPeople sorted by age:");
        persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .forEach(System.out::println);
                
        // Sorting by multiple criteria
        System.out.println("\nPeople sorted by age and then name:");
        persons.stream()
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getName))
                .forEach(System.out::println);
                
        // Limit and Skip
        System.out.println("\nFirst 2 people after skipping 2:");
        persons.stream()
                .skip(2)
                .limit(2)
                .forEach(System.out::println);
                
        // Collecting results
        List<String> names = persons.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("\nCollected names: " + names);
        
        // Joining strings
        String joinedNames = persons.stream()
                .map(Person::getName)
                .collect(Collectors.joining(", "));
        System.out.println("\nJoined names: " + joinedNames);
        
        // Grouping
        Map<Integer, List<Person>> personsByAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("\nPeople grouped by age: " + personsByAge);
        
        // Counting
        Map<Integer, Long> countByAge = persons.stream()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
        System.out.println("\nCount by age: " + countByAge);
        
        // Reducing
        Optional<Person> oldestPerson = persons.stream()
                .reduce((p1, p2) -> p1.getAge() > p2.getAge() ? p1 : p2);
        oldestPerson.ifPresent(p -> System.out.println("\nOldest person: " + p));
        
        // Numeric streams
        int sum = IntStream.rangeClosed(1, 10).sum();
        System.out.println("\nSum of numbers 1 to 10: " + sum);
        
        // Average age
        double averageAge = persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
        System.out.println("\nAverage age: " + averageAge);
        
        // Finding elements
        Optional<Person> anyPersonAge25 = persons.stream()
                .filter(p -> p.getAge() == 25)
                .findAny();
        anyPersonAge25.ifPresent(p -> System.out.println("\nAny person of age 25: " + p));
        
        // Matching
        boolean allOver18 = persons.stream().allMatch(p -> p.getAge() > 18);
        boolean anyOver30 = persons.stream().anyMatch(p -> p.getAge() > 30);
        boolean noneOver40 = persons.stream().noneMatch(p -> p.getAge() > 40);
        
        System.out.println("\nAll over 18? " + allOver18);
        System.out.println("Any over 30? " + anyOver30);
        System.out.println("None over 40? " + noneOver40);
        
        // Parallel streams for better performance with large datasets
        long count = persons.parallelStream()
                .filter(p -> p.getAge() > 25)
                .count();
        System.out.println("\nNumber of people over 25 (parallel): " + count);
    }
    
    static class Person {
        private String name;
        private int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}
