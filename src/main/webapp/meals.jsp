<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Meal list</h1>
    <table>
        <tr>
            <th>DateTime</th>
            <th>Description</th>
            <th>Calories</th>
            <th>Exceed</th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <tr style="color: ${meal.exceed ? 'red' : 'green'}">
                <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td>${meal.exceed}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
