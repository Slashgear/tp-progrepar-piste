<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <div class="row">
        <h1><c:out value="${apprenant.prenomapprenant}"/> <c:out value="${apprenant.nomapprenant}"/></h1>


        <div class="col-md-12">
            <div class="well">
                <h2>Résumé</h2>
            </div>
        </div>
        <c:if test="${not empty showinsc}">
            <div class="col-md-6">
                <div class="well">
                    <form class="form-horizontal" method="post" action="/apprenant/inscrire">
                        <fieldset>
                            <legend>Inscrire apprenant à un jeu</legend>
                            <div class="form-group">
                                <label for="select" class="col-lg-2 control-label">Jeux disponibles</label>


                                <div class="col-lg-10">
                                    <select class="form-control" id="select" name="idJeu" required>
                                        <c:forEach items="${jeux}" var="jeu">
                                            <option value="<c:out value="${jeu.numjeu}"/>"><c:out
                                                    value="${jeu.libellejeu}"/></option>
                                        </c:forEach>
                                    </select>
                                </div>

                            </div>
                            <input type="hidden" name="idApprenant" value="${apprenant.numapprenant}"/>

                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary">Valider</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </c:if>

    </div>
</div>
<%@include file="../common/footer.jsp" %>
