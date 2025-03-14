package com.example.java21;

import java.util.*;

/**
 * Demonstrates Sequenced Collections introduced in Java 21.
 * 
 * Sequenced Collections provide a standard way to work with collections that have
 * a defined encounter order (beginning and end), allowing operations like adding
 * elements at either end or processing elements from either direction.
 */
public class SequencedCollections {

    public static void main(String[] args) {
        introduceSequencedCollections();
        sequencedListExamples();
        sequencedSetExamples();
        sequencedMapExamples();
        realWorldUseCases();
        comparingWithLegacyCode();
    }
    
    private static void introduceSequencedCollections() {
        System.out.println("Sequenced Collections (Java 21)");
        System.out.println("------------------------------");
        
        System.out.println("Sequenced Collections introduce new interfaces for ordered collections:");
        System.out.println("- SequencedCollection: Base interface for collections with a defined encounter order");
        System.out.println("- SequencedSet: For ordered sets");
        System.out.println("- SequencedMap: For ordered maps");
        
        System.out.println("\nKey capabilities added:");
        System.out.println("- Accessing first/last elements");
        System.out.println("- Adding elements at the beginning/end");
        System.out.println("- Creating reversed views of collections");
        System.out.println("- Standardized methods across different collection types");
        
        System.out.println("\nImplementing classes include:");
        System.out.println("- List implementations (ArrayList, LinkedList)");
        System.out.println("- LinkedHashSet");
        System.out.println("- LinkedHashMap");
        System.out.println("- TreeSet");
        System.out.println("- TreeMap");
        
        System.out.println();
    }
    
    private static void sequencedListExamples() {
        System.out.println("Sequenced List Examples");
        System.out.println("----------------------");
        
        // Create a list that's also a SequencedCollection
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        
        System.out.println("Original list: " + fruits);
        
        // New methods from SequencedCollection
        
        // Get first and last elements
        String firstFruit = fruits.getFirst();
        String lastFruit = fruits.getLast();
        System.out.println("First fruit: " + firstFruit);
        System.out.println("Last fruit: " + lastFruit);
        
        // Add at the beginning and end
        fruits.addFirst("Apricot");
        fruits.addLast("Date");
        System.out.println("After adding first and last: " + fruits);
        
        // Remove from the beginning and end
        String removedFirst = fruits.removeFirst();
        String removedLast = fruits.removeLast();
        System.out.println("Removed first: " + removedFirst);
        System.out.println("Removed last: " + removedLast);
        System.out.println("After removals: " + fruits);
        
        // Get a reversed view
        List<String> reversedFruits = fruits.reversed();
        System.out.println("Reversed view: " + reversedFruits);
        
        // The reversed view is live - changes to original reflect in the reversed view
        fruits.add("Elderberry");
        System.out.println("Original after adding Elderberry: " + fruits);
        System.out.println("Reversed view after change: " + reversedFruits);
        
        System.out.println();
    }
    
    private static void sequencedSetExamples() {
        System.out.println("Sequenced Set Examples");
        System.out.println("---------------------");
        
        // LinkedHashSet maintains insertion order and implements SequencedSet
        LinkedHashSet<String> countries = new LinkedHashSet<>();
        countries.add("Canada");
        countries.add("Brazil");
        countries.add("Australia");
        
        System.out.println("Countries set: " + countries);
        
        // Access first/last elements (new in Java 21)
        String firstCountry = countries.getFirst();
        String lastCountry = countries.getLast();
        System.out.println("First country: " + firstCountry);
        System.out.println("Last country: " + lastCountry);
        
        // Add elements in specific positions
        countries.addFirst("Argentina");
        countries.addLast("Denmark");
        System.out.println("After adding first and last: " + countries);
        
        // Remove from ends
        countries.removeFirst();
        countries.removeLast();
        System.out.println("After removing first and last: " + countries);
        
        // Get a reversed view
        Set<String> reversedCountries = countries.reversed();
        System.out.println("Reversed countries: " + reversedCountries);
        
        // TreeSet also implements SequencedSet (but sorts elements)
        TreeSet<String> sortedCountries = new TreeSet<>(countries);
        System.out.println("\nSorted countries (TreeSet): " + sortedCountries);
        System.out.println("First (alphabetically): " + sortedCountries.getFirst());
        System.out.println("Last (alphabetically): " + sortedCountries.getLast());
        
        // Adding elements respects the sort order, not the position specified
        sortedCountries.addFirst("Zimbabwe"); // Will actually go to the end (alphabetically)
        sortedCountries.addLast("Albania");   // Will actually go to the beginning (alphabetically)
        System.out.println("After adding 'first' and 'last' to TreeSet: " + sortedCountries);
        
        System.out.println();
    }
    
    private static void sequencedMapExamples() {
        System.out.println("Sequenced Map Examples");
        System.out.println("---------------------");
        
        // LinkedHashMap maintains insertion order and implements SequencedMap
        LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        
        System.out.println("Original scores map: " + scores);
        
        // Access first/last entries
        Map.Entry<String, Integer> firstEntry = scores.firstEntry();
        Map.Entry<String, Integer> lastEntry = scores.lastEntry();
        System.out.println("First entry: " + firstEntry.getKey() + " = " + firstEntry.getValue());
        System.out.println("Last entry: " + lastEntry.getKey() + " = " + lastEntry.getValue());
        
        // Get first/last keys and values
        String firstKey = scores.firstKey();
        String lastKey = scores.lastKey();
        System.out.println("First key: " + firstKey);
        System.out.println("Last key: " + lastKey);
        
        // Put entries at the beginning/end
        scores.putFirst("Aaron", 88);
        scores.putLast("Zach", 91);
        System.out.println("After putting first and last: " + scores);
        
        // Remove from the beginning/end
        Map.Entry<String, Integer> removedFirst = scores.pollFirstEntry();
        Map.Entry<String, Integer> removedLast = scores.pollLastEntry();
        System.out.println("Removed first: " + removedFirst.getKey() + " = " + removedFirst.getValue());
        System.out.println("Removed last: " + removedLast.getKey() + " = " + removedLast.getValue());
        System.out.println("After removals: " + scores);
        
        // Get a reversed view
        Map<String, Integer> reversedScores = scores.reversed();
        System.out.println("Reversed view: " + reversedScores);
        
        // TreeMap also implements SequencedMap (but sorts by key)
        TreeMap<String, Integer> sortedScores = new TreeMap<>(scores);
        System.out.println("\nSorted scores (TreeMap): " + sortedScores);
        System.out.println("First entry (by key): " + sortedScores.firstEntry());
        System.out.println("Last entry (by key): " + sortedScores.lastEntry());
        
        System.out.println();
    }
    
    private static void realWorldUseCases() {
        System.out.println("Real-World Use Cases");
        System.out.println("-------------------");
        
        // 1. LRU Cache implementation with bounded LinkedHashMap
        System.out.println("1. LRU Cache Example:");
        int cacheSize = 3;
        
        Map<String, String> lruCache = new LinkedHashMap<>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };
        
        // This represents cache access - least recently used items move to the end
        lruCache.put("key1", "value1");
        lruCache.put("key2", "value2");
        lruCache.put("key3", "value3");
        System.out.println("Initial cache: " + lruCache);
        
        // Access key1 (moves to the end of access order)
        lruCache.get("key1");
        System.out.println("After accessing key1: " + lruCache);
        
        // Add key4 - should remove the least recently used item (key2)
        lruCache.put("key4", "value4");
        System.out.println("After adding key4: " + lruCache);
        
        // With SequencedMap, we can easily get the oldest entry
        Map.Entry<String, String> oldestEntry = ((LinkedHashMap<String, String>)lruCache).firstEntry();
        System.out.println("Oldest entry: " + oldestEntry.getKey() + " = " + oldestEntry.getValue());
        
        // 2. Breadcrumb navigation
        System.out.println("\n2. Breadcrumb Navigation Example:");
        Deque<String> breadcrumbs = new ArrayDeque<>();
        
        // User navigates through pages
        breadcrumbs.addLast("Home");
        breadcrumbs.addLast("Products");
        breadcrumbs.addLast("Electronics");
        breadcrumbs.addLast("Smartphones");
        
        System.out.println("Current path: " + String.join(" > ", breadcrumbs));
        
        // User clicks back button twice
        breadcrumbs.removeLast();
        breadcrumbs.removeLast();
        
        System.out.println("After clicking back twice: " + String.join(" > ", breadcrumbs));
        
        // User navigates to a different section
        breadcrumbs.addLast("Computers");
        System.out.println("After navigating to new section: " + String.join(" > ", breadcrumbs));
        
        // 3. Order processing with priority
        System.out.println("\n3. Order Processing Queue:");
        
        // Order record with priority
        record Order(String id, String customer, int priority) {}
        
        // Use TreeSet with custom comparator for priority queue
        TreeSet<Order> orderQueue = new TreeSet<>(
                Comparator.comparingInt(Order::priority).reversed()
                        .thenComparing(Order::id));
        
        orderQueue.add(new Order("A123", "Alice", 2));
        orderQueue.add(new Order("B456", "Bob", 1));
        orderQueue.add(new Order("C789", "Charlie", 3));
        orderQueue.add(new Order("D012", "David", 2));
        
        System.out.println("Order processing queue:");
        orderQueue.forEach(order -> 
            System.out.println("  " + order.id() + " - " + order.customer() + 
                            " (Priority: " + order.priority() + ")"));
        
        // Process highest priority order first
        Order nextOrder = orderQueue.getFirst(); // Highest priority
        System.out.println("\nProcessing next order: " + nextOrder.id() + " - " + nextOrder.customer());
        
        System.out.println();
    }
    
    private static void comparingWithLegacyCode() {
        System.out.println("Comparing with Legacy Code");
        System.out.println("-------------------------");
        
        System.out.println("Before Java 21:");
        System.out.println("""
        // List operations - different methods for different collections
        List<String> list = new ArrayList<>();
        String first = list.isEmpty() ? null : list.get(0);
        String last = list.isEmpty() ? null : list.get(list.size() - 1);
        
        list.add(0, "newFirst");  // Add at beginning
        list.add("newLast");      // Add at end
        
        // LinkedList had specialized methods
        LinkedList<String> linkedList = new LinkedList<>();
        String firstElement = linkedList.getFirst();
        String lastElement = linkedList.getLast();
        
        // Deque had similar but different method names
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("first");
        deque.addLast("last");
        
        // No standard way to reverse collections
        List<String> reversed = new ArrayList<>(list);
        Collections.reverse(reversed);
        
        // TreeSet/TreeMap had some methods but not others
        TreeSet<String> treeSet = new TreeSet<>();
        String firstItem = treeSet.first();
        String lastItem = treeSet.last();
        
        // Navigable interfaces added more methods
        NavigableSet<String> navigableSet = new TreeSet<>();
        navigableSet.descendingSet(); // Creates a reversed view
        """);
        
        System.out.println("\nAfter Java 21 (standardized methods):");
        System.out.println("""
        // Works with ArrayList, LinkedList, etc.
        List<String> list = new ArrayList<>();
        String first = list.getFirst();
        String last = list.getLast();
        
        list.addFirst("newFirst");
        list.addLast("newLast");
        
        // Same methods work on sets
        LinkedHashSet<String> set = new LinkedHashSet<>();
        String firstElement = set.getFirst();
        String lastElement = set.getLast();
        
        // Works with maps too
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        Map.Entry<String, Integer> firstEntry = map.firstEntry();
        Map.Entry<String, Integer> lastEntry = map.lastEntry();
        
        // Uniform way to get reversed views
        List<String> reversedList = list.reversed();
        Set<String> reversedSet = set.reversed();
        Map<String, Integer> reversedMap = map.reversed();
        """);
        
        System.out.println("\nBenefits of the new approach:");
        System.out.println("- Method consistency across different collection types");
        System.out.println("- More intuitive API (direct methods vs. index calculations)");
        System.out.println("- Safer operations (no IndexOutOfBoundsException)");
        System.out.println("- Reverse views without copying or external utilities");
        System.out.println("- Better expressiveness leads to more readable code");
    }
}
