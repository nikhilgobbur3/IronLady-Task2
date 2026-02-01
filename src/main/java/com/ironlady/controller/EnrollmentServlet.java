package com.ironlady.controller;

import com.ironlady.dao.EnrollmentDAO;
import com.ironlady.dao.StudentDAO;
import com.ironlady.model.Enrollment;
import com.ironlady.model.Student;
import com.ironlady.service.RuleBasedAI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/enrollments")
public class EnrollmentServlet extends HttpServlet {
    private EnrollmentDAO enrollmentDAO;
    private StudentDAO studentDAO;

    @Override
    public void init() {
        enrollmentDAO = new EnrollmentDAO();
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
            action = "list";

        try {
            switch (action) {
                case "new":
                    showNewForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    deleteEnrollment(req, resp);
                    break;
                default:
                    listEnrollments(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("insert".equals(action)) {
                insertEnrollment(req, resp);
            } else if ("update".equals(action)) {
                updateEnrollment(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEnrollments(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Enrollment> listEnrollments = enrollmentDAO.getAllEnrollments();
        req.setAttribute("listEnrollments", listEnrollments);
        req.getRequestDispatcher("enrollment-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Student> students = studentDAO.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("enrollment-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Enrollment existingEnrollment = enrollmentDAO.getEnrollmentById(id);
        List<Student> students = studentDAO.getAllStudents();
        req.setAttribute("enrollment", existingEnrollment);
        req.setAttribute("students", students);
        req.getRequestDispatcher("enrollment-form.jsp").forward(req, resp);
    }

    private void insertEnrollment(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String courseName = req.getParameter("courseName");
        Date startDate = Date.valueOf(req.getParameter("startDate"));

        // AI Logic integration
        String status = RuleBasedAI.suggestStatus(startDate);
        String priority = RuleBasedAI.suggestPriority(courseName);
        String aiFlag = RuleBasedAI.analyzeEnrollment(courseName, startDate);

        Enrollment newEnrollment = new Enrollment(studentId, courseName, startDate, status, priority, aiFlag);
        enrollmentDAO.addEnrollment(newEnrollment);
        resp.sendRedirect("enrollments");
    }

    private void updateEnrollment(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));
        String courseName = req.getParameter("courseName");
        Date startDate = Date.valueOf(req.getParameter("startDate"));

        // AI Logic integration (Re-evaluating might be desired, or keeping manual
        // edits. Let's re-evaluate for automation demo)
        String status = RuleBasedAI.suggestStatus(startDate);
        String priority = RuleBasedAI.suggestPriority(courseName);
        String aiFlag = RuleBasedAI.analyzeEnrollment(courseName, startDate);

        Enrollment enrollment = new Enrollment(id, studentId, courseName, startDate, status, priority, aiFlag, null);
        enrollmentDAO.updateEnrollment(enrollment);
        resp.sendRedirect("enrollments");
    }

    private void deleteEnrollment(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        enrollmentDAO.deleteEnrollment(id);
        resp.sendRedirect("enrollments");
    }
}
