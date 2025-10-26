package data;

import models.Student;
import models.Instructor;
import models.Course;
import models.Assignment;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
    public static Map<String, Student> students = new HashMap<>();
    public static Map<String, Instructor> instructors = new HashMap<>();
    public static Map<String, Course> courses = new HashMap<>();
    public static Map<String, Assignment> assignments = new HashMap<>();

    public static void clearAll() {
        students.clear();
        instructors.clear();
        courses.clear();
        assignments.clear();
    }

    public static void printSummary() {
        System.out.println("Data Summary:");
        System.out.println("Students: " + students.size());
        System.out.println("Instructors: " + instructors.size());
        System.out.println("Courses: " + courses.size());
        System.out.println("Assignments: " + assignments.size());
    }
}
