<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Iron Lady - Student List</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
        </head>

        <body>
            <header>
                <div class="container">
                    <h1>Iron Lady Enrollment System</h1>
                    <nav>
                        <ul>
                            <li><a href="dashboard">Dashboard</a></li>
                            <li><a href="students">Students</a></li>
                            <li><a href="enrollments">Enrollments</a></li>
                            <li><a href="logout"
                                    style="background-color: #e74c3c; padding: 10px 15px; border-radius: 4px;">Logout</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </header>

            <div class="container">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 20px;">
                    <h2>Student List</h2>
                    <a href="students?action=new" class="btn"
                        style="background: var(--accent-color); color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px;">+
                        Add New Student</a>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="student" items="${listStudents}">
                            <tr>
                                <td>
                                    <c:out value="${student.id}" />
                                </td>
                                <td>
                                    <c:out value="${student.name}" />
                                </td>
                                <td>
                                    <c:out value="${student.email}" />
                                </td>
                                <td>
                                    <c:out value="${student.phone}" />
                                </td>
                                <td class="action-links">
                                    <a href="students?action=edit&id=${student.id}">Edit</a>
                                    <a href="students?action=delete&id=${student.id}" class="delete"
                                        onclick="return confirm('Are you sure you want to delete this student?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <script src="js/script.js"></script>
        </body>

        </html>