package com.ironlady.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Enrollment {
    private int id;
    private int studentId;
    private String courseName;
    private Date startDate;
    private String status;
    private String priority;
    private String aiFlag;
    private Timestamp createdAt;

    // Helper field for display
    private String studentName;

    public Enrollment() {
    }

    public Enrollment(int id, int studentId, String courseName, Date startDate, String status, String priority,
            String aiFlag, Timestamp createdAt) {
        this.id = id;
        this.studentId = studentId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.status = status;
        this.priority = priority;
        this.aiFlag = aiFlag;
        this.createdAt = createdAt;
    }

    public Enrollment(int studentId, String courseName, Date startDate, String status, String priority, String aiFlag) {
        this.studentId = studentId;
        this.courseName = courseName;
        this.startDate = startDate;
        this.status = status;
        this.priority = priority;
        this.aiFlag = aiFlag;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getAiFlag() {
        return aiFlag;
    }

    public void setAiFlag(String aiFlag) {
        this.aiFlag = aiFlag;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
