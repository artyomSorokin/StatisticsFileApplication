<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Line statistics</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/tableStyle.css" />
</head>
<body>
<table border="1">
    <tr>
        <th class="col1">Text Line</th>
        <th class="col1">Line Length</th>
        <th class="col1">Longest Word</th>
        <th class="col1">Shortest Word</th>
        <th class="col1">Average Word Length</th>
    </tr>
    <c:forEach items="${lineEntities}" var="statisticsLine">
        <tr>
            <td>${statisticsLine.line}</td>
            <td>${statisticsLine.lineLength}</td>
            <td>${statisticsLine.longestWord}</td>
            <td>${statisticsLine.shortestWord}</td>
            <td>${statisticsLine.averageWordLength}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
