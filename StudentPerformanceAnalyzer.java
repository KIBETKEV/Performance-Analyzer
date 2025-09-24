package m2b;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * STUDENT PERFORMANCE ANALYZER
 * Creative Version Assignment
 * Author: Kelvin Ngeno
 * Course: AIT 502
 * Date: 2025-09-20
 *
 * Reads student names and their scores in two subjects from a text file.
 * Saves data into arrays and computes aggregates for both strings and numbers.
 * Displays results in a professional table format.
 */
public class StudentPerformanceAnalyzer {
    public static void main(String[] args) {
        String today = LocalDate.now().toString();
        System.out.println("STUDENT PERFORMANCE ANALYZER by Kelvin Ngeno on " + today);
        System.out.println();

        File file = new File("students.txt");

        try (Scanner scanner = new Scanner(file)) {
            // Temporary storage (20 students max, can adjust as needed)
            String[] names = new String[50];
            int[] math = new int[50];
            int[] science = new int[50];
            int count = 0;

            // Read until ###END###
            while (scanner.hasNext()) {
                String name = scanner.next();
                if (name.equals("###END###")) break;
                int mathScore = scanner.nextInt();
                int scienceScore = scanner.nextInt();

                names[count] = name;
                math[count] = mathScore;
                science[count] = scienceScore;
                count++;
            }

            // Print table
            System.out.printf("%-12s %-8s %-8s%n", "Name", "Math", "Science");
            System.out.println("---------------------------------");
            for (int i = 0; i < count; i++) {
                System.out.printf("%-12s %-8d %-8d%n", names[i], math[i], science[i]);
            }
            System.out.println("---------------------------------");

            // String aggregates
            String longestName = "";
            int vowelCount = 0;
            for (int i = 0; i < count; i++) {
                if (names[i].length() > longestName.length()) {
                    longestName = names[i];
                }
                char firstChar = Character.toLowerCase(names[i].charAt(0));
                if (firstChar == 'a' || firstChar == 'e' || firstChar == 'i' ||
                    firstChar == 'o' || firstChar == 'u') {
                    vowelCount++;
                }
            }

            // Number aggregates
            int minMath = math[0], maxMath = math[0], sumMath = 0;
            int minSci = science[0], maxSci = science[0], sumSci = 0;

            for (int i = 0; i < count; i++) {
                minMath = Math.min(minMath, math[i]);
                maxMath = Math.max(maxMath, math[i]);
                sumMath += math[i];

                minSci = Math.min(minSci, science[i]);
                maxSci = Math.max(maxSci, science[i]);
                sumSci += science[i];
            }

            double avgMath = (double) sumMath / count;
            double avgSci = (double) sumSci / count;
            double overallAvg = (sumMath + sumSci) / (2.0 * count);

            // Print results
            System.out.println("\nString Aggregates:");
            System.out.println("- Longest name: " + longestName);
            System.out.println("- Names starting with vowels: " + vowelCount);

            System.out.println("\nNumber Aggregates:");
            System.out.printf("- Math scores → Min: %d, Max: %d, Avg: %.1f%n", minMath, maxMath, avgMath);
            System.out.printf("- Science scores → Min: %d, Max: %d, Avg: %.1f%n", minSci, maxSci, avgSci);
            System.out.printf("- Overall average score: %.1f%n", overallAvg);

        } catch (FileNotFoundException e) {
            System.out.println("Error: students.txt not found.");
        }
    }
}