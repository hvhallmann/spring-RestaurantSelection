<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>

<body>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>Resultado Ganhador</h2>
	
    <datatables:table id="results" data="${selections}" row="sumVotes" theme="bootstrap2" cssClass="table table-striped"
                      pageable="false" info="false">
        <datatables:column title="Nome">
            <c:out value="${sumVotes.restaurantName} "></c:out>
        </datatables:column>
        <datatables:column title="Date"  sortInitDirection="desc" >
            <c:out value="${sumVotes.lunchTime} "></c:out>
        </datatables:column>
        <datatables:column title="Numero Votos">
            <c:out value="${sumVotes.totalVotes} "></c:out>
        </datatables:column>
        
    </datatables:table>
	
	* obs.: Os resultados para hoje somente são liberados após as 11h
	
    <jsp:include page="../fragments/footer.jsp"/>

</div>

</body>

</html>
