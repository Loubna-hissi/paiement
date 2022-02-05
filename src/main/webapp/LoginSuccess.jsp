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
<title>Vue Abonné</title>
</head>
<body>
    <div class="container">
     <fmt:message key="BienvenuAbonne" var="BienvenuAbonne" /> 
       <h2 align="center" style="color:red">${BienvenuAbonne}</h2>
        <form class="form" id="paiement" method="post" action="paiement">
            <div class="form__message form__message--error"></div>
            <div class="form__input-group">
            <fmt:message key="BienvenuMr_Ms" var="BienvenuMr_Ms" /> 
                <label>${BienvenuMr_Ms} : ${connectedUser.getLogin()}</label>
                <div class="form__input-error-message"></div>
            </div>
            <div class="form__input-group">
            <fmt:message key="Numero" var="Numero" /> 
                <label>${Numero} : ${connectedUser.getNumero()}</label>
                <div class="form__input-error-message"></div>
            </div>
            <div class="form__input-group">
            <fmt:message key="Montant" var="Montant" /> 
                <label>${Montant} :  ${connectedUser.getMontant()}</label>
                <div class="form__input-error-message"></div>
            </div>
            <div class="form__input-group">
             <fmt:message key="Ndc" var="Ndc" /> 
                <input type="text" class="form__input" autofocus placeholder="${Ndc}" name="Ncarte" value='${Ncarte}' required>
                <div class="form__input-error-message"></div>
            </div>
            <div class="form__input-group">
            <fmt:message key="DE" var="DE" /> 
                <input type="date" class="form__input" autofocus placeholder="${DE}" name="dateExpiration" value='${DateExpi}' required>
                <div class="form__input-error-message"></div>
            </div>
             <div class="form__input-group">
             <fmt:message key="TDC" var="TDC" /> 
                <input type="text" class="form__input" autofocus placeholder="${TDC }" name="dernierChiff" value='${DernierChiff}' required>
                <div class="form__input-error-message"></div>
            </div>
             <fmt:message key="buttonValue" var="buttonValue" /> 
            <button class="form__button" type="submit" name="validerbtn"style="background:green">${buttonValue}</button>
            <p class="form__text">
            </p>
        </form>
        <fmt:message key="MessageEreurNCarte2" var="MessageEreurNCarte2" /> 
        <fmt:message key="MessageEreurNCarte3" var="MessageEreurNCarte3" /> 
        <fmt:message key="MessageEreurNCarte5" var="MessageEreurNCarte5" />  
        <fmt:message key="MessageEreurNCarte6" var="MessageEreurNCarte6" /> 
        <fmt:message key="MessageEreurNCarte7" var="MessageEreurNCarte7" /> 
        <c:set var = "ErreurPaiement" scope = "session" value = "${ErreurPaiement}"/>
        <c:if test = "${ErreurPaiement == 2}">
         <p Style="color:red"><c:out value = "${MessageEreurNCarte2}"/><p>
        </c:if>
        <c:if test = "${ErreurPaiement == 3}">
         <p Style="color:red"><c:out value = "${MessageEreurNCarte3}"/><p>
        </c:if>
       <c:if test = "${ErreurPaiement == 5}">
         <p Style="color:red"><c:out value = "${MessageEreurNCarte5}"/><p>
        </c:if>
        <c:if test = "${ErreurPaiement == 6}">
         <p Style="color:red"><c:out value = "${MessageEreurNCarte6}"/><p>
        </c:if>
        <c:if test = "${ErreurPaiement == 7}">
         <p Style="color:red"><c:out value = "${MessageEreurNCarte7}"/><p>
        </c:if>
    </div>
</body>
</html>

















