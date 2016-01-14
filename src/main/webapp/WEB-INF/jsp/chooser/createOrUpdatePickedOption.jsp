<!DOCTYPE html>

<%@ page session="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<html lang="en">

<jsp:include page="../fragments/staticFiles.jsp"/>
<body>

<script>
    $(function () {
        $("#pickedDate").datepicker({dateFormat: 'yy/mm/dd'});
    });
</script>
<div class="container">
    <jsp:include page="../fragments/bodyHeader.jsp"/>

    <h2>escolha restaurante</h2>
    
     <form:form modelAttribute="chooser"
               class="form-horizontal">
               
         <div class="control-group">
            <petclinic:selectField name="owner.lastName" label="Funcionarios " names="${employees}" size="5"/>
         </div>      
         
         <petclinic:inputField label="Dia do Almoco" name="pickedDate"/>
         
         <div class="control-group">
            <petclinic:selectField name="restaurant.mainName" label="Restaurantes " names="${restaurants}" size="5"/>
         </div>  
                              
	     <div class="form-actions">
	     	<button type="submit">Finalizar</button>
	     </div>    
	     
     </form:form>   

	<!-- 
    <datatables:table id="vets" data="${vets.vetList}" row="vet" theme="bootstrap2" cssClass="table table-striped"
                      pageable="false" info="false">
        <datatables:column title="Name">
            <c:out value="${vet.firstName} ${vet.lastName}"></c:out>
        </datatables:column>
        <datatables:column title="Specialties">
            <c:forEach var="specialty" items="${vet.specialties}">
                <c:out value="${specialty.name}"/>
            </c:forEach>
            <c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
        </datatables:column>
    </datatables:table>

	

    <table class="table-buttons">
        <tr>
            <td>
                <a href="<spring:url value="/vets.xml" htmlEscape="true" />">View as XML</a>
            </td>
            <td>
                <a href="<spring:url value="/vets.json" htmlEscape="true" />">View as JSon</a>
            </td>
        </tr>
    </table>
 -->
    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
