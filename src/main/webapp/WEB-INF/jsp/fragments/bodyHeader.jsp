<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/resources/images/banner-graphic_db.png" var="banner"/>
<img src="${banner}"/>

<div class="navbar" style="width: 601px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 120px;"><a href="<spring:url value="/" htmlEscape="true" />"><i class="icon-home"></i>
                Home</a></li>
            <li style="width: 150px;"><a href="<spring:url value="/owners/find.html" htmlEscape="true" />"><i
                    class="icon-search"></i> Funcionarios</a></li>
            <li style="width: 160px;"><a href="<spring:url value="/vets.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i> Restaurantes</a></li>
            <li style="width: 160px;"><a href="<spring:url value="/escolher.html" htmlEscape="true" />"><i
                    class="icon-search"></i> Escolher</a></li>
            <li style="width: 160px;"><a href="<spring:url value="/resultado.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i> Resultado</a></li>        
            <!-- <li style="width: 110px;"><a href="<spring:url value="/oups.html" htmlEscape="true" />"
                                         title="trigger a RuntimeException to see how it is handled"><i
                    class="icon-warning-sign"></i> Error</a></li>  -->
        </ul>
    </div>
</div>
	
