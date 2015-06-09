<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<jsp:include page="../common/header.jsp"/>
<div class="container">
    <div class="row">
        <div class="alert alert-dismissable alert-danger">
            <h3>
                <strong> Une erreur est survenue : </strong>
                <c:out value="${message}"/></h3>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"/>