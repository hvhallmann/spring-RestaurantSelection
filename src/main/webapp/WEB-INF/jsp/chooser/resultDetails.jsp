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

    <h2>Resultado Ganhador</h2>
	
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Nome</th>
            <th>Para o Dia</th>
            <th>Com n Votos</th>
        </tr>
        </thead>
        <tr>
            <td><c:out value="${oneChoice.restaurant.mainName}"/></td>
            <td><joda:format value="${oneChoice.pickedDate}" pattern="yyyy/MM/dd"/></td>
            <td><c:out value="${oneChoice.description}"/></td>
        </tr>
    </table>
	

    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
