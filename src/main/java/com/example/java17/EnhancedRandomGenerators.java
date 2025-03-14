package com.example.java17;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.IntStream;

/**
 * Demonstrates the Enhanced Pseudo-Random Number Generators introduced in Java 17.
 * 
 * This feature provides a new interface hierarchy and implementations for random number
 * generation, offering better performance, more algorithms, and a more flexible API.
 */
public class EnhancedRandomGenerators {

    public static void main(String[] args) {
        introduceRandomGenerators();
        simpleRandomExamples();
        multipleAlgorithms();
        streamOperations();
        jumpAndLeapOperations();
        legacyCompatibility();
        bestPractices();
    }
    
    private static void introduceRandomGenerators() {
        System.out.println("Enhanced Pseudo-Random Number Generators (Java 17)");
        System.out.println("--------------------------------------------------");
        
        System.out.println("Java 17 introduced a new random number generator framework that:");
        System.out.println("- Provides a unified API for all random number generators");
        System.out.println("- Offers multiple high-quality algorithm implementations");
        System.out.println("- Improves performance for random number generation");
        System.out.println("- Simplifies common random generation tasks");
        System.out.println("- Maintains compatibility with legacy Random class");
        
        System.out.println("\nKey components:");
        System.out.println("- RandomGenerator: The main interface for all random generators");
        System.out.println("- RandomGeneratorFactory: Factory for creating generator instances");
        System.out.println("- SplittableRandomGenerator: Generators that can be split into multiple streams");
        System.out.println("- JumpableRandomGenerator: Generators that can jump ahead quickly");
        System.out.println("- LeapableRandomGenerator: Generators that can leap ahead even further");
        
        System.out.println();
    }
    
    private static void simpleRandomExamples() {
        System.out.println("Basic Random Generator Usage");
        System.out.println("--------------------------");
        
        // Create a default random generator
        RandomGenerator random = RandomGenerator.getDefault();
        
        System.out.println("Default generator: " + random.getClass().getSimpleName());
        System.out.println("Random int: " + random.nextInt());
        System.out.println("Random int (bounded): " + random.nextInt(100));
        System.out.println("Random long: " + random.nextLong());
        System.out.println("Random float: " + random.nextFloat());
        System.out.println("Random double: " + random.nextDouble());
        System.out.println("Random boolean: " + random.nextBoolean());
        System.out.println("Random gaussian: " + random.nextGaussian());
        
        // Generate bytes
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        System.out.print("Random bytes: ");
        for (byte b : bytes) {
            System.out.print(String.format("%02X ", b));
        }
        System.out.println();
        
        System.out.println();
    }
    
    private static void multipleAlgorithms() {
        System.out.println("Multiple Algorithms");
        System.out.println("-------------------");
        
        System.out.println("Available random number generator algorithms:");
        
        // List all available algorithms
        RandomGeneratorFactory.all()
            .sorted((f1, f2) -> f1.name().compareTo(f2.name()))
            .limit(10) // Limiting to 10 for demonstration
            .forEach(factory -> {
                System.out.printf("- %s: %s (statistically %s)%n", 
                    factory.name(),
                    factory.group(),
                    factory.isStatisticallySound() ? "sound" : "not sound");
            });
        
        System.out.println("...and more");
        
        // Create specific generators
        RandomGenerator xoroshiro = RandomGeneratorFactory.of("Xoroshiro128PlusPlus")
                                        .create();
        RandomGenerator l64X128 = RandomGeneratorFactory.of("L64X128StarStarRandom")
                                     .create();
        
        System.out.println("\nComparing outputs from different generators:");
        System.out.println("Xoroshiro128++: " + xoroshiro.nextInt(1000));
        System.out.println("L64X128StarStar: " + l64X128.nextInt(1000));
        
        System.out.println("\nChoosing algorithms based on needs:");
        System.out.println("- For cryptographic security: SecureRandom (outside this API)");
        System.out.println("- For high quality, fast generation: L128X128MixRandom");
        System.out.println("- For compatibility with legacy code: Random");
        System.out.println("- For reproducible tests: SplittableRandom with a known seed");
        
        System.out.println();
    }
    
    private static void streamOperations() {
        System.out.println("Stream Operations");
        System.out.println("-----------------");
        
        RandomGenerator random = RandomGenerator.getDefault();
        
        // Generate stream of random ints
        System.out.println("5 random integers between 1 and 100:");
        IntStream intStream = random.ints(5, 1, 101);
        intStream.forEach(i -> System.out.print(i + " "));
        System.out.println();
        
        // Generate stream of random doubles
        System.out.println("\n5 random doubles between 0.0 and 1.0:");
        random.doubles(5).forEach(d -> System.out.printf("%.6f ", d));
        System.out.println();
        
        // Process random values using streams
        System.out.println("\nSum of 1000 random integers between 1 and 10:");
        int sum = random.ints(1000, 1, 11).sum();
        System.out.println(sum);
        
        // Generate and filter random values
        System.out.println("\n5 even random integers between 1 and 100:");
        random.ints(1, 101)
              .filter(n -> n % 2 == 0)
              .limit(5)
              .forEach(i -> System.out.print(i + " "));
        System.out.println();
        
        System.out.println();
    }
    
    private static void jumpAndLeapOperations() {
        System.out.println("Jump and Leap Operations");
        System.out.println("-----------------------");
        
        System.out.println("Some random generators support 'jumping' or 'leaping' ahead:");
        
        try {
            // Try to get a jumpable generator
            RandomGeneratorFactory<? extends RandomGenerator> factory = 
                RandomGeneratorFactory.all()
                    .filter(f -> f.name().equals("Xoshiro256PlusPlus"))
                    .findFirst()
                    .orElse(null);
            
            if (factory != null) {
                RandomGenerator baseGenerator = factory.create(123); // Seeded for reproducibility
                
                // Cast to appropriate interface if supported
                if (baseGenerator instanceof JumpableRandomGenerator) {
                    JumpableRandomGenerator jumpable = (JumpableRandomGenerator) baseGenerator;
                    
                    System.out.println("Original sequence:");
                    for (int i = 0; i < 3; i++) {
                        System.out.print(baseGenerator.nextInt(100) + " ");
                    }
                    System.out.println();
                    
                    // Jump ahead (equivalent to many nextInt() calls)
                    System.out.println("After jump:");
                    JumpableRandomGenerator jumped = jumpable.copy();
                    jumped.jump();
                    for (int i = 0; i < 3; i++) {
                        System.out.print(jumped.nextInt(100) + " ");
                    }
                    System.out.println();
                    
                    // Check if leapable too
                    if (jumpable instanceof LeapableRandomGenerator) {
                        LeapableRandomGenerator leapable = (LeapableRandomGenerator) jumpable;
                        
                        // Leap ahead (equivalent to many jump() calls)
                        System.out.println("After leap:");
                        LeapableRandomGenerator leaped = leapable.copy();
                        leaped.leap();
                        for (int i = 0; i < 3; i++) {
                            System.out.print(leaped.nextInt(100) + " ");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("Selected generator doesn't support jumping.");
                }
            } else {
                System.out.println("Xoshiro256PlusPlus generator not available.");
            }
        } catch (Exception e) {
            System.out.println("Exception when demonstrating jump/leap: " + e.getMessage());
        }
        
        System.out.println("\nUse cases for jump and leap:");
        System.out.println("- Parallel stream generation without contention");
        System.out.println("- Creating multiple independent but deterministic sequences");
        System.out.println("- Skipping ahead in simulations");
        
        System.out.println();
    }
    
    private static void legacyCompatibility() {
        System.out.println("Legacy Compatibility");
        System.out.println("--------------------");
        
        // The old java.util.Random implements the new RandomGenerator interface
        java.util.Random legacyRandom = new java.util.Random();
        RandomGenerator modernInterface = legacyRandom; // Direct assignment works
        
        System.out.println("Legacy Random instance is a RandomGenerator: " + 
                (legacyRandom instanceof RandomGenerator));
                
        // Compare implementations
        System.out.println("\nComparing implementations:");
        System.out.println("Legacy Random: " + legacyRandom.nextInt(100));
        System.out.println("Same instance through RandomGenerator: " + modernInterface.nextInt(100));
        
        // Get a Random-compatible generator through the factory
        RandomGenerator randomCompatible = RandomGeneratorFactory.of("Random").create();
        System.out.println("Factory-created Random: " + randomCompatible.nextInt(100));
        
        System.out.println("\nMigration path:");
        System.out.println("1. Replace 'new Random()' with 'RandomGenerator.getDefault()'");
        System.out.println("2. Use the enhanced API methods when needed");
        System.out.println("3. Experiment with different algorithms for specific needs");
        
        System.out.println();
    }
    
    private static void bestPractices() {
        System.out.println("Best Practices");
        System.out.println("--------------");
        
        System.out.println("1. Choose the right algorithm for your needs:");
        System.out.println("   - Default for general purpose");
        System.out.println("   - SecureRandom for cryptographic security");
        System.out.println("   - Specific algorithms for performance/quality tradeoffs");
        
        System.out.println("\n2. Seeding for reproducibility:");
        System.out.println("   RandomGenerator seeded = RandomGeneratorFactory.of(\"Xoshiro256PlusPlus\").create(seed);");
        
        System.out.println("\n3. Stream generation for large quantities of random numbers:");
        System.out.println("   random.ints(count, min, max) or random.doubles(count, min, max)");
        
        System.out.println("\n4. Thread safety considerations:");
        System.out.println("   - RandomGenerator implementations are not generally thread-safe");
        System.out.println("   - Use ThreadLocalRandom for thread-local sequences");
        System.out.println("   - Or use jumpable generators to create independent sequences per thread");
        
        System.out.println("\n5. Testing with random data:");
        System.out.println("   - Always use a seeded generator for reproducible tests");
        System.out.println("   - Record seeds used in failed tests for debugging");
    }
    
    // Additional interfaces mentioned in the examples (for illustration)
    interface JumpableRandomGenerator extends RandomGenerator {
        JumpableRandomGenerator copy();
        void jump();
    }
    
    interface LeapableRandomGenerator extends JumpableRandomGenerator {
        LeapableRandomGenerator copy();
        void leap();
    }
}
