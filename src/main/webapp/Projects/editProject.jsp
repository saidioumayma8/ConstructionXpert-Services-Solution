<%@ page import="Models.Project" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty errorMessage}">
    <div class="error-message">${errorMessage}</div>
</c:if>

<h2>Modify Project</h2>
<form action="modifyProject" method="post">
    <input type="hidden" name="id" value="${project.id}" />
    <label for="nom">Project Name:</label>
    <input type="text" id="nom" name="nom" value="${project.nom}" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required>${project.description}</textarea><br><br>

    <label for="dateDebut">Start Date:</label>
    <input type="date" id="dateDebut" name="dateDebut" value="${project.dateDebut}" required><br><br>

    <label for="dateFin">End Date:</label>
    <input type="date" id="dateFin" name="dateFin" value="${project.dateFin}" required><br><br>

    <label for="budget">Budget:</label>
    <input type="number" id="budget" name="budget" value="${project.budget}" required><br><br>

    <button type="submit">Update Project</button>
</form>
