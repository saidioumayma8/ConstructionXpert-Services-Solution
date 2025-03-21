<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

        <h1 class="text-3xl font-bold text-gray-900 dark:text-white animate-fade-in">All Projects</h1>
        <p class="text-gray-600 dark:text-gray-300 mt-2">Here is a list of all the projects.</p>

        <!-- Projects List -->
        <div class="mt-6">
            <p class="text-lg font-semibold text-gray-700 dark:text-gray-200">Total Projects: ${projects.size}</p>

            <div class="space-y-4 mt-4">
                <c:if test="${not empty projects}">
                    <c:forEach var="project" items="${projects}">
                        <div class="bg-white dark:bg-gray-800 p-6 rounded-lg shadow-md">
                            <h3 class="text-2xl font-semibold text-gray-900 dark:text-gray-100">${project.nom}</h3>
                            <p class="text-gray-700 dark:text-gray-300">${project.description}</p>
                            <div class="mt-4 text-sm text-gray-600 dark:text-gray-400">
                                <p>Start Date: ${project.dateDebut}</p>
                                <p>End Date: ${project.dateFin}</p>
                                <p>Budget: ${project.budget}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
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

    /* Stats Card */
    .stats-card {
        background: white;
        padding: 20px;
        border-radius: 10px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        text-align: center;
        transform: scale(0.9);
        opacity: 0;
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
