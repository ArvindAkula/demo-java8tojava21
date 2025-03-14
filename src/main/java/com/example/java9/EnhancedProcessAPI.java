package com.example.java9;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Demonstrates the Enhanced Process API introduced in Java 9.
 * 
 * Java 9 improves the Process API to make it easier to interact with and manage
 * operating system processes.
 */
public class EnhancedProcessAPI {

    public static void main(String[] args) {
        System.out.println("Enhanced Process API (Java 9)");
        System.out.println("-----------------------------");
        
        try {
            // Get current process information
            demonstrateCurrentProcess();
            
            // Process builder improvements
            demonstrateProcessBuilderImprovements();
            
            // Process handle for better management
            demonstrateProcessHandle();
            
            // Stream of all system processes
            demonstrateAllProcesses();
            
            // Child processes
            demonstrateChildProcesses();
            
            // Process completion
            demonstrateProcessCompletion();
            
        } catch (IOException | InterruptedException e) {
            System.err.println("Error demonstrating Process API: " + e.getMessage());
        }
    }
    
    private static void demonstrateCurrentProcess() {
        System.out.println("\n1. Current Process Information");
        System.out.println("---------------------------");
        
        ProcessHandle current = ProcessHandle.current();
        System.out.println("Current PID: " + current.pid());
        
        Optional<ProcessHandle.Info> info = current.info();
        info.ifPresent(i -> {
            System.out.println("Process info:");
            i.command().ifPresent(cmd -> System.out.println("  Command: " + cmd));
            i.commandLine().ifPresent(cmdLine -> System.out.println("  Command line: " + cmdLine));
            i.startInstant().ifPresent(start -> System.out.println("  Start time: " + start));
            i.totalCpuDuration().ifPresent(cpu -> System.out.println("  CPU time: " + cpu));
            i.user().ifPresent(user -> System.out.println("  User: " + user));
        });
    }
    
    private static void demonstrateProcessBuilderImprovements() throws IOException, InterruptedException {
        System.out.println("\n2. ProcessBuilder Improvements");
        System.out.println("-----------------------------");
        
        // Create a process using ProcessBuilder
        ProcessBuilder builder = new ProcessBuilder();
        
        // Set the command based on the OS
        boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
        if (isWindows) {
            builder.command("cmd.exe", "/c", "dir");
        } else {
            builder.command("sh", "-c", "ls -l");
        }
        
        System.out.println("Starting process: " + String.join(" ", builder.command()));
        
        // Start the process and get input stream
        Process process = builder.start();
        
        // Wait for the process to complete with timeout
        boolean completed = process.waitFor(5, TimeUnit.SECONDS);
        System.out.println("Process completed within timeout: " + completed);
        
        // Get the exit value
        int exitValue = process.exitValue();
        System.out.println("Exit value: " + exitValue);
    }
    
    private static void demonstrateProcessHandle() throws IOException {
        System.out.println("\n3. Process Handle");
        System.out.println("----------------");
        
        // Start a new process
        ProcessBuilder builder = new ProcessBuilder();
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            builder.command("cmd.exe", "/c", "ping -n 10 127.0.0.1");
        } else {
            builder.command("sh", "-c", "sleep 10");
        }
        
        System.out.println("Starting long-running process...");
        Process process = builder.start();
        
        // Get the process handle
        ProcessHandle handle = process.toHandle();
        System.out.println("Process PID: " + handle.pid());
        
        // Check if the process is alive
        System.out.println("Is process alive? " + handle.isAlive());
        
        // Try to terminate the process
        boolean terminated = handle.destroy();
        System.out.println("Was process terminated? " + terminated);
        
        // Force termination if normal termination failed
        if (!terminated) {
            terminated = handle.destroyForcibly();
            System.out.println("Was process force terminated? " + terminated);
        }
    }
    
    private static void demonstrateAllProcesses() {
        System.out.println("\n4. Stream of All Processes");
        System.out.println("-------------------------");
        
        // Get all processes as a stream
        Stream<ProcessHandle> allProcesses = ProcessHandle.allProcesses();
        
        // Count the number of processes
        long processCount = allProcesses.count();
        System.out.println("Total number of processes: " + processCount);
        
        // Process filtering example
        System.out.println("\nProcesses running for more than 1 hour:");
        Instant oneHourAgo = Instant.now().minus(Duration.ofHours(1));
        
        ProcessHandle.allProcesses()
                .filter(ph -> ph.info().startInstant().orElse(Instant.now()).isBefore(oneHourAgo))
                .limit(5) // Limit to 5 examples
                .forEach(process -> {
                    ProcessHandle.Info info = process.info();
                    System.out.printf("PID: %d, Command: %s, Start time: %s%n",
                            process.pid(),
                            info.command().orElse("Unknown"),
                            info.startInstant().orElse(null));
                });
    }
    
    private static void demonstrateChildProcesses() throws IOException {
        System.out.println("\n5. Child Processes");
        System.out.println("----------------");
        
        // Start parent process
        ProcessBuilder parentBuilder = new ProcessBuilder();
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            parentBuilder.command("cmd.exe", "/c", "start cmd.exe /c ping -n 5 127.0.0.1");
        } else {
            parentBuilder.command("sh", "-c", "sleep 5 & echo $!");
        }
        
        System.out.println("Starting parent process...");
        Process parent = parentBuilder.start();
        
        // Get the children of the current process
        ProcessHandle current = ProcessHandle.current();
        System.out.println("Children of the current Java process:");
        current.children().forEach(child -> {
            System.out.println("Child PID: " + child.pid());
            child.info().command().ifPresent(cmd -> System.out.println("  Command: " + cmd));
        });
        
        // Descendants include children and their children
        System.out.println("\nDescendants of the current Java process:");
        current.descendants().limit(5).forEach(desc -> {
            System.out.println("Descendant PID: " + desc.pid());
            desc.info().command().ifPresent(cmd -> System.out.println("  Command: " + cmd));
        });
    }
    
    private static void demonstrateProcessCompletion() throws IOException {
        System.out.println("\n6. Process Completion");
        System.out.println("--------------------");
        
        // Start a process
        ProcessBuilder builder = new ProcessBuilder();
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            builder.command("cmd.exe", "/c", "ping -n 3 127.0.0.1");
        } else {
            builder.command("sh", "-c", "sleep 3");
        }
        
        System.out.println("Starting process...");
        Process process = builder.start();
        
        // Get a CompletableFuture that completes when the process exits
        CompletableFuture<Process> completion = process.onExit();
        
        // Add a callback to be executed when the process completes
        completion.thenAccept(p -> {
            System.out.println("Process completed with exit code: " + p.exitValue());
        });
        
        System.out.println("Waiting for process to complete...");
        try {
            // Just to keep the main thread alive until the process completes
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
