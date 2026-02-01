<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Iron Lady - Student Form</title>
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
                <h2>
                    <c:if test="${student != null}">Edit Student</c:if>
                    <c:if test="${student == null}">Add New Student</c:if>
                </h2>

                <div class="form-container">
                    <form action="students" method="post">
                        <input type="hidden" name="action" value="${student != null ? 'update' : 'insert'}">
                        <c:if test="${student != null}">
                            <input type="hidden" name="id" value="${student.id}">
                        </c:if>

                        <div class="form-group">
                            <label>Full Name:</label>
                            <input type="text" name="name" value="<c:out value='${student.name}' />" required>
                        </div>

                        <div class="form-group">
                            <label>Email:</label>
                            <input type="email" name="email" value="<c:out value='${student.email}' />" required>
                        </div>

                        <div class="form-group">
                            <label>Phone:</label>
                            <input type="text" name="phone" value="<c:out value='${student.phone}' />" required>
                        </div>

                        <button type="submit">Save</button>
                        <a href="students" class="btn-secondary"
                            style="padding: 10px 20px; border-radius: 4px; text-decoration: none; color: white;">Cancel</a>
                    </form>
                </div>
            </div>

            <script src="js/script.js"></script>
        </body>

        </html>