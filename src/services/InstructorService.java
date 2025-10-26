package services;

import data.DataStore;
import models.Instructor;
import models.Course;
import utils.InputValidator;

import java.util.Scanner;
import java.util.UUID;

public class InstructorService {
    private Scanner scanner = new Scanner(System.in);

    public void registerInstructor() {
        System.out.println("\n=== Instructor Registration ===");
        System.out.print("Enter full name: "); String name = scanner.nextLine();
        System.out.print("Enter username: "); String username = scanner.nextLine();
        if (DataStore.instructors.containsKey(username)) { System.out.println("Username exists."); return; }
        System.out.print("Enter password: "); String password = scanner.nextLine();
        System.out.print("Enter email: "); String email = scanner.nextLine();
        System.out.print("Enter phone: "); String phone = scanner.nextLine();
        if (!InputValidator.isValidEmail(email)) { System.out.println("Invalid email."); return; }
        if (!InputValidator.isValidPhone(phone)) { System.out.println("Invalid phone."); return; }
        String id = UUID.randomUUID().toString();
        Instructor instructor = new Instructor(id, name, username, password, email, phone);
        DataStore.instructors.put(username, instructor);
        System.out.println("✅ Registration successful for " + name + "!");
    }

    public Instructor loginInstructor() {
        System.out.println("\n=== Instructor Login ===");
        System.out.print("Enter username: "); String username = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();
        if (!DataStore.instructors.containsKey(username)) { System.out.println("❌ Username not found."); return null; }
        Instructor instructor = DataStore.instructors.get(username);
        if (!instructor.getPassword().equals(password)) { System.out.println("❌ Incorrect password."); return null; }
        System.out.println("✅ Welcome, " + instructor.getName() + "!");
        return instructor;
    }

    public void createCourse(Instructor instructor) {
        System.out.println("\n=== Create a New Course ===");
        System.out.print("Enter course title: "); String title = scanner.nextLine();
        String courseId = UUID.randomUUID().toString();
        Course course = new Course(courseId, title, instructor.getId());
        DataStore.courses.put(courseId, course);
        instructor.addCourse(courseId);
        System.out.println("✅ Course '" + title + "' created successfully!");
    }
}
