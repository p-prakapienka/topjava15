<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Meal list</h1>
    <form method="post" action="meals?action=${meal.id ne 0 ? 'update' : 'create'}">
        <input type="hidden" name="id" value="${meal.id}">
        <input type="text" name="description" value="${meal.description}">
        <input type="number" name="calories" value="${meal.calories}" step="1">
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}">
        <input type="submit" value="${meal.id ne 0 ? 'update' : 'create'}">
    </form>
    <table>
        <tr>
            <th>DateTime</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Exceed</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr style="color: ${meal.exceed ? 'red' : 'green'}">
                <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.exceed}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
