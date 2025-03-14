# Java 8 to Java 21 Features Demo

This repository demonstrates the key features introduced in Java versions 8 through 21. Each feature has a dedicated example showcasing its usage and benefits.

## Features by Java Version

### Java 8 (2014)
- **Lambda Expressions & Functional Interfaces**: First-class support for functional programming.
- **Streams API**: Functional-style operations on collections.
- **Default Methods in Interfaces**: Method implementations in interfaces.
- **New Date/Time API**: Modern date and time handling (java.time package).
- **Optional Class**: Better null value handling.
- **Nashorn JavaScript Engine**: Lightweight JavaScript runtime.

### Java 9 (2017)
- **Module System (Project Jigsaw)**: Modularized JDK for better encapsulation.
- **JShell (REPL)**: Interactive tool for experimenting with Java code.
- **Enhanced Process API**: Simplified process handling.
- **Private Methods in Interfaces**: Private helper methods in interfaces.

### Java 10 (2018)
- **Local-Variable Type Inference (var)**: Type inference for local variables.
- **Application Class-Data Sharing**: Improved startup times and memory footprint.
- **Container Awareness Improvements**: Better performance in containerized environments.

### Java 11 (2018, LTS)
- **HTTP Client API**: Standard HTTP/2 and WebSocket communications.
- **New String Methods**: Utility methods (isBlank(), lines(), strip(), repeat()).
- **Single-File Source-Code Programs**: Running Java programs without explicit compilation.

### Java 12 (2019)
- **Switch Expressions (Preview)**: More concise switch statements.
- **Shenandoah GC (Experimental)**: Low-pause-time garbage collector.
- **JVM Constants API**: Better modeling of nominal descriptors for constants.

### Java 13 (2019)
- **Text Blocks (Preview)**: Multi-line string literals.
- **Enhanced Switch Expressions (Continued Preview)**: Refined switch expressions.
- **Dynamic CDS Archives**: Improved Class-Data Sharing.

### Java 14 (2020)
- **Records (Preview)**: Compact classes for data carriers.
- **Pattern Matching for instanceof (Preview)**: Simplified type checking.
- **Helpful NullPointerExceptions**: Enhanced exception messages.
- **Continued Switch Expressions**: Further evolution of switch expressions.

### Java 15 (2020)
- **Sealed Classes (Preview)**: Controlled inheritance.
- **Hidden Classes**: Classes not accessible via standard class-loading.
- **Text Blocks (Standard)**: Final version of text blocks.

### Java 16 (2021)
- **Records (Standard)**: Final version of records.
- **Pattern Matching for instanceof (Standard)**: Final version of pattern matching.
- **Jpackage Tool**: Packaging of self-contained Java applications.

### Java 17 (2021, LTS)
- **Sealed Classes (Standard)**: Final version of sealed classes.
- **Enhanced Pseudo-Random Number Generators**: New interfaces for random number generation.
- **Deprecation of the Security Manager**: Phase-out in favor of modern security.
- **Foreign Function & Memory API (Incubator)**: Native code interoperation.

### Java 18 (2022)
- **Simple Web Server (Incubator)**: Minimal HTTP server for testing.
- **UTF-8 as the Default Charset**: Consistency across platforms.
- **Various Performance and API Enhancements**: Under-the-hood improvements.

### Java 19 (2022)
- **Virtual Threads (Preview)**: Lightweight concurrency model.
- **Structured Concurrency (Preview)**: Managing multiple concurrent tasks.
- **Record Patterns (Preview)**: Pattern matching for records.
- **Enhanced Pattern Matching for switch (Preview)**: Advanced switch capabilities.

### Java 20 (2023)
- **Virtual Threads (Continued Preview)**: Refinements to lightweight threads.
- **Scoped Values (Preview)**: Sharing immutable data between threads.
- **Record Patterns & Pattern Matching Enhancements**: More pattern matching features.
- **Structured Concurrency (Continued Preview)**: Advanced concurrent task management.

### Java 21 (2023, LTS)
- **Virtual Threads (Standard)**: Production-ready virtual threads.
- **Structured Concurrency (Standard/Enhanced)**: Streamlined concurrent task management.
- **Foreign Function & Memory API Enhancements**: Safer native code interoperation.
- **Pattern Matching for switch**: Continued improvements in pattern matching.
- **Performance & API Refinements**: Garbage collection and runtime efficiency.

## Getting Started

### Prerequisites
- Java 21 JDK or later
- Maven 3.8 or later

### Building and Running

Clone the repository:
```bash
git clone https://github.com/ArvindAkula/demo-java8tojava21.git
cd demo-java8tojava21
```

Build the project:
```bash
mvn clean package
```

Run the examples:
```bash
java -jar target/demo-java8tojava21-1.0-SNAPSHOT.jar
```
