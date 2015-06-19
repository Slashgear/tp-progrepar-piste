<%@include file="../common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/floatingactionbutton.css"/>" rel="stylesheet"/>

<div class="container">
    <div class="row">
        <c:if test="${not empty isDeleted}">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>${isDeleted}</strong>
            </div>
        </c:if>
        <c:if test="${not empty success}">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert">×</button>
                <strong>${success}</strong>
            </div>
        </c:if>
        <c:forEach items="${listeApprenants}" var="apprenant">
            <div class="col-md-4 col-sm-6">
                <div class="well infobox">
                    <h2><c:out value="${apprenant.prenomapprenant}"/> <c:out value="${apprenant.nomapprenant}"/></h2>

                    <div class="btn-group btn-group-justified">
                        <a href="/apprenant/${apprenant.numapprenant}"
                           class="btn btn-sm btn-flat btn-info btn-material-indigo"
                           title="Détails"><i
                                class="mdi-content-add-circle-outline"></i></a>
                        <a href="/apprenant/modifier/${apprenant.numapprenant}" class="btn btn-sm btn-flat btn-primary"
                           title="Modifier"><i
                                class="mdi-content-create"></i></a>
                        <a class="btn btn-sm  btn-flat btn-danger"
                           title="Supprimer" data-toggle="modal" data-target="#modal_confirm"
                           data-apprenant="<c:out value="${apprenant.prenomapprenant}"/> <c:out value="${apprenant.nomapprenant}"/>"
                           data-id="<c:out value="${apprenant.numapprenant}"/>">
                            <i class="mdi-content-clear"></i></a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<div id="modal_confirm" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title">Etes-vous sur de vouloir supprimer cet Apprenant ?</h4>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Annuler</button>
                <a id="ModalSuppr" class="btn btn-danger">Supprimer</a>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
    $('#modal_confirm').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget)
        var apprenant = button.data('apprenant')
        var numApprenant = button.data('id')
        var modal = $(this)
        modal.find('.modal-title').html('Etes-vous sur de vouloir supprimer <strong>' + apprenant + '</strong> de la liste des apprenants.')
        $('#ModalSuppr').attr('href', '/apprenant/suppr/' + numApprenant)
    })
</script>
<footer class="footer">
    <div class="container">
        <div class="pull-right">
            <%@include file="/vues/common/floatingactionbutton.jsp"%>
        </div>
    </div>
</footer>
<%@include file="../common/footer.jsp" %>
