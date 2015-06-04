<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title> Bonjour : ${personne} </title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Numaction</th>
        <th>Action précédente</th>
        <th>Libellé</th>
        <th>Score minimum</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ListeIndicateur}" var="action">
        <tr>
            <td><c:out value="${action.numaction}"/></td>
            <td><c:out value="${action.actNumaction}"/></td>
            <td><c:out value="${action.libaction}"/></td>
            <td><c:out value="${action.scoremin}"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>