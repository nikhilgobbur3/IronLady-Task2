<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Iron Lady - Admin Login</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
            <style>
                body {
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    min-height: 100vh;
                    background: linear-gradient(135deg, #2c3e50 0%, #3498db 100%);
                }

                .login-card {
                    background: white;
                    padding: 40px;
                    border-radius: 8px;
                    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.2);
                    width: 100%;
                    max-width: 400px;
                    text-align: center;
                }

                .login-header h1 {
                    color: #2c3e50;
                    font-size: 2rem;
                    margin-bottom: 2rem;
                }

                .login-form input {
                    margin-bottom: 1.5rem;
                    padding: 12px;
                    font-size: 1rem;
                }

                .login-btn {
                    width: 100%;
                    padding: 12px;
                    font-size: 1.1rem;
                    background-color: #2c3e50;
                    transition: 0.3s;
                }

                .login-btn:hover {
                    background-color: #34495e;
                }

                .error-msg {
                    color: #e74c3c;
                    margin-bottom: 15px;
                    display: block;
                }
            </style>
        </head>

        <body>

            <div class="login-card">
                <div class="login-header">
                    <h1>Iron Lady Admin</h1>
                </div>

                <c:if test="${errorMessage != null}">
                    <span class="error-msg">
                        <c:out value="${errorMessage}" />
                    </span>
                </c:if>

                <form action="login" method="post" class="login-form">
                    <div class="form-group">
                        <input type="text" name="username" placeholder="Username" required autocomplete="off">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" placeholder="Password" required>
                    </div>
                    <button type="submit" class="login-btn">Secure Login</button>
                </form>
            </div>

        </body>

        </html>