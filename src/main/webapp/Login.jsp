<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.test" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel='stylesheet' href='Styles/main.css'>
<title>Connexion Abonné</title>
</head>
<body>
    <div class="container">
    <fmt:message key="VueAbonne" var="VueAbonne" />  
       <h2 align="center">${VueAbonne}</h2>
        <form class="form" id="login" method="post" action="${pageContext.request.contextPath}/login">
            <div class="form__message form__message--error"></div>
            <div class="form__input-group">
            <fmt:message key="Nom" var="Nom" /> 
                <input type="text" class="form__input" autofocus placeholder="${Nom}" name="login"  value='${login}' required>
                <div class="form__input-error-message"></div>
            </div>
            <div class="form__input-group">
             <fmt:message key="Mdp" var="Mdp" /> 
                <input type="password" class="form__input" autofocus placeholder="${Mdp}" name="password" value='${password}'required>
                <div class="form__input-error-message"></div>
            </div>
            <fmt:message key="Seconnecter" var="Seconnecter" /> 
            <button class="form__button" type="submit" name="se_connecter" name="btn" style="background-color: blue;">${Seconnecter}</button>
            <p class="form__text">
            </p>
        </form>
        <p class="form__text">
         </p>
         <fmt:message key="Inscription" var="Inscription" />  
         <a href="inscription" style="text-decoration:none;color:green;text-size:bold" name="hrefinscription">${Inscription}</a>
         <fmt:message key="MessageEreurtest" var="MessageEreurtest" />  
        <c:set var = "messageEreur" scope = "session" value = "${messageEreur}"/>
        <c:if test = "${messageEreur == 1}">
         <p Style="color:red"><c:out value = "${MessageEreurtest}"/><p>
      </c:if>
    </div>
</body>
</html>

















