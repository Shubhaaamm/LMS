package models;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String title;
    private String instructorId;
    private List<String> enrolledStudents;

    public Course(String id, String title, String instructorId) {
        this.id = id;
        this.title = title;
        this.instructorId = instructorId;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getInstructorId() { return instructorId; }
    public List<String> getEnrolledStudents() { return enrolledStudents; }

    public void enrollStudent(String studentId) {
        if (!enrolledStudents.contains(studentId)) enrolledStudents.add(studentId);
    }

    @Override
    public String toString() {
        return title + " (Instructor ID: " + instructorId + ")";
    }
}
