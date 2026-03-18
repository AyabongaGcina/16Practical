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
         