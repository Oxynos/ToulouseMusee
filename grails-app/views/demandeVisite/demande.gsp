<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'demandeVisite.label', default: 'DemandeVisite')}" />
    <title>Nouvelle demande de visite</title>
</head>
<body>
<table>
    <tr><th>Musées demandés</th></tr>
    <g:each var="m" in="${session["musees"]?.sort { it.nom }}">
        <tr><td><g:link controller="musee" action="show" id="${m.id}">${fieldValue(bean: m, field: "nom")}</g:link></td></tr>
    </g:each>
</table>
<div id="create-demandeVisite" class="content scaffold-create" role="main">
    <h1>Votre demande de visite</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${demandeVisiteInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${demandeVisiteInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form url="[resource:demandeVisiteInstance, action:'soumettreDemande']" >
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
