<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language }" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources.test" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Styles/main.css">
<title>Acceuil</title>
</head>
<body>

    <div class="container">
       <fmt:message key="bienvenuValue" var="bienvenuValue" />
        <h2>${bienvenuValue}</h2>
        <fmt:message key="connectionAutant" var="connectionAutant" />
        <h1 class="form__input-error-message">${connectionAutant}</h1>
            <div class="form__message form__message--error"></div>
            
             <form method="post" action="loginAgent">
              <fmt:message key="Agent" var="Agent" />
            <button class="form__button" type="submit" name="se_connecter" style="background-color: blue;">${Agent}</button>        
            <p class="form__text">
            </p>
            </form>
            <div class="form__message form__message--error"></div>
               <form method="post" action="login">
               <fmt:message key="Abonne" var="Abonne" />
            <button class="form__button" type="submit" name="Abonnebtn" style="background-color: red;">${Abonne}</button>
            <p class="form__text">
            </p>
            </form>
    </div>
</body>
</html>