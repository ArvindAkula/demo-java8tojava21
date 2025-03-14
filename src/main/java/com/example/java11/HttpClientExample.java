package com.example.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Demonstrates the standardized HTTP Client API introduced in Java 11.
 * 
 * This API supports both synchronous and asynchronous programming models,
 * HTTP/2, WebSockets, and replaces the older HttpURLConnection.
 */
public class HttpClientExample {

    public static void main(String[] args) {
        try {
            // Basic GET request
            synchronousGet();
            
            // Asynchronous GET request
            asynchronousGet();
            
            // POST request with body
            postRequest();
            
            // Multiple asynchronous requests
            multipleAsyncRequests();
            
            // Configure timeout, version, etc.
            configuredClient();
            
            // HTTP/2 specific features
            http2Example();
            
            // Download file example
            downloadFile();
            
        } catch (Exception e) {
            System.err.println("Error in HTTP Client example: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Basic synchronous GET request
     */
    private static void synchronousGet() throws IOException, InterruptedException {
        System.out.println("1. Basic Synchronous GET Request");
        System.out.println("-------------------------------");
        
        // Create a client
        HttpClient client = HttpClient.newHttpClient();
        
        // Create a request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET() // GET is default, so this line is optional
                .build();
        
        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // Print response details
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Headers: " + response.headers());
        System.out.println("Body: " + response.body());
        System.out.println();
    }
    
    /**
     * Asynchronous GET request
     */
    private static void asynchronousGet() throws ExecutionException, InterruptedException {
        System.out.println("2. Asynchronous GET Request");
        System.out.println("--------------------------");
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get?param=async"))
                .header("Accept", "application/json")
                .build();
        
        // Send the request asynchronously
        CompletableFuture<HttpResponse<String>> futureResponse = 
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        
        // Process the response when it completes
        CompletableFuture<String> futureBody = futureResponse.thenApply(response -> {
            System.out.println("Got response with status code: " + response.statusCode());
            return response.body();
        });
        
        // Get the body from the CompletableFuture
        String body = futureBody.get();
        System.out.println("Response body: " + body);
        System.out.println();
    }
    
    /**
     * POST request with a JSON body
     */
    private static void postRequest() throws IOException, InterruptedException {
        System.out.println("3. POST Request with JSON Body");
        System.out.println("----------------------------");
        
        HttpClient client = HttpClient.newHttpClient();
        
        String jsonBody = "{\"name\":\"John\", \"age\":30}";
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
        System.out.println();
    }
    
    /**
     * Send multiple asynchronous requests
     */
    private static void multipleAsyncRequests() throws ExecutionException, InterruptedException {
        System.out.println("4. Multiple Asynchronous Requests");
        System.out.println("--------------------------------");
        
        HttpClient client = HttpClient.newHttpClient();
        
        // Create a list of URIs to fetch
        List<URI> uris = List.of(
                URI.create("https://httpbin.org/get?param=1"),
                URI.create("https://httpbin.org/get?param=2"),
                URI.create("https://httpbin.org/get?param=3")
        );
        
        // Create a list of requests
        List<HttpRequest> requests = uris.stream()
                .map(uri -> HttpRequest.newBuilder(uri).build())
                .collect(Collectors.toList());
        
        // Send all requests asynchronously
        List<CompletableFuture<HttpResponse<String>>> futures = requests.stream()
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());
        
        // Combine all futures into a single future that completes when all responses are received
        CompletableFuture<List<HttpResponse<String>>> combinedFuture = 
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()));
        
        // Process all responses
        List<HttpResponse<String>> responses = combinedFuture.get();
        
        for (int i = 0; i < responses.size(); i++) {
            HttpResponse<String> response = responses.get(i);
            System.out.println("Response " + (i + 1) + " status: " + response.statusCode());
            System.out.println("Response " + (i + 1) + " body: " + response.body());
        }
        System.out.println();
    }
    
    /**
     * HTTP client with custom configuration
     */
    private static void configuredClient() throws IOException, InterruptedException {
        System.out.println("5. Configured HTTP Client");
        System.out.println("------------------------");
        
        // Create a client with custom configuration
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // Prefer HTTP/2
                .followRedirects(HttpClient.Redirect.NORMAL) // Follow redirects
                .connectTimeout(Duration.ofSeconds(10)) // Connection timeout
                .build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .timeout(Duration.ofSeconds(30)) // Request timeout
                .header("User-Agent", "Java 11 HttpClient Demo")
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("Protocol version: " + response.version());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
        System.out.println();
    }
    
    /**
     * HTTP/2 specific features
     */
    private static void http2Example() throws IOException, InterruptedException {
        System.out.println("6. HTTP/2 Features");
        System.out.println("------------------");
        
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // Use HTTP/2
                .build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://nghttp2.org/httpbin/get")) // This server supports HTTP/2
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("Protocol used: " + response.version());
        System.out.println("Status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
        System.out.println();
    }
    
    /**
     * Download a file using HTTP client
     */
    private static void downloadFile() throws IOException, InterruptedException {
        System.out.println("7. Download File Example");
        System.out.println("-----------------------");
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/image/jpeg")) // Sample image URL
                .build();
        
        // Prepare the output file path (this example uses a temp file)
        Path outputPath = Files.createTempFile("downloaded-", ".jpg");
        
        // Send the request and save the response to the file
        HttpResponse<Path> response = client.send(
                request, HttpResponse.BodyHandlers.ofFile(outputPath));
        
        System.out.println("File downloaded to: " + outputPath);
        System.out.println("File size: " + Files.size(outputPath) + " bytes");
        System.out.println("Status code: " + response.statusCode());
        System.out.println();
    }
}
