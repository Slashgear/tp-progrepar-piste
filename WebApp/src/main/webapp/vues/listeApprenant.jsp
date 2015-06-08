<%@ page language="java" contentType="text/html; charset=ISO-8859-1" isELIgnored="false"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row">
        <table class="table table-striped table-hover ">
            <thead>
            <tr>
                <th>#</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Action</th>
            </thead>
            <tbody>

            <c:forEach items="${listeApprenants}" var="apprenant">
                <tr>
                    <td>${apprenant.numapprenant}</td>
                    <td>${apprenant.nomapprenant}</td>
                    <td>${apprenant.prenomapprenant}</td>
                    <td>
                        <a href="/apprenant/modifier/${apprenant.numapprenant}"><i class="mdi-content-create"></i></a>
                        <a href="/apprenant/suppr/${apprenant.numapprenant}"><i class="mdi-content-clear"></i></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
