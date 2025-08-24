import java.io.*;
import java.util.*;

// Step 1: Student Class
class Student implements Serializable {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public String getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Student [Roll No: " + rollNumber + ", Name: " + name + ", Grade: " + grade + "]";
    }
}

// Step 2: Student Management System
class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }

    // Add student with validation
    public void addStudent(String name, int rollNumber, String grade) {
        if (name.isEmpty() || grade.isEmpty()) {
            System.out.println("❌ Name and Grade cannot be empty!");
            return;
        }
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                System.out.println("❌ Roll Number already exists!");
                return;
            }
        }
        students.add(new Student(name, rollNumber, grade));
        saveToFile();
        System.out.println("✅ Student added successfully.");
    }

    // Remove student
    public void removeStudent(int rollNumber) {
        Student toRemove = null;
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                toRemove = s;
                break;
            }
        }
        if (toRemove != null) {
            students.remove(toRemove);
            saveToFile();
            System.out.println("✅ Student removed successfully.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    // Search student
    public void searchStudent(int rollNumber) {
        for (Student s : students) {
            if (s.getRollNumber() == rollNumber) {
                System.out.println("🔍 Found: " + s);
                return;
            }
        }
        System.out.println("❌ Student not found.");
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students found.");
            return;
        }
        System.out.println("📋 All Students:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // Save to file
    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    // Load from file
    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if no file yet
        } catch (Exception e) {
            System.out.println("❌ Error loading data: " + e.getMessage());
        }
    }
}

// Step 3: Console-based UI
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();
                    sms.addStudent(name, roll, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll Number to remove: ");
                    int rollRemove = sc.nextInt();
                    sms.removeStudent(rollRemove);
                    break;
                case 3:
                    System.out.print("Enter Roll Number to search: ");
                    int rollSearch = sc.nextInt();
                    sms.searchStudent(rollSearch);
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    System.out.println("👋 Exiting... Data saved.");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
