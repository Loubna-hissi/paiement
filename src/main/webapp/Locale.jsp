<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.test" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<link rel='stylesheet' href='Styles/main.css'>
<title>Language</title>
<% 	 response.setHeader("Cache-Control","no-cache");
	  response.setHeader("Cache-Control","no-store");
	  response.setHeader("Pragma","no-cache");
	  response.setDateHeader ("Expires", 0);%>
</head>
<body>
<div class="container">
   <fmt:message key="languageMassage" var="languageMassage" />  
    <h1 align="center"> ${languageMassage}</h1>
	<form>
		<select id="language" name="language" onchange="submit()" class="form__input">
		    <option value="fr" ${language == 'fr' ? 'selected' : ''}>Français</option>
			<option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
			
		</select>
		
	</form>
	<form method="post" action="Accueil.jsp">
     	<h1 Style="display:none">${language}</h1>
     	<p class="form__text"> </p>
     	<fmt:message key="buttonValue" var="buttonValue" />
		 <button class="form__button" type="submit" name="se_connecter" style="background:red">${buttonValue}</button>
	</form>
	</div>
</body>
</html>