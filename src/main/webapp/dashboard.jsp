<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>Iron Lady - Dashboard</title>
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
            <h2>Welcome to the Automation Dashboard</h2>

            <div class="dashboard-grid">
                <div class="card" onclick="window.location.href='students'">
                    <h3>Manage Students</h3>
                    <p>View List</p>
                    <span>Add, Edit, or Remove Students</span>
                </div>

                <div class="card" onclick="window.location.href='enrollments'">
                    <h3>Manage Enrollments</h3>
                    <p>View Active</p>
                    <span>Process student enrollments</span>
                </div>

                <div class="card">
                    <h3>System Status</h3>
                    <p>Online</p>
                    <span>AI Logic Service Running</span>
                </div>
            </div>

            <div class="ai-insight">
                <h3>AI Insight</h3>
                <p>The system is currently using rule-based AI to automatically suggest flow priority and enrollment
                    status based on course data logic.</p>
            </div>
        </div>

        <script src="js/script.js"></script>
    </body>

    </html>