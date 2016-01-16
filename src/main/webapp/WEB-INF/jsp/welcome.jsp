<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html lang="en">

<jsp:include page="fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="fragments/bodyHeader.jsp"/>
    <spring:url value="/resources/images/fotografia-massa.jpg" htmlEscape="true" var="restImage"/>
    <img src="${restImage}"/>
	<h3>Obrigado por escolher o melhor almoço!</h3>
    <jsp:include page="fragments/footer.jsp"/>

</div>
</body>

</html>
