package models;

import java.util.ArrayList;
import java.util.List;

public class Instructor {
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private List<String> createdCourses;

    public Instructor(String id, String name, String username, String password, String email, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.createdCourses = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<String> getCreatedCourses() { return createdCourses; }

    public void addCourse(String courseId) { createdCourses.add(courseId); }

    @Override
    public String toString() {
        return name + " (" + username + ") | " + email + " | " + phone;
    }
}
