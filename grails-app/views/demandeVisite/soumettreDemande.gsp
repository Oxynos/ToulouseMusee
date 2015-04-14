
<%@ page import="toulousemusee.DemandeVisite" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
    <title>Demande acceptée</title>
</head>
<body>
<div class="message" role="contentinfo">
    <ul>
        Votre demande ${demandeVisiteInstance?.code} est désormais en cours de traitement.
    </ul>

    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Retour à l'accueil</a></li>
    </ul>
</div>
</body>
</html>
