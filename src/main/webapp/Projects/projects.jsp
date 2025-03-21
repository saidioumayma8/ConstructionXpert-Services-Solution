<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>All Projects</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.0.2/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>
<div class="container mx-auto mt-10">
    <h1 class="text-3xl font-bold mb-5">All Projects</h1>

    <!-- Display Projects -->
    <c:if test="${not empty projects}">
        <table class="min-w-full table-auto border-collapse border border-gray-200">
            <thead>
            <tr class="bg-gray-100">
                <th class="px-4 py-2 text-left border border-gray-200">Project Name</th>
                <th class="px-4 py-2 text-left border border-gray-200">Description</th>
                <th class="px-4 py-2 text-left border border-gray-200">Start Date</th>
                <th class="px-4 py-2 text-left border border-gray-200">End Date</th>
                <th class="px-4 py-2 text-left border border-gray-200">Budget</th>
            </tr>
            </thead>
            <tbody>
            <!-- Iterate over the projects list -->
            <c:forEach var="project" items="${projects}">
                <tr class="border-t border-gray-200">
                    <td class="px-4 py-2">${project.nom}</td>
                    <td class="px-4 py-2">${project.description}</td>
                    <td class="px-4 py-2">${project.dateDebut}</td>
                    <td class="px-4 py-2">${project.dateFin}</td>
                    <td class="px-4 py-2">${project.budget}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <!-- If no projects, show this message -->
    <c:if test="${empty projects}">
        <div class="text-gray-500">No projects to display</div>
    </c:if>
</div>
</body>
</html>
