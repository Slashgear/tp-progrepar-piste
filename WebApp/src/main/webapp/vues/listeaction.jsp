<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
         pageEncoding="UTF-8" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <table class="table table-striped table-hover ">
            <thead>
            <tr>
                <th>Numaction</th>
                <th>Action précédente</th>
                <th>Libellé</th>
                <th>Score minimum</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listeActions}" var="action">
                <tr>
                    <td><a href=<c:out value="action/${action.numaction}"/>><c:out value="${action.numaction}"/></a>
                    </td>
                    <td><a href=<c:out value="action/${action.actNumaction}"/>><c:out
                            value="${action.actNumaction}"/></a></td>
                    <td><c:out value="${action.libaction}"/></td>
                    <td><c:out value="${action.scoremin}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>