
<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div id="list-musee" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />

            <th><g:message code="musee.adresse.label" default="Adresse" /></th>

            <g:sortableColumn property="horairesOuverture" title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}" />

            <g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />

            <th><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></th>

            <g:sortableColumn property="accesBus" title="${message(code: 'musee.accesBus.label', default: 'Acces Bus')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${museeInstanceList}" status="i" var="museeInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

                <td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                <td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

                <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

                <td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

                <td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>

                <td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${museeInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>