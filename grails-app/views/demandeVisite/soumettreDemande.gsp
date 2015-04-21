
<%@ page import="toulousemusee.DemandeVisite" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
    <title>Demande acceptée</title>
</head>
<body>
<div class="spinner">
    <ul>
        Votre demande ${demandeVisiteInstance?.code} est désormais en cours de traitement.
    </ul>
</div>
<div class="button" role="button">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Retour à l'accueil</a></li>
    </ul>
</div>
</body>
</html>
