<%@ page import="java.sql.*, javax.servlet.http.*" %>
<%
    HttpSession session = request.getSession(false);
    String user = (session != null) ? (String) session.getAttribute("user") : null;
    if (user == null) {
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h1>Welcome, <%= user %></h1>
        <a href="logout.jsp">Logout</a>
    </div>
</body>
</html>
