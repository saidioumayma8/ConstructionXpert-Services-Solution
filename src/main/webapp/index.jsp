
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
            <a href="projects.jsp" class="sidebar-link">Projects</a>
            <a href="Tasks.jsp" class="sidebar-link">Tasks</a>
            <a href="Users.jsp" class="sidebar-link">Users</a>
            <a href="Reports.jsp" class="sidebar-link">Reports</a>
            <a href="Settings.jsp" class="sidebar-link">Settings</a>
        </nav>
    </aside>

    <!-- Main Content -->
    <main class="flex-1 p-8">
        <button id="toggleSidebar" class=" px-4 py-2 rounded mb-4">Toggle Sidebar</button>

        <h1 class="text-3xl font-bold text-gray-900 dark:text-white animate-fade-in">Welcome, Admin</h1>
        <p class="text-gray-600 dark:text-gray-300 mt-2">Here’s an overview of your projects and tasks.</p>

        <!-- Animated Statistics -->
        <div class="grid grid-cols-3 gap-6 mt-6">
            <div class="stats-card">
                <h3 class="text-lg font-bold dark:text-gray-200">Ongoing Projects</h3>
                <p id="projectsCount" class="text-4xl font-semibold">0</p>
            </div>
            <div class="stats-card">
                <h3 class="text-lg font-bold dark:text-gray-200">Tasks Completed</h3>
                <p id="tasksCount" class="text-4xl font-semibold">0</p>
            </div>
            <div class="stats-card">
                <h3 class="text-lg font-bold dark:text-gray-200">Users Registered</h3>
                <p id="usersCount" class="text-4xl font-semibold">0</p>
            </div>
        </div>

        <!-- Quick Actions -->
        <div class="mt-6">
            <h2 class="text-xl font-bold dark:text-gray-200">Quick Actions</h2>
            <div class="mt-3 space-x-3">
                <button><a href="./Projects/addProject.jsp">Add Project</a></button>
                <button><a href="AddTask.jsp">Add Task</a></button>
                <button><a href="GenerateReport.jsp">Generate Report</a></button>
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
