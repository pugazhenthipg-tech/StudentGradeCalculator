import java.util.Locale;
import java.util.Scanner;

/**
 * Task 2 - Student Grade Calculator
 *
 * Reads subject marks, validates them, and prints totals, average, grade and
 * pass/fail. A student passes only when the average and every subject are at
 * least 40.
 */
public class StudentGradeCalculator {

    static final int PASS_MARK = 40;
    static final int MAX_SUBJECTS = 20;
    static final int MAX_MARK = 100;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        } else {
            runCalculator();
        }
        scanner.close();
    }

    static void runCalculator() {
        System.out.println("=== Student Grade Calculator ===");

        int subjectCount = readInt("How many subjects (1-20)? ", 1, MAX_SUBJECTS);
        if (subjectCount == -1) {
            System.out.println("Input ended. Goodbye!");
            return;
        }

        String[] names = new String[subjectCount];
        int[] marks = new int[subjectCount];

        for (int i = 0; i < subjectCount; i++) {
            String defaultName = "Subject " + (i + 1);
            String name = readLine("Name of subject " + (i + 1)
                    + " (Enter for \"" + defaultName + "\"): ");
            if (name == null) {
                System.out.println("Input ended. Goodbye!");
                return;
            }

            name = name.trim();
            if (name.length() == 0) {
                name = defaultName;
            }

            int mark = readInt("Marks for " + name + " (0-100): ", 0, MAX_MARK);
            if (mark == -1) {
                System.out.println("Input ended. Goodbye!");
                return;
            }

            names[i] = name;
            marks[i] = mark;
        }

        printReport(names, marks);
    }

    static void printReport(String[] names, int[] marks) {
        int total = 0;
        int failedSubjects = 0;

        for (int i = 0; i < marks.length; i++) {
            total = total + marks[i];
            if (marks[i] < PASS_MARK) {
                failedSubjects++;
            }
        }

        double average = (double) total / marks.length;
        String overallGrade = gradeFor(average);
        boolean passed = failedSubjects == 0 && average >= PASS_MARK;

        System.out.println();
        System.out.println("----- Report card -----");
        System.out.printf(Locale.US, "%-20s %6s %6s %-6s%n",
                "Subject", "Marks", "Grade", "Status");
        System.out.println("----------------------------------------------");

        for (int i = 0; i < names.length; i++) {
            System.out.printf(Locale.US, "%-20.20s %6d %6s %-6s%n",
                    names[i],
                    marks[i],
                    gradeFor(marks[i]),
                    marks[i] >= PASS_MARK ? "PASS" : "FAIL");
        }

        System.out.println("----------------------------------------------");
        System.out.printf(Locale.US, "Total   : %d / %d%n",
                total, marks.length * MAX_MARK);
        System.out.printf(Locale.US, "Average : %.2f%%%n", average);
        System.out.println("Grade   : " + overallGrade
                + " (" + remarkFor(overallGrade) + ")");
        System.out.println("Result  : " + (passed ? "PASS" : "FAIL"));

        if (failedSubjects > 0) {
            System.out.print("Failed  : ");
            boolean first = true;
            for (int i = 0; i < names.length; i++) {
                if (marks[i] < PASS_MARK) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(names[i] + " (" + marks[i] + ")");
                    first = false;
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Pass rule: average >= 40 and every subject >= 40.");
    }

    static String gradeFor(double percentage) {
        if (percentage >= 90.0) {
            return "A+";
        }
        if (percentage >= 80.0) {
            return "A";
        }
        if (percentage >= 70.0) {
            return "B";
        }
        if (percentage >= 60.0) {
            return "C";
        }
        if (percentage >= 50.0) {
            return "D";
        }
        if (percentage >= 40.0) {
            return "E";
        }
        return "F";
    }

    static String remarkFor(String grade) {
        if (grade.equals("A+")) {
            return "Outstanding";
        }
        if (grade.equals("A")) {
            return "Excellent";
        }
        if (grade.equals("B")) {
            return "Very good";
        }
        if (grade.equals("C")) {
            return "Good";
        }
        if (grade.equals("D")) {
            return "Satisfactory";
        }
        if (grade.equals("E")) {
            return "Pass";
        }
        return "Fail";
    }

    /**
     * Reads a whole number in the given range.
     *
     * @return the number, or -1 when input ends
     */
    static int readInt(String prompt, int minimum, int maximum) {
        while (true) {
            String line = readLine(prompt);
            if (line == null) {
                return -1;
            }

            try {
                int value = Integer.parseInt(line.trim());
                if (value < minimum || value > maximum) {
                    System.out.println("Enter a whole number from "
                            + minimum + " to " + maximum + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    static String readLine(String prompt) {
        System.out.print(prompt);
        if (!scanner.hasNextLine()) {
            return null;
        }
        return scanner.nextLine();
    }

    static void runDemo() {
        System.out.println("=== Student Grade Calculator (demo) ===");

        String[] names = {
            "Mathematics",
            "Physics",
            "Chemistry",
            "English",
            "Computer Science"
        };
        int[] marks = {88, 92, 76, 65, 49};

        printReport(names, marks);
    }
}
