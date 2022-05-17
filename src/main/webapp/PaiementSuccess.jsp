<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.test"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="ISO-8859-1">
<link rel='stylesheet' href='Styles/main.css'>
<title>Succès</title>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
    
	<div class="container">
	<%if(request.getParameter("logout") != null ){ 
	      session.invalidate();
	      } %>
     ${connectedUser.getLogin() }
	 <fmt:message key="paiementsucces" var="paiementsucces" /> 
		<h1 class="form__input-error-message">${paiementsucces}</h1>
		<div style="display:flex">
			<form action="pdf" method="post">
			 <fmt:message key="pdf" var="pdf" /> 
				<input type='submit' value='${pdf}' class="form__button" style="margin-left:5px">
			</form>
			<form  method="post" action="Deconnexion">
		
			<fmt:message key="deconnexion" var="deconnexion" /> 
				<input type='submit' value='${deconnexion}' name="logout" class="form__button" style="margin-left:8px;background:red ">
			</form>
		</div>
	</div>
</body>
</html>