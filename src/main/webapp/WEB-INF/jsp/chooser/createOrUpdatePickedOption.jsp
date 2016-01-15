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

    <h2>Olá, Escolha o usuário, data e restaurante desejados</h2>
    
     <form:form modelAttribute="chooser"
               class="form-horizontal">
               
         <div class="control-group" id="ownLastName">
            <petclinic:selectField name="owner.lastName" label="Funcionarios " names="${employees}" size="5"/>
         </div>      
         
         <petclinic:inputField label="Dia do Almoco" name="pickedDate"/>
         
         <div class="control-group" id="restMainName">
            <petclinic:selectField name="restaurant.mainName" label="Restaurantes " names="${restaurants}" size="5"/>
         </div>  
                              
	     <div class="form-actions">
	     	<button type="submit">Finalizar</button>
	     </div>    
	     
     </form:form>   

    <jsp:include page="../fragments/footer.jsp"/>
</div>
</body>

</html>
