<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Project List</title>
</head>
<body>
<h1>All Projects</h1>

<c:if test="${not empty successMessage}">
    <p style="color: green">${successMessage}</p>
</c:if>
<c:if test="${not empty errorMessage}">
    <p style="color: red">${errorMessage}</p>
</c:if>

<table border="1">
    <tr>
        <th>Project Name</th>
        <th>Description</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Budget</th>
        <th>Actions</th>
    </tr>

    <c:forEach var="project" items="${projects}">
        <tr>
            <td>${project.nom}</td>
            <td>${project.description}</td>
            <td>${project.dateDebut}</td>
            <td>${project.dateFin}</td>
            <td>${project.budget}</td>
            <td>
                <a href="ViewProjetServlet?id=${project.id}">View</a> |
                <a href="UpdateProjetServlet?id=${project.id}">Update</a> |
                <a href="DeleteProjetServlet?id=${project.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>

<h2>Add a New Project</h2>
<form action="AddProjetServlet" method="post">
    <%--@declare id="budget"--%><%--@declare id="nom"--%><%--@declare id="description"--%><%--@declare id="datedebut"--%><%--@declare id="datefin"--%><label for="nom">Project Name:</label>
    <input type="text" name="nom" required/><br/>

    <label for="description">Description:</label>
    <textarea name="description" required></textarea><br/>

    <label for="dateDebut">Start Date:</label>
    <input type="date" name="dateDebut" required/><br/>

    <label for="dateFin">End Date:</label>
    <input type="date" name="dateFin" required/><br/>

    <label for="budget">Budget:</label>
    <input type="number" step="0.01" name="budget" required/><br/>

    <button type="submit">Add Project</button>
</form>
</body>
</html>
