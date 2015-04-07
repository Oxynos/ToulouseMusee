<%@ page import="toulousemusee.Gestionnaire" %>



<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="gestionnaire.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${gestionnaireInstance?.nom}"/>

</div>

