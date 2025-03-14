package com.example.java21;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Demonstrates Virtual Threads introduced in Java 21 (Project Loom).
 * 
 * Virtual threads are lightweight threads that are managed by the Java runtime
 * rather than the operating system. They enable a high level of concurrency
 * with minimal resource usage, making them ideal for I/O-bound applications.
 */
public class VirtualThreads {

    // Counter for tracking thread creation
    private static final AtomicInteger threadCount = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        introduceVirtualThreads();
        compareThreadTypes();
        virtualThreadCreation();
        executorWithVirtualThreads();
        massiveConcurrency();
        structuredConcurrency();
    }
    
    /**
     * Introduction to virtual threads
     */
    private static void introduceVirtualThreads() {
        System.out.println("1. Introduction to Virtual Threads");
        System.out.println("--------------------------------");
        
        System.out.println("Virtual threads are:");
        System.out.println("- Lightweight threads managed by the JVM rather than the OS");
        System.out.println("- Ideal for I/O-bound applications that need high concurrency");
        System.out.println("- Implemented using carrier threads (usually platform threads)");
        System.out.println("- Designed to reduce the cost of thread creation and context switching");
        System.out.println("- A solution to the C10K problem (handling 10,000+ connections)");
        System.out.println("- Part of Project Loom, introduced in Java 21 as a standard feature");
        
        System.out.println();
    }
    
    /**
     * Compare platform threads and virtual threads
     */
    private static void compareThreadTypes() throws Exception {
        System.out.println("2. Comparing Thread Types");
        System.out.println("------------------------");
        
        // Number of threads to create
        final int threadCount = 10_000;
        
        // Measure platform thread creation time
        Instant platformStart = Instant.now();
        try {
            System.out.println("Creating " + threadCount + " platform threads...");
            List<Thread> platformThreads = new ArrayList<>();
            
            for (int i = 0; i < threadCount; i++) {
                Thread platformThread = new Thread(() -> {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
                platformThreads.add(platformThread);
            }
            
            // Start a small subset of the threads (creating all of them might crash the system)
            int startCount = Math.min(100, threadCount);
            for (int i = 0; i < startCount; i++) {
                platformThreads.get(i).start();
            }
            
            // Wait for the started threads to complete
            for (int i = 0; i < startCount; i++) {
                platformThreads.get(i).join();
            }
            
        } catch (OutOfMemoryError e) {
            System.out.println("Out of memory while creating platform threads: " + e.getMessage());
        }
        
        Instant platformEnd = Instant.now();
        Duration platformDuration = Duration.between(platformStart, platformEnd);
        System.out.println("Platform thread creation time: " + platformDuration.toMillis() + "ms");
        
        // Measure virtual thread creation time
        Instant virtualStart = Instant.now();
        System.out.println("\nCreating " + threadCount + " virtual threads...");
        List<Thread> virtualThreads = new ArrayList<>();
        
        for (int i = 0; i < threadCount; i++) {
            Thread virtualThread = Thread.ofVirtual().unstarted(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            virtualThreads.add(virtualThread);
        }
        
        // Start all virtual threads
        for (Thread thread : virtualThreads) {
            thread.start();
        }
        
        // Wait for all virtual threads to complete
        for (Thread thread : virtualThreads) {
            thread.join();
        }
        
        Instant virtualEnd = Instant.now();
        Duration virtualDuration = Duration.between(virtualStart, virtualEnd);
        System.out.println("Virtual thread creation and execution time: " + virtualDuration.toMillis() + "ms");
        
        System.out.println("\nComparison:");
        System.out.println("- Platform threads: Heavy, each thread maps to an OS thread");
        System.out.println("- Virtual threads: Lightweight, managed by the JVM");
        System.out.println("- Memory usage: Platform ~2MB per thread, Virtual ~2KB per thread");
        System.out.println("- Context switching: Platform (expensive OS operation), Virtual (cheap JVM operation)");
        System.out.println("- Use platform threads for: CPU-intensive tasks, native code, thread-local heavy code");
        System.out.println("- Use virtual threads for: I/O-bound tasks, blocking operations, high concurrency");
        
        System.out.println();
    }
    
    /**
     * Different ways to create virtual threads
     */
    private static void virtualThreadCreation() throws Exception {
        System.out.println("3. Virtual Thread Creation Methods");
        System.out.println("--------------------------------");
        
        // Method 1: Thread.ofVirtual().start()
        System.out.println("Method 1: Thread.ofVirtual().start()");
        Thread vt1 = Thread.ofVirtual().start(() -> {
            System.out.println("  Running in virtual thread: " + Thread.currentThread());
        });
        vt1.join();
        
        // Method 2: Thread.ofVirtual().unstarted()
        System.out.println("\nMethod 2: Thread.ofVirtual().unstarted()");
        Thread vt2 = Thread.ofVirtual().unstarted(() -> {
            System.out.println("  Running in virtual thread: " + Thread.currentThread());
        });
        vt2.start();
        vt2.join();
        
        // Method 3: Thread.startVirtualThread()
        System.out.println("\nMethod 3: Thread.startVirtualThread()");
        Thread vt3 = Thread.startVirtualThread(() -> {
            System.out.println("  Running in virtual thread: " + Thread.currentThread());
        });
        vt3.join();
        
        // Method 4: Custom ThreadFactory
        System.out.println("\nMethod 4: Custom ThreadFactory");
        ThreadFactory factory = Thread.ofVirtual().factory();
        Thread vt4 = factory.newThread(() -> {
            System.out.println("  Running in virtual thread: " + Thread.currentThread());
        });
        vt4.start();
        vt4.join();
        
        // Method 5: Configuring virtual thread properties
        System.out.println("\nMethod 5: Configuring virtual thread properties");
        Thread vt5 = Thread.ofVirtual()
                .name("custom-named-vthread")
                .start(() -> {
                    System.out.println("  Thread name: " + Thread.currentThread().getName());
                    System.out.println("  Is virtual: " + Thread.currentThread().isVirtual());
                });
        vt5.join();
        
        System.out.println();
    }
    
    /**
     * Using ExecutorService with virtual threads
     */
    private static void executorWithVirtualThreads() throws Exception {
        System.out.println("4. ExecutorService with Virtual Threads");
        System.out.println("------------------------------------");
        
        // Reset thread count
        threadCount.set(0);
        
        // Traditional thread pool with platform threads
        System.out.println("Traditional fixed thread pool (platform threads):");
        try (ExecutorService executorPlatform = Executors.newFixedThreadPool(10, 
                r -> new Thread(r, "platform-" + threadCount.incrementAndGet()))) {
            
            for (int i = 0; i < 5; i++) {
                executorPlatform.submit(() -> {
                    String threadName = Thread.currentThread().getName();
                    System.out.println("  Task running in: " + threadName);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                });
            }
            
            // Allow time for tasks to complete
            Thread.sleep(500);
        }
        
        // Reset thread count
        threadCount.set(0);
        
        // Virtual thread per task executor
        System.out.println("\nVirtual thread per task executor:");
        try (ExecutorService executorVirtual = Executors.newVirtualThreadPerTaskExecutor()) {
            
            for (int i = 0; i < 5; i++) {
                executorVirtual.submit(() -> {
                    String threadInfo = Thread.currentThread().toString();
                    System.out.println("  Task running in: " + threadInfo);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return null;
                });
            }
            
            // Allow time for tasks to complete
            Thread.sleep(500);
        }
        
        System.out.println("\nKey differences:");
        System.out.println("- newVirtualThreadPerTaskExecutor creates a new virtual thread for each task");
        System.out.println("- No thread pool limits - can scale to millions of tasks");
        System.out.println("- Automatic resource management when using try-with-resources");
        System.out.println("- Simplifies coding by removing thread pool tuning considerations");
        
        System.out.println();
    }
    
    /**
     * Demonstrate massive concurrency with virtual threads
     */
    private static void massiveConcurrency() throws Exception {
        System.out.println("5. Massive Concurrency");
        System.out.println("---------------------");
        
        // Number of concurrent tasks
        final int taskCount = 100_000;
        
        System.out.println("Running " + taskCount + " concurrent tasks with virtual threads...");
        Instant start = Instant.now();
        
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // Submit all tasks
            List<Runnable> tasks = IntStream.range(0, taskCount)
                    .mapToObj(i -> (Runnable) () -> {
                        try {
                            // Simulate I/O operation (e.g., network request)
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    })
                    .toList();
            
            // Submit and wait for all tasks
            tasks.forEach(executor::submit);
        }
        
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        
        System.out.println("All " + taskCount + " tasks completed in " + duration.toMillis() + "ms");
        System.out.println("Tasks per second: " + (taskCount * 1000.0 / duration.toMillis()));
        
        System.out.println("\nReal-world applications:");
        System.out.println("- Web servers handling many concurrent connections");
        System.out.println("- Microservices making multiple API calls");
        System.out.println("- Database connection pools");
        System.out.println("- High-throughput message processing");
        
        System.out.println();
    }
    
    /**
     * Structured concurrency (preview in Java 21)
     */
    private static void structuredConcurrency() throws Exception {
        System.out.println("6. Structured Concurrency (Preview)");
        System.out.println("--------------------------------");
        
        System.out.println("Structured Concurrency is a preview feature in Java 21 that:");
        System.out.println("- Treats multiple tasks running in different threads as a single unit of work");
        System.out.println("- Ensures all subtasks complete before the main task completes");
        System.out.println("- Propagates cancellation from parent to child tasks");
        System.out.println("- Consolidates error handling");
        System.out.println("- Uses try-with-resources with the StructuredTaskScope class");
        
        // Code example commented out because it's a preview feature requiring special JVM flags
        System.out.println("\nExample code (requires preview features to be enabled):");
        System.out.println("```java");
        System.out.println("try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {");
        System.out.println("    Future<User> user = scope.fork(() -> fetchUser(userId));");
        System.out.println("    Future<Order> order = scope.fork(() -> fetchOrder(orderId));");
        System.out.println("    ");
        System.out.println("    scope.join();           // Wait for all tasks to complete");
        System.out.println("    scope.throwIfFailed();  // Propagate errors");
        System.out.println("    ");
        System.out.println("    // Both tasks completed successfully");
        System.out.println("    processUserAndOrder(user.resultNow(), order.resultNow());");
        System.out.println("}");
        System.out.println("```");
        
        System.out.println("\nBenefits of Structured Concurrency:");
        System.out.println("- Clearer code organization with parent-child relationships");
        System.out.println("- Automatic resource cleanup");
        System.out.println("- Simplified error handling");
        System.out.println("- No thread leaks");
    }
}
