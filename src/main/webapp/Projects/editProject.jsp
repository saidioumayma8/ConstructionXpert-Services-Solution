<%@ page import="Models.Project" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    Project project = (Project) request.getAttribute("project");
    if (project == null) {
%>
<p>Error: Project not found.</p>
<%
        return;
    }
%>

<h2>Modify Project</h2>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<div class="error-message"><%= errorMessage %></div>
<%
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Project - ConstructionXpert</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
</head>
<body class="bg-gray-900 dark:bg-gray-900 dark:text-white">

<!-- Sidebar -->
<div class="flex h-screen">
    <aside class="w-64 bg-gray-800 dark:bg-gray-800 shadow-lg transform transition-transform duration-300 ease-in-out" id="sidebar">
        <div class="p-6">
            <h2 class="text-xl font-bold text-gray-200 dark:text-gray-200">Admin Panel</h2>
        </div>
        <nav class="mt-4">
            <a href="projects" class="sidebar-link">Projects</a>
            <a href="Tasks.jsp" class="sidebar-link">Tasks</a>
            <a href="Users.jsp" class="sidebar-link">Users</a>
            <a href="Reports.jsp" class="sidebar-link">Reports</a>
            <a href="Settings.jsp" class="sidebar-link">Settings</a>
            <a href="LogoutServlet" class="sidebar-link text-red-600">Logout</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-8">
        <button id="toggleSidebar" class="px-4 py-2 rounded mb-4 text-gray-700 dark:text-white">Toggle Sidebar</button>
        <h1 class="text-3xl font-bold text-gray-900 dark:text-white animate-fade-in">Add New Project</h1>
<form action="modifyProject" method="post" class="bg-gray-800 p-6 shadow-lg rounded-lg mt-6">
    <input type="hidden" name="id" class="w-full px-4 py-2 border rounded-lg bg-gray-700 text-gray-100"value="<%= project.getId() %>" />

    <label for="nom">Project Name:</label>
    <input type="text" id="nom" name="nom" class="w-full px-4 py-2 border rounded-lg bg-gray-700 text-gray-100"value="<%= project.getNom() %>" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" class="w-full px-4 py-2 border rounded-lg bg-gray-700 text-gray-100"required><%= project.getDescription() %></textarea><br><br>

    <label for="dateDebut">Start Date:</label>
    <input type="date" id="dateDebut" name="dateDebut" class="w-full px-4 py-2 border rounded-lg bg-gray-700 text-gray-100"value="<%= project.getDateDebut() %>" required><br><br>

    <label for="dateFin">End Date:</label>
    <input type="date" id="dateFin" name="dateFin" class="w-full px-4 py-2 border rounded-lg bg-gray-700 text-gray-100"value="<%= project.getDateFin() %>" required><br><br>

    <label for="budget">Budget:</label>
    <input type="number" id="budget" name="budget"class="w-full px-4 py-2 border rounded-lg bg-gray-700 text-gray-100" value="<%= project.getBudget() %>" required><br><br>

    <button type="submit">Update Project</button>
</form>


    </main>
</div>

<!-- Styles -->
<style>
    /* Buttons */
    button {
        position: relative;
        display: inline-block;
        margin: 15px;
        padding: 15px 30px;
        text-align: center;
        font-size: 18px;
        letter-spacing: 1px;
        text-decoration: none;
        color: #ffffff;
        background: transparent;
        cursor: pointer;
        transition: ease-out 0.5s;
        border: 2px solid #ff8000;
        border-radius: 10px;
        box-shadow: inset 0 0 0 0 #ff8000;
    }

    button:hover {
        color: white;
        box-shadow: inset 0 -100px 0 0 #ff8000;
    }

    button:active {
        transform: scale(0.9);
    }

    /* Sidebar */
    .sidebar-link {
        display: block;
        padding: 12px;
        transition: transform 0.2s;
    }

    .sidebar-link:hover {
        background: #f3f4f6;
        transform: translateX(5px);
    }

    /* Animation */
    @keyframes fadeIn {
        from {
            opacity: 0;
            transform: translateY(-20px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }

    .animate-fade-in {
        animation: fadeIn 0.8s ease-in-out;
    }
</style>

<!-- JavaScript for Sidebar Toggle -->
<script>
    window.onload = function () {
        // Sidebar Toggle
        document.getElementById("toggleSidebar").addEventListener("click", function () {
            document.getElementById("sidebar").classList.toggle("-translate-x-64");
        });
    };
    document.addEventListener("DOMContentLoaded", function () {
        document.querySelector("form").addEventListener("submit", function (event) {
            let startDate = new Date(document.querySelector('input[name="date_debut"]').value);
            let endDate = new Date(document.querySelector('input[name="date_fin"]').value);
            let errorMsg = document.getElementById("dateError");

            if (startDate >= endDate) {
                event.preventDefault();
                errorMsg.textContent = "La date de debut doit etre avant la date de fin.";
                errorMsg.classList.remove("hidden");
            } else {
                errorMsg.classList.add("hidden");
            }
        });
    });
</script>

</body>
</html>
