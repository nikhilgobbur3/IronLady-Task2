package com.ironlady.dao;

import com.ironlady.model.Enrollment;
import com.ironlady.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    public void addEnrollment(Enrollment enrollment) throws SQLException {
        String sql = "INSERT INTO enrollments (student_id, course_name, start_date, status, priority, ai_flag) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setString(2, enrollment.getCourseName());
            pstmt.setDate(3, enrollment.getStartDate());
            pstmt.setString(4, enrollment.getStatus());
            pstmt.setString(5, enrollment.getPriority());
            pstmt.setString(6, enrollment.getAiFlag());
            pstmt.executeUpdate();
        }
    }

    public List<Enrollment> getAllEnrollments() throws SQLException {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT e.*, s.name as student_name FROM enrollments e JOIN students s ON e.student_id = s.id ORDER BY e.created_at DESC";
        try (Connection conn = DBConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Enrollment e = new Enrollment(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("course_name"),
                        rs.getDate("start_date"),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getString("ai_flag"),
                        rs.getTimestamp("created_at"));
                e.setStudentName(rs.getString("student_name"));
                enrollments.add(e);
            }
        }
        return enrollments;
    }

    public Enrollment getEnrollmentById(int id) throws SQLException {
        String sql = "SELECT e.*, s.name as student_name FROM enrollments e JOIN students s ON e.student_id = s.id WHERE e.id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Enrollment e = new Enrollment(
                            rs.getInt("id"),
                            rs.getInt("student_id"),
                            rs.getString("course_name"),
                            rs.getDate("start_date"),
                            rs.getString("status"),
                            rs.getString("priority"),
                            rs.getString("ai_flag"),
                            rs.getTimestamp("created_at"));
                    e.setStudentName(rs.getString("student_name"));
                    return e;
                }
            }
        }
        return null;
    }

    public void updateEnrollment(Enrollment enrollment) throws SQLException {
        String sql = "UPDATE enrollments SET student_id = ?, course_name = ?, start_date = ?, status = ?, priority = ?, ai_flag = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentId());
            pstmt.setString(2, enrollment.getCourseName());
            pstmt.setDate(3, enrollment.getStartDate());
            pstmt.setString(4, enrollment.getStatus());
            pstmt.setString(5, enrollment.getPriority());
            pstmt.setString(6, enrollment.getAiFlag());
            pstmt.setInt(7, enrollment.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteEnrollment(int id) throws SQLException {
        String sql = "DELETE FROM enrollments WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
