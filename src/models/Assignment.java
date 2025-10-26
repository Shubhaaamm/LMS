package models;

public class Assignment {
    private String id;
    private String courseId;
    private String title;
    private String description;

    public Assignment(String id, String courseId, String title, String description) {
        this.id = id;
        this.courseId = courseId;
        this.title = title;
        this.description = description;
    }

    public String getId() { return id; }
    public String getCourseId() { return courseId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "[" + courseId + "] " + title + " - " + description;
    }
}

