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
            