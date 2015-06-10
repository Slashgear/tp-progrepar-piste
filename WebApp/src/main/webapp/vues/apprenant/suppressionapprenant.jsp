<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../common/header.jsp" %>
<div class="container">
    <div class="row">
        <form class="form-horizontal">
            <fieldset>
                <legend>Suppression d'un Apprenant</legend>
                <div class="form-group">
                    <label for="select" class="col-lg-2 control-label">Apprenant</label>

                    <div class="col-lg-10">
                        <select class="form-control" id="select">
                            <c:forEach items="${listeApprenants}" var="apprenant">
                                <option>N° ${apprenant.numapprenant}
                                    - ${apprenant.prenomapprenant} ${apprenant.nomapprenant}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button class="btn btn-default">Annuler</button>
                        <button type="submit" class="btn btn-primary">Supprimer</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="../common/footer.jsp" %>