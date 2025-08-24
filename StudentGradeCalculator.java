import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Input number of subjects
        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();

        int[] marks = new int[n];
        int totalMarks = 0;

        // Step 2: Take marks input for each subject
        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
            marks[i] = sc.nextInt();
            totalMarks += marks[i];
        }

        // Step 3: Calculate average percentage
        double averagePercentage = (double) totalMarks / n;

        // Step 4: Grade calculation
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 75) {
            grade = 'B';
        } else if (averagePercentage >= 60) {
            grade = 'C';
        } else if (averagePercentage >= 40) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Step 5: Display results
        System.out.println("\n📊 Results:");
        System.out.println("Total Marks = " + totalMarks + " out of " + (n * 100));
        System.out.println("Average Percentage = " + averagePercentage + "%");
        System.out.println("Grade = " + grade);

        sc.close();
    }
}
