//Ayabonga Gcina
//4446494
//Practical 6

import java.io.*;

public class ulysess_cleaned {
    public static void main(String[] args) {
        String sourceFilePath = "ulysses.txt";
        String destinationFilePath = "cleaned_ulysses.txt";
        
        int totalCleanWords = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(destinationFilePath))) {
             String currentLine;
             while ((currentLine = reader.readLine()) != null) {
                String[] extractedWords = currentLine.split("\\s+");
                  for (String rawWord : extractedWords) {
                    String processedWord = sanitizeWord(rawWord);
                    
                    if (!processedWord.isEmpty()) {
                        writer.println(processedWord);
                        totalCleanWords++;
                    }
                }
            }
            System.out.println("Cleaned " + totalCleanWords + " words from " + sourceFilePath);
            System.out.println("Output written to: " + destinationFilePath);
         } catch (IOException ioException) {
            System.err.println("Error: " + ioException.getMessage());
        }
    } 
    public static String sanitizeWord(String inputWord) {
        return inputWord.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }
}  