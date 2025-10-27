package services;

import data.DataStore;
import models.Student;
import models.Instructor;
import models.Course;

import java.util.*;

public class CourseService {
    private Scanner scanner = new Scanner(System.in);
    private static int courseCounter = 1; // Keeps track of number generation

    // === STUDENT MENU ===
    public void studentMenu(Student s) {
        while (true) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View All Courses");
            System.out.println("2. Enroll in Course");
            System.out.println("3. View My Courses");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> viewAllCourses();
                case 2 -> enrollCourseByCode(s);
                case 3 -> viewEnrolledCourses(s);
                case 4 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // === INSTRUCTOR MENU ===
    public void instructorMenu(Instructor i) {
        while (true) {
            System.out.println("\n=== Instructor Menu ===");
            System.out.println("1. Create Course");
            System.out.println("2. View My Courses");
            System.out.println("3. Logout");
            System.out.print("Choose option: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> createCourse(i);
                case 2 -> viewInstructorCourses(i);
                case 3 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // === CREATE COURSE ===
    public void createCourse(Instructor i) {
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();

        // Generate readable ID
        String courseCode = String.format("C%03d", courseCounter++);
        String id = UUID.randomUUID().toString();

        Course c = new Course(id, courseCode, title, i.getId());
        DataStore.courses.put(courseCode, c);
        i.addCourse(courseCode);

        System.out.println(" Course '" + title + "' created with ID: " + courseCode);
    }

    // === VIEW ALL COURSES ===
    public void viewAllCourses() {
        if (DataStore.courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        System.out.println("\n=== Available Courses ===");
        for (Course c : DataStore.courses.values()) {
            System.out.println(c.getCourseCode() + " - " + c.getTitle());
        }
    }

    // === STUDENT ENROLLMENT BY COURSE CODE ===
    public void enrollCourseByCode(Student s) {
        if (DataStore.courses.isEmpty()) {
            System.out.println("No courses available to enroll.");
            return;
        }

        viewAllCourses();
        System.out.print("\nEnter Course Code (e.g., C001): ");
        String code = scanner.nextLine().trim().toUpperCase();

        Course c = DataStore.courses.get(code);
        if (c == null) {
            System.out.println(" Course not found.");
            return;
        }

        if (s.getEnrolledCourses().contains(code)) {
            System.out.println("You are already enrolled in this course.");
            return;
        }

        s.enrollCourse(code);
        System.out.println(" Enrolled successfully in " + c.getTitle() + " (" + code + ")");
    }

    // === STUDENT VIEW ENROLLED COURSES ===
    public void viewEnrolledCourses(Student s) {
        if (s.getEnrolledCourses().isEmpty()) {
            System.out.println("No courses enrolled yet.");
            return;
        }

        System.out.println("\n=== My Courses ===");
        for (String code : s.getEnrolledCourses()) {
            Course c = DataStore.courses.get(code);
            if (c != null)
                System.out.println(code + " - " + c.getTitle());
        }
    }

    // === INSTRUCTOR VIEW CREATED COURSES ===
    public void viewInstructorCourses(Instructor i) {
        if (i.getCreatedCourses().isEmpty()) {
            System.out.println("No courses created yet.");
            return;
        }
        System.out.println("\n=== My Created Courses ===");
        for (String code : i.getCreatedCourses()) {
            Course c = DataStore.courses.get(code);
            if (c != null)
                System.out.println(code + " - " + c.getTitle());
        }
    }
}