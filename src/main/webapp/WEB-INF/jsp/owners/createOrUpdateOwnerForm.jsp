<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>
        <c:if test="${owner['new']}">Novo </c:if> Funcionário
    </h2>
    <form:form modelAttribute="owner" class="form-horizontal" id="add-owner-form">
        <petclinic:inputField label="Primeiro Nome" name="firstName"/>
        <petclinic:inputField label="Último Nome" name="lastName"/>
        <petclinic:inputField label="Login" name="login"/>
        <petclinic:inputField label="password" name="password"/>
        <petclinic:inputField label="Endereço" name="address"/>
        <petclinic:inputField label="Cidade" name="city"/>
        <petclinic:inputField label="Telefone" name="telephone"/>        

        <div class="form-actions">
            <c:choose>
                <c:when test="${owner['new']}">
                    <button type="submit">Add Funcionário</button>
                </c:when>
                <c:otherwise>
                    <button type="submit">Atualizar Funcionário</button>
                </c:otherwise>
            </c:choose>
        </div>
    </form:form>
</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>

</html>
