<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <c:forEach items="${jeux}" var="jeu">
            <div class="col-lg-3 col-md-4 col-sm-6">
                <a href=<c:out value="jeu/${jeu.numjeu}"/>>
                    <div class="well">
                        #<c:out value="${jeu.numjeu}"/> : <c:out value="${jeu.libellejeu}"/>
                    </div>
                </a>
            </div>
        </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>