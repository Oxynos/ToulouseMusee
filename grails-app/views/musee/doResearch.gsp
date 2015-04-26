<%@ page import="toulousemusee.Musee" %>

    <g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
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

            <g:sortableColumn property="accesMetro" title="${message(code: 'musee.accesMetro.label', default: 'Acces Metro')}" />

            <g:sortableColumn property="ajoutFavoris" title="${message(code: 'ajoutFavoris.label', default: 'Ajouter aux favoris')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${museeInstanceList}" status="i" var="museeInstance">
            <g:set var="favourite" value="${session["musees"]?.contains(museeInstance)}" />
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

                <td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                <td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

                <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

                <td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

                <td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>

                <td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>

                <td><g:form controller="musee">
                    <g:hiddenField name="id" value="${museeInstance.id}"/>
                    <fieldset class="buttons">
                        <g:actionSubmit value="${favourite? "Favori" : " + "}"
                                        onclick="return confirm(/Voulez vous ajouter ${museeInstance.nom} à vos musées préférés ?/)" action="addMusee"
                                        disabled="${favourite}" id="buttonLarge"/>
                    </fieldset>
                </g:form>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <util:remotePaginate update="museeList" action="doResearch" total="${museeInstanceCount?:0}" max="5" />
    </div>
</div>