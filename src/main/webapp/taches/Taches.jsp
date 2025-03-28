<%@ page import="Models.Tache" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - ConstructionXpert</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.2/gsap.min.js"></script>
</head>
<body class="bg-gray-50 dark:bg-gray-900 dark:text-white">

<!-- Sidebar -->
<div class="flex h-screen">
    <aside class="w-64 bg-white dark:bg-gray-800 shadow-lg transform transition-transform duration-300 ease-in-out" id="sidebar">
        <div class="p-6">
            <h2 class="text-xl font-bold text-gray-800 dark:text-gray-200">Admin Panel</h2>
        </div>
        <nav class="mt-4">
            <a href="projects" class="sidebar-link">Projects</a>
            <a href="Tasks.jsp" class="sidebar-link">Tasks</a>
            <a href="Users.jsp" class="sidebar-link">Users</a>
            <a href="Reports.jsp" class="sidebar-link">Reports</a>
            <a href="Settings.jsp" class="sidebar-link">Settings</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-8">
        <button id="toggleSidebar" class=" px-4 py-2 rounded mb-4">Toggle Sidebar</button>

        <h1 class="text-3xl font-bold text-gray-900 dark:text-white animate-fade-in">All Tasks</h1>
        <p class="text-gray-600 dark:text-gray-300 mt-2">Here is a list of all the tasks.</p>

        <%
            List<Tache> tasks = (List<Tache>) request.getAttribute("tasks");
        %>

        <table class="w-full border-collapse">
            <thead>
            <tr class="bg-gray-200">
                <th class="p-3">ID</th>
                <th class="p-3">Description</th>
                <th class="p-3">Start Date</th>
                <th class="p-3">End Date</th>
                <th class="p-3">ProjectID</th>
                <th class="p-3">Actions</th>
            </tr>
            </thead>
            <tbody>
            <% if (tasks != null && !tasks.isEmpty()) { %>
            <% for(Tache task : tasks) { %>
            <tr class="border-b hover:bg-[#A8D5BA]/20 transition duration-300">
                <td class="p-3"><%= task.getId() %></td>
                <td class="p-3"><%= task.getDescription() %></td>
                <td class="p-3"><%= task.getDateDebut() %></td>
                <td class="p-3"><%= task.getDateFin() %></td>
                <td class="p-3"><%= task.getProjectId() %></td>
                <td>
                    <!-- Edit Button -->
                    <a href="modifytask?id=<%= task.getId %>" class="btn btn-primary">Edit</a>
                    <!-- Delete Button with Confirmation -->
                    <a href="deleteTask?id=<%= task.getId() %>" class="btn btn-danger"
                       onclick="return confirm('Are you sure you want to delete this task?');">Delete</a>
                </td>
            </tr>
            <% } %>
            <% } else { %>
            <tr>
                <td colspan="6" class="p-3 text-center text-red-500">No tasks found!</td>
            </tr>
            <% } %>
            </tbody>
        </table>

    </main>
</div>
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
</style>

<!-- JavaScript for Animations and Dark Mode Toggle -->
<script>
    window.onload = function () {
        document.getElementById("toggleSidebar").addEventListener("click", function () {
            document.getElementById("sidebar").classList.toggle("-translate-x-64");
        });

        function animateCounter(elementId, target) {
            let counter = { value: 0 };
            gsap.to(counter, {
                value: target,
                duration: 2,
                ease: "power1.out",
                onUpdate: function () {
                    let element = document.getElementById(elementId);
                    if (element) {
                        element.innerText = Math.floor(counter.value);
                    }
                }
            });
        }

        animateCounter("projectsCount", 5);
        animateCounter("tasksCount", 20);
        animateCounter("usersCount", 15);
    };
</script>

</body>
</html>
