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
<link rel="stylesheet" href="Styles/main2.css">
<title>Vue Agent</title>
</head>
<body>
       <div class="container">
   <fmt:message key="BienvenuAgent" var="BienvenuAgent" /> 
       <h2 align="center" style="color:red">${BienvenuAgent}</h2>
          <table>
               <tr>
                 <fmt:message key="Nom_Prenom" var="Nom_Prenom" /> 
                   <td>${Nom_Prenom}</td>
                 <fmt:message key="Numero" var="Numero" /> 
                   <td>${Numero}</td>
                   <td>Email</td>
                 <fmt:message key="Montant" var="Montant" /> 
                   <td>${Montant}</td>
                 <fmt:message key="statut" var="statut" /> 
                   <td>${statut}</td>
               </tr>

                  <c:forEach items="${consultUser}" var="news">
                   <tr>
                     <td><c:out value="${news.getLogin()}"/></td>
                     <td><c:out value="${news.getNumero()}"/></td>
                     <td><c:out value="${news.getEmail()}"/></td>
                     <td><c:out value="${news.getMontant()}"/></td>
                     <fmt:message key="statutPaye" var="statutPaye" /> 
                     <fmt:message key="nonstatutPaye" var="nonstatutPaye" /> 
                     
                     <td>
                       <c:set var = "statut"  value = "${news.getStatus()}"/>
                         <c:if test = "${statut == 0}">
                           <c:out value = "${nonstatutPaye}"/>
                         </c:if> 
                         <c:if test = "${statut == 1}">
                           <c:out value = "${statutPaye}"/>
                         </c:if> 
                     </td>
                  </tr>
                  </c:forEach>
          
          </table> 
      </div>
</body>
</html>