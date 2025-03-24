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

<form action="modifyProject" method="post">
    <input type="hidden" name="id" value="<%= project.getId() %>" />

    <label for="nom">Project Name:</label>
    <input type="text" id="nom" name="nom" value="<%= project.getNom() %>" required><br><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required><%= project.getDescription() %></textarea><br><br>

    <label for="dateDebut">Start Date:</label>
    <input type="date" id="dateDebut" name="dateDebut" value="<%= project.getDateDebut() %>" required><br><br>

    <label for="dateFin">End Date:</label>
    <input type="date" id="dateFin" name="dateFin" value="<%= project.getDateFin() %>" required><br><br>

    <label for="budget">Budget:</label>
    <input type="number" id="budget" name="budget" value="<%= project.getBudget() %>" required><br><br>

    <button type="submit">Update Project</button>
</form>
