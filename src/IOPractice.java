import java.io.*;
import java.util.*;
import java.nio.file.*;

public class IOPractice {
    public static void main(String[] args) {
        String inputFile1 = "input1.txt";
        String inputFile2 = "input2.txt";
        String mergedOutputFile = "merged.txt";
        String commonOutputFile = "common.txt";

        // Print the current working directory for debugging
        System.out.println("Current working directory: " + Paths.get("").toAbsolutePath());

        try {
            // Read integers from input files
            List<Integer> list1 = readIntegersFromFile(inputFile1);
            List<Integer> list2 = readIntegersFromFile(inputFile2);

            // Merge the lists while maintaining the order
            List<Integer> mergedList = new ArrayList<>(list1);
            mergedList.addAll(list2);

            // Write merged list to output file
            writeIntegersToFile(mergedOutputFile, mergedList);

            // Find common integers
            Set<Integer> commonIntegers = new HashSet<>(list1);
            commonIntegers.retainAll(list2);

            // Write common integers to output file
            writeIntegersToFile(commonOutputFile, new ArrayList<>(commonIntegers));

            System.out.println("Files processed successfully.");

        } catch (FileNotFoundException e) {
            System.err.println("One of the input files was not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("An IO error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in one of the files: " + e.getMessage());
        }
    }

    private static List<Integer> readIntegersFromFile(String filename) throws IOException, NumberFormatException {
        List<Integer> integers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                integers.add(Integer.parseInt(line.trim()));
            }
        }
        return integers;
    }

    private static void writeIntegersToFile(String filename, List<Integer> integers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
            for (Integer integer : integers) {
                writer.write(integer.toString());
                writer.newLine();
            }
        }
    }
}
