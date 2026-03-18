//Consulted Deepseek for File reading and timing logic
import java.io.BufferedReader;
import java.io.FileReader; 
import java.io.IOException; 
import java.util.ArrayList;
import java.util.List;

public class TryHeapsort {
    public static void main(String[] args) {
        String inputFilePath = "cleaned_ulysses.txt";
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
        for (String currentWord : topDownInput) {
            topDownHeap.insert(currentWord);
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
        for (int index = 0; index < Math.min(20, bottomUpSortedResult.length); index++) {
            System.out.println((index + 1) + ": " + bottomUpSortedResult[index]);
        }

           // Check if sorts match
        boolean resultsMatch = true;
        for (int index = 0; index < bottomUpSortedResult.length; index++) {
            if (!bottomUpSortedResult[index].equals(topDownSortedResult[index])) {
                resultsMatch = false;
                System.out.println("Mismatch at index " + index + 
                    ": " + bottomUpSortedResult[index] + " vs " + topDownSortedResult[index]);
                break;
            }
        }