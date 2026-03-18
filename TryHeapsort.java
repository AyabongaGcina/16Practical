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