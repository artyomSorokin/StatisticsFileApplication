<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>FileHandledList</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/tableStyle.css" />
</head>
<body>
<form method="post">
    Put your text inside
    <textarea name="text" rows="10" cols="40"></textarea>
    <input type="submit" value="Query" />
</form>
<p><a href="/fileStatistics">On the Main page</a></p>
<br/>
<table border="1">
    <tr>
        <th class="col1">File Name</th>
        <th class="col1">File Length</th>
        <th class="col1">Longest Word</th>
        <th class="col1">Shortest Word</th>
        <th class="col1">Average Word Length</th>
    </tr>
    <c:forEach items="${fileEntities}" var="statisticsFile">
        <tr>
            <td><a href="/lineStatistics?filename=${statisticsFile.query}">${statisticsFile.fileName}</a></td>
            <td>${statisticsFile.fileLength}</td>
            <td>${statisticsFile.longestWord}</td>
            <td>${statisticsFile.shortestWord}</td>
            <td>${statisticsFile.averageWordLength}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
