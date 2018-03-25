<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Добавить участников</title>
    <spring:url value="/static/css/style.css" var="mainCss"/>
    <link rel="stylesheet" href="${mainCss}">
</head>
<body>
 <form action="/tournament/createWithDefaultParticipants">
     <textarea id="participants-field" title="participants">Введите участников, разделяя переносом строки</textarea>
    <br> <input type="submit" value="Создать турнир">
 </form>
</body>
</html>
