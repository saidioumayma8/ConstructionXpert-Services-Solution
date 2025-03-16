<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Project</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 p-8">
<h1 class="text-3xl font-bold text-orange-600 mb-6">🏗 Add New Project</h1>

<form action="ProjectServlet" method="post" class="bg-white p-6 shadow-lg rounded-lg">
    <div class="mb-4">
        <label class="block text-gray-700 font-semibold">Project Name</label>
        <input type="text" name="name" class="w-full px-4 py-2 border rounded-lg" required>
    </div>

    <div class="mb-4">
        <label class="block text-gray-700 font-semibold">Description</label>
        <textarea name="description" class="w-full px-4 py-2 border rounded-lg" required></textarea>
    </div>

    <div class="mb-4">
        <label class="block text-gray-700 font-semibold">Start Date</label>
        <input type="date" name="startDate" class="w-full px-4 py-2 border rounded-lg" required>
    </div>

    <div class="mb-4">
        <label class="block text-gray-700 font-semibold">End Date</label>
        <input type="date" name="endDate" class="w-full px-4 py-2 border rounded-lg" required>
    </div>

    <div class="mb-4">
        <label class="block text-gray-700 font-semibold">Budget ($)</label>
        <input type="number" name="budget" class="w-full px-4 py-2 border rounded-lg" step="0.01" required>
    </div>

    <button type="submit" class="bg-orange-500 text-white px-6 py-2 rounded-lg">Add Project</button>
</form>
</body>
</html>
