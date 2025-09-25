
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * PROGRAM: StudentMatrixAnalyzer
 * Reads student names and grades from a file,
 * stores them in arrays, computes aggregates,
 * and prints formatted tables.
 */
public class StudentMatrixAnalyzer {
    public static void main(String[] args) {
        System.out.println("STUDENTMATRIXANALYZER by Kelvin Ngeno on 09/24/2025\n");

        // --- Step 1: Read file
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File("grades.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: grades.txt not found.");
            return;
        }

        // --- Step 2: Read number of students and assignments
        int numberOfStudents = fileScanner.nextInt();
        int numberOfAssignments = fileScanner.nextInt();
        fileScanner.nextLine(); // move to next line

        // --- Step 3: Arrays for names and grades
        String[] names = new String[numberOfStudents];
        int[][] grades = new int[numberOfStudents][numberOfAssignments];

        // --- Step 4: Read names + grades into arrays
        for (int i = 0; i < numberOfStudents; i++) {
            names[i] = fileScanner.next();
            for (int j = 0; j < numberOfAssignments; j++) {
                grades[i][j] = fileScanner.nextInt();
            }
        }
        fileScanner.close();

        // --- Step 5: Row-level aggregates
        int[] rowSums = new int[numberOfStudents];
        double[] rowAverages = new double[numberOfStudents];
        int[] rowMax = new int[numberOfStudents];

        // --- Step 6: Matrix-level aggregates
        int totalSum = 0;
        int highestGrade = Integer.MIN_VALUE;
        int totalCount = 0;

        for (int i = 0; i < numberOfStudents; i++) {
            int sum = 0;
            int max = Integer.MIN_VALUE;

            for (int j = 0; j < numberOfAssignments; j++) {
                int grade = grades[i][j];
                sum += grade;
                totalSum += grade;
                totalCount++;
                if (grade > max) max = grade;
                if (grade > highestGrade) highestGrade = grade;
            }

            rowSums[i] = sum;
            rowAverages[i] = (double) sum / numberOfAssignments;
            rowMax[i] = max;
        }

        double overallAverage = (double) totalSum / totalCount;

        // --- Step 7: Print Input Table
        System.out.println("Input Data:");
        System.out.print("| Student  |");
        for (int j = 1; j <= numberOfAssignments; j++) {
            System.out.printf("  G%-2d |", j);
        }
        System.out.println();
        System.out.println("|----------|" + "-----|".repeat(numberOfAssignments));

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.printf("| %-8s |", names[i]);
            for (int j = 0; j < numberOfAssignments; j++) {
                System.out.printf(" %3d |", grades[i][j]);
            }
            System.out.println();
        }

        // --- Step 8: Print Row-Level Computations
        System.out.println("\nRow-Level Computations:");
        System.out.println("| Student  |  Sum | Average |  Max |");
        System.out.println("|----------|------|---------|------|");
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.printf("| %-8s | %4d | %7.2f | %4d |\n",
                    names[i], rowSums[i], rowAverages[i], rowMax[i]);
        }

        // --- Step 9: Print Matrix-Level Aggregates
        System.out.println("\nMatrix-Level Aggregates:");
        System.out.println("Total Sum of Grades = " + totalSum);
        System.out.printf("Overall Average = %.2f\n", overallAverage);
        System.out.println("Highest Grade in Matrix = " + highestGrade);
    }
}