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
- **Removal of Java EE/CORBA Modules**: Streamlined the JDK by removing outdated APIs.

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
- **Removal of Nashorn**: Phased out the Nashorn JavaScript engine.

### Java 16 (2021)
- **Records (Standard)**: Final version of records.
- **Pattern Matching for instanceof (Standard)**: Final version of pattern matching.
- **Jpackage Tool**: Packaging of self-contained Java applications.
- **Foreign Linker API (Incubator)**: Early version of Foreign Function & Memory API.

### Java 17 (2021, LTS)
- **Sealed Classes (Standard)**: Final version of sealed classes.
- **Enhanced Pseudo-Random Number Generators**: New interfaces for random number generation.
- **Foreign Function & Memory API (Incubator)**: Native code interoperation.
- **Deprecation of the Security Manager**: Phase-out in favor of modern security.
- **Pattern Matching for switch (Preview)**: Early version of switch pattern matching.

### Java 18 (2022)
- **Simple Web Server (Incubator)**: Minimal HTTP server for testing.
- **UTF-8 as the Default Charset**: Consistency across platforms.
- **Code Snippets in JavaDoc**: Better documentation with example code.
- **Vector API (Incubator)**: SIMD vector computations.

### Java 19 (2022)
- **Virtual Threads (Preview)**: Lightweight concurrency model.
- **Structured Concurrency (Preview)**: Managing multiple concurrent tasks.
- **Record Patterns (Preview)**: Pattern matching for records.
- **Enhanced Pattern Matching for switch (Preview)**: Advanced switch capabilities.
- **Foreign Function & Memory API (Preview)**: Continued development.

### Java 20 (2023)
- **Virtual Threads (Second Preview)**: Refinements to lightweight threads.
- **Scoped Values (Preview)**: Sharing immutable data between threads.
- **Record Patterns & Pattern Matching Enhancements**: More pattern matching features.
- **Structured Concurrency (Second Preview)**: Advanced concurrent task management.

### Java 21 (2023, LTS)
- **Virtual Threads (Standard)**: Production-ready virtual threads.
- **Structured Concurrency (Preview)**: Streamlined concurrent task management.
- **Foreign Function & Memory API (Preview)**: Safer native code interoperation.
- **Pattern Matching for switch (Standard)**: Final version of switch pattern matching.
- **Sequenced Collections**: New interfaces for ordered collections.
- **String Templates (Preview)**: Enhanced string formatting.
- **Record Patterns (Standard)**: Final version of record patterns.
- **Performance & API Refinements**: Garbage collection and runtime efficiency.

## Repository Structure

This repository is organized with examples for each Java feature:

```
src/main/java/com/example/
├── java8/      # Java 8 features
├── java9/      # Java 9 features
├── java10/     # Java 10 features
├── java11/     # Java 11 features
├── java12/     # Java 12 features
├── java13/     # Java 13 features
├── java14/     # Java 14 features
├── java15/     # Java 15 features
├── java16/     # Java 16 features
├── java17/     # Java 17 features
├── java18/     # Java 18 features
├── java19/     # Java 19 features
├── java20/     # Java 20 features
└── java21/     # Java 21 features
```

Each example includes detailed comments and demonstrates practical use cases for the features.

## Highlighted Examples

- **Lambda and Streams**: Functional programming with collections
- **Records**: Concise immutable data classes
- **Pattern Matching**: Type checks and data extraction
- **Sealed Classes**: Controlled inheritance hierarchies
- **Virtual Threads**: High-throughput concurrent programming
- **Text Blocks**: Multi-line string handling
- **Enhanced Random Numbers**: Improved random number generation
- **Foreign Function API**: Native code interoperation

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

Run specific examples:
```bash
# For example, to run the Virtual Threads example
java -cp target/demo-java8tojava21-1.0-SNAPSHOT.jar com.example.java21.VirtualThreads

# To run with preview features (if needed for some examples)
java --enable-preview -cp target/demo-java8tojava21-1.0-SNAPSHOT.jar com.example.java21.PatternMatchingForSwitch
```

## Contributing

Feel free to contribute additional examples or improvements to existing ones. Pull requests are welcome!

## License

This project is available under the MIT License - encouraging learning and experimentation with Java features.
