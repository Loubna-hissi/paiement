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
    <fmt:message key="VueAbonneinscription" var="VueAbonneinscription" />  
       <h2 align="center">${VueAbonneinscription}</h2>
        <form class="form" id="login" method="post" action="inscription">
            <div class="form__message form__message--error"></div>
            <div class="form__input-group">
            <fmt:message key="Nom" var="Nom" /> 
                <input type="text" class="form__input" autofocus placeholder="${Nom}" name="name"  value='' required>
                <div class="form__input-error-message"></div>
            </div>
             <div class="form__input-group">
            <fmt:message key="mail" var="mail" /> 
                <input type="email" class="form__input" autofocus placeholder="${mail}" name="mail"  value='' required>
                <div class="form__input-error-message"></div>
            </div>
             <div class="form__input-group">
            <fmt:message key="NumeroTele" var="NumeroTele" /> 
                <input type="text" class="form__input" autofocus placeholder="${NumeroTele}" name="numero"  value='' required>
                <div class="form__input-error-message"></div>
            </div>
             <div class="form__input-group">
            <fmt:message key="Mdp" var="Mdp" /> 
                <input type="password" class="form__input" autofocus placeholder="${Mdp}" name="password1"  value='' required>
                <div class="form__input-error-message"></div>
            </div>
            <div class="form__input-group">
             <fmt:message key="Mdpconfirme" var="Mdpconfirme" /> 
                <input type="password" class="form__input" autofocus placeholder="${Mdpconfirme}" name="passwordconf" value=""required>
                <div class="form__input-error-message"></div>
            </div>
            <fmt:message key="inscrit" var="inscrit"/> 
            <button class="form__button" type="submit" name="se_connecter" name="inscrit" style="background-color: green;">${inscrit}</button>
            <p class="form__text">
            </p>
        </form>
        <p class="form__text">
         </p>
         <fmt:message key="login" var="login" />  
         <a href="login" style="text-decoration:none;color:green;text-size:bold">${login}</a>
         <fmt:message key="existeuser" var="existeuser" /> 
         <fmt:message key="passwordWrong" var="passwordWrong"/>  
        <c:set var = "messageInscription" scope = "session" value = "${messageInscription}"/>
        <c:if test = "${messageInscription == 1}">
         <p Style="color:red"><c:out value = "${existeuser}"/><p>
        </c:if>
        <c:if test = "${messageInscription == 2}">
         <p Style="color:red"><c:out value = "${passwordWrong}"/><p>
        </c:if>
    </div>
</body>
</html>

















