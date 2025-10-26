import services.StudentService;
import services.InstructorService;
import models.Student;
import models.Instructor;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static InstructorService instructorService = new InstructorService();

    public static void main(String[] args) {
        boolean running = true;
        System.out.println("==== Welcome to LMS CLI ====");
        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Student Register");
            System.out.println("2. Student Login");
            System.out.println("3. Instructor Register");
            System.out.println("4. Instructor Login");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1": studentService.registerStudent(); break;
                case "2":
                    Student student = studentService.loginStudent();
                    if (student != null) studentMenu(student);
                    break;
                case "3": instructorService.registerInstructor(); break;
                case "4":
                    Instructor instructor = instructorService.loginInstructor();
                    if (instructor != null) instructorMenu(instructor);
                    break;
                case "5": running = false; System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid option."); break;
            }
        }
    }

    private static void studentMenu(Student student) {
        System.out.println("Student Menu for " + student.getName());
        System.out.println("Features like enroll courses, view assignments can be added here.");
    }

    private static void instructorMenu(Instructor instructor) {
        System.out.println("Instructor Menu for " + instructor.getName());
        System.out.println("1. Create Course");
        System.out.println("2. View Created Courses");
        System.out.print("Choose: ");
        String ch = scanner.nextLine();
        if (ch.equals("1")) instructorService.createCourse(instructor);
    }
}
