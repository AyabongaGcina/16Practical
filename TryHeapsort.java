// Ayabonga Gcina
// 4446494
// Practical 6 - Heapsort timing comparison
// Consulted DeepSeek for File reading and timing logic

import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

public class TryHeapsort {

    public static void main(String[] args) {
        String inputFilePath = "cleaned_ulysses.txt"; // Correct filename

        // Read words from file
        String[] allWords = readWordsFromFile(inputFilePath);
        if (allWords.length == 0) {
            System.err.println("No words found in file. Exiting.");
            return;
        }
        System.out.println("Total words to sort: " + allWords.length);

        // Make copies for each method
        String[] bottomUpInput = allWords.clone();
        String[] topDownInput = allWords.clone();

        // ---------- Bottom-up timing ----------
        long startTimeNano = System.nanoTime();
        
        Heap bottomUpHeap = new Heap(bottomUpInput.length);
        bottomUpHeap.buildUp(bottomUpInput);
        String[] bottomUpSortedResult = bottomUpHeap.sort();
        
        long endTimeNano = System.nanoTime();
        long bottomUpDuration = endTimeNano - startTimeNano;
        
        // ---------- Top-down timing ----------
        startTimeNano = System.nanoTime();
        
        Heap topDownHeap = new Heap(topDownInput.length);
        for (String word : topDownInput) {
            topDownHeap.insert(word);
        }
        String[] topDownSortedResult = topDownHeap.sort();
        
        endTimeNano = System.nanoTime();
        long topDownDuration = endTimeNano - startTimeNano;
        
        // ---------- Display results ----------
        System.out.println("\n=== TIMING RESULTS ===");
        System.out.printf("Bottom-up build + sort: %.3f ms%n", bottomUpDuration / 1_000_000.0);
        System.out.printf("Top-down build + sort:   %.3f ms%n", topDownDuration / 1_000_000.0);
        
        // Verify first few words are sorted
        System.out.println("\n=== VERIFICATION (first 20 sorted words) ===");
        System.out.println("Bottom-up sort result:");
        for (int i = 0; i < Math.min(20, bottomUpSortedResult.length); i++) {
            System.out.println((i + 1) + ": " + bottomUpSortedResult[i]);
        }

        // Check if both sorts match
        boolean resultsMatch = true;
        for (int i = 0; i < bottomUpSortedResult.length; i++) {
            if (!bottomUpSortedResult[i].equals(topDownSortedResult[i])) {
                resultsMatch = false;
                System.out.println("Mismatch at index " + i + 
                    ": " + bottomUpSortedResult[i] + " vs " + topDownSortedResult[i]);
                break;
            }
        }
        System.out.println("\nBoth sorts produced " + 
            (resultsMatch ? "IDENTICAL" : "DIFFERENT") + " results.");
    }
    
    // ---------- Helper method to read words ----------
    private static String[] readWordsFromFile(String filePath) {
        List<String> words = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    words.add(line);
                }
            }
            System.out.println("Successfully read " + words.size() + " words from " + filePath);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        
        return words.toArray(new String[0]);
    }
}