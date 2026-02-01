<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Iron Lady - Enrollment List</title>
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
                    <h2>Enrollment List</h2>
                    <a href="enrollments?action=new" class="btn"
                        style="background: var(--accent-color); color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px;">+
                        Add Enrollment</a>
                </div>

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Student Name</th>
                            <th>Course</th>
                            <th>Start Date</th>
                            <th>Status</th>
                            <th>Priority (AI)</th>
                            <th>AI Analysis</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="enrollment" items="${listEnrollments}">
                            <tr>
                                <td>
                                    <c:out value="${enrollment.id}" />
                                </td>
                                <td>
                                    <c:out value="${enrollment.studentName}" />
                                </td>
                                <td>
                                    <c:out value="${enrollment.courseName}" />
                                </td>
                                <td>
                                    <c:out value="${enrollment.startDate}" />
                                </td>
                                <td><span class="ai-badge" style="background: #eee;">
                                        <c:out value="${enrollment.status}" />
                                    </span></td>
                                <td>
                                    <span class="ai-badge priority-${enrollment.priority}">
                                        <c:out value="${enrollment.priority}" />
                                    </span>
                                </td>
                                <td>
                                    <c:if test="${enrollment.aiFlag != 'OK'}">
                                        <span class="ai-flag">⚠️
                                            <c:out value="${enrollment.aiFlag}" />
                                        </span>
                                    </c:if>
                                    <c:if test="${enrollment.aiFlag == 'OK'}">
                                        <span style="color: green;">✔ OK</span>
                                    </c:if>
                                </td>
                                <td class="action-links">
                                    <a href="enrollments?action=edit&id=${enrollment.id}">Edit</a>
                                    <a href="enrollments?action=delete&id=${enrollment.id}" class="delete"
                                        onclick="return confirm('Delete this enrollment?');">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <script src="js/script.js"></script>
        </body>

        </html>