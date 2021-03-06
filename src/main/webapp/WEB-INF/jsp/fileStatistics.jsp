<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>FileHandledList</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/tableStyle.css" />
</head>
<body>
<form method="post">
    Enter absolute path to file or directory
    <input type="text" name="path" value="${path}" />
    <input type="submit" value="Query" />
</form>
<br/>
<p><a href="/userTextStatistics">Send your text to see statistics</a></p>
<br/>
<ul>
<c:forEach items="${filters}" var="filter">
       <li><a href="${filter.href}">${filter.name}</a></li>
</c:forEach>
</ul>
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
