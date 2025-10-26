package services;

import data.DataStore;
import models.Student;
import utils.InputValidator;

import java.util.Scanner;
import java.util.UUID;

public class StudentService {
    private Scanner scanner = new Scanner(System.in);

    public void registerStudent() {
        System.out.println("\n=== Student Registration ===");
        System.out.print("Enter full name: "); String name = scanner.nextLine();
        System.out.print("Enter username: "); String username = scanner.nextLine();
        if (DataStore.students.containsKey(username)) {
            System.out.println("Username already exists."); return;
        }
        System.out.print("Enter password: "); String password = scanner.nextLine();
        System.out.print("Enter email: "); String email = scanner.nextLine();
        System.out.print("Enter phone number: "); String phone = scanner.nextLine();
        if (!InputValidator.isValidEmail(email)) { System.out.println("Invalid email."); return; }
        if (!InputValidator.isValidPhone(phone)) { System.out.println("Invalid phone."); return; }
        String id = UUID.randomUUID().toString();
        Student student = new Student(id, name, username, password, email, phone);
        DataStore.students.put(username, student);
        System.out.println("✅ Registration successful for " + name + "!");
    }

    public Student loginStudent() {
        System.out.println("\n=== Student Login ===");
        System.out.print("Enter username: "); String username = scanner.nextLine();
        System.out.print("Enter password: "); String password = scanner.nextLine();
        if (!DataStore.students.containsKey(username)) { System.out.println("❌ Username not found."); return null; }
        Student student = DataStore.students.get(username);
        if (!student.getPassword().equals(password)) { System.out.println("❌ Incorrect password."); return null; }
        System.out.println("✅ Welcome, " + student.getName() + "!");
        return student;
    }
}
