<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Iron Lady - Enrollment Form</title>
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
                    <c:if test="${enrollment != null}">Edit Enrollment</c:if>
                    <c:if test="${enrollment == null}">New Enrollment</c:if>
                </h2>

                <div class="form-container">
                    <form action="enrollments" method="post">
                        <input type="hidden" name="action" value="${enrollment != null ? 'update' : 'insert'}">
                        <c:if test="${enrollment != null}">
                            <input type="hidden" name="id" value="${enrollment.id}">
                        </c:if>

                        <div class="form-group">
                            <label>Student:</label>
                            <select name="studentId" required>
                                <c:forEach var="st" items="${students}">
                                    <option value="${st.id}" <c:if test="${enrollment.studentId == st.id}">selected
                                        </c:if>>
                                        <c:out value="${st.name}" /> (${st.email})
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-group">
                            <label>Course Name:</label>
                            <input type="text" name="courseName" value="<c:out value='${enrollment.courseName}' />"
                                placeholder="e.g. Java Masterclass" required>
                            <small>Type 'Premium', 'Advanced', or 'Masterclass' to trigger High PriorityAI
                                suggestion.</small>
                        </div>

                        <div class="form-group">
                            <label>Start Date:</label>
                            <input type="date" name="startDate" value="<c:out value='${enrollment.startDate}' />"
                                required>
                            <small>Future dates will set Status to 'Pending'. Past/Today set to 'Active'.</small>
                        </div>

                        <button type="submit">Save Enrollment</button>
                        <a href="enrollments" class="btn-secondary"
                            style="padding: 10px 20px; border-radius: 4px; text-decoration: none; color: white;">Cancel</a>
                    </form>
                </div>
            </div>

            <script src="js/script.js"></script>
        </body>

        </html>