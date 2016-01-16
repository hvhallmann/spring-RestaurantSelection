<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

   <h2>Informação Funcionário</h2>

    <table class="table table-striped" style="width:600px;">
        <tr>
            <th>Nome</th>
            <td><b><c:out value="${owner.firstName} ${owner.lastName}"/></b></td>
        </tr>
        <tr>
            <th>Endereço</th>
            <td><c:out value="${owner.address}"/></td>
        </tr>
        <tr>
            <th>Cidade</th>
            <td><c:out value="${owner.city}"/></td>
        </tr>
        <tr>
            <th>Telefone</th>
            <td><c:out value="${owner.telephone}"/></td>
        </tr>
        <tr>
            <td>
                <spring:url value="{ownerId}/edit.html" var="editUrl">
                    <spring:param name="ownerId" value="${owner.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editUrl)}" class="btn btn-info">Editar</a></td>
            <td>
        </tr>
    </table>

    <jsp:include page="../fragments/footer.jsp"/>


</div>

</body>

</html>
