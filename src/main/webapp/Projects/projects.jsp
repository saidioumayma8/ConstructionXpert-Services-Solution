<%@ page import="java.sql.*" %>
<%@ page import="Utils.DatabaseConnection" %>  <%-- Import the DBConnection class --%>

<%
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    try {
        conn = DatabaseConnection.getConnection();
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Projet");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Projects</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-8">
<h1 class="text-3xl font-bold mb-6">Project List</h1>
<a href="addProject.jsp" class="bg-blue-500 text-white px-4 py-2 rounded mb-4 inline-block">Add Project</a>

<table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden mt-4">
    <thead>
    <tr class="bg-gray-200">
        <th class="py-2 px-4">ID</th>
        <th class="py-2 px-4">Name</th>
        <th class="py-2 px-4">Start Date</th>
        <th class="py-2 px-4">End Date</th>
        <th class="py-2 px-4">Budget</th>
        <th class="py-2 px-4">Actions</th>
    </tr>
    </thead>
    <tbody>
    <% while (rs.next()) { %>
    <tr class="border-b">
        <td class="py-2 px-4"><%= rs.getInt("id") %></td>
        <td class="py-2 px-4"><%= rs.getString("nom") %></td>
        <td class="py-2 px-4"><%= rs.getDate("date_debut") %></td>
        <td class="py-2 px-4"><%= rs.getDate("date_fin") %></td>
        <td class="py-2 px-4">$<%= rs.getDouble("budget") %></td>
        <td class="py-2 px-4">
            <a href="projectDetails.jsp?id=<%= rs.getInt("id") %>" class="text-blue-600">View</a> |
            <a href="editProject.jsp?id=<%= rs.getInt("id") %>" class="text-green-600">Edit</a> |
            <a href="deleteProject.jsp?id=<%= rs.getInt("id") %>" class="text-red-600" onclick="return confirm('Are you sure?');">Delete</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>
</body>
</html>

<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close resources in finally block to ensure they are closed even if an exception occurs
        if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }
%>
