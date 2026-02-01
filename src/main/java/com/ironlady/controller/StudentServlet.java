package com.ironlady.controller;

import com.ironlady.dao.StudentDAO;
import com.ironlady.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private StudentDAO studentDAO;

    @Override
    public void init() {
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
                    deleteStudent(req, resp);
                    break;
                default:
                    listStudents(req, resp);
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
                insertStudent(req, resp);
            } else if ("update".equals(action)) {
                updateStudent(req, resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudents(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        List<Student> listStudents = studentDAO.getAllStudents();
        req.setAttribute("listStudents", listStudents);
        req.getRequestDispatcher("student-list.jsp").forward(req, resp);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("student-form.jsp").forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student existingStudent = studentDAO.getStudentById(id);
        req.setAttribute("student", existingStudent);
        req.getRequestDispatcher("student-form.jsp").forward(req, resp);
    }

    private void insertStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Student newStudent = new Student(name, email, phone);
        studentDAO.addStudent(newStudent);
        resp.sendRedirect("students");
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        Student student = new Student(id, name, email, phone, null);
        studentDAO.updateStudent(student);
        resp.sendRedirect("students");
    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentDAO.deleteStudent(id);
        resp.sendRedirect("students");
    }
}
