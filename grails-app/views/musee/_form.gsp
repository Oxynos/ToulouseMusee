<%@ page import="toulousemusee.Musee" %>



<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesBus', 'error')} required">
	<label for="accesBus">
		<g:message code="musee.accesBus.label" default="Acces Bus" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accesBus" required="" value="${museeInstance?.accesBus}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'accesMetro', 'error')} required">
	<label for="accesMetro">
		<g:message code="musee.accesMetro.label" default="Acces Metro" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="accesMetro" required="" value="${museeInstance?.accesMetro}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'adresse', 'error')} required">
	<label for="adresse">
		<g:message code="musee.adresse.label" default="Adresse" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="adresse" required="" value="${museeInstance?.adresse}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'gestionnaire', 'error')} required">
	<label for="gestionnaire">
		<g:message code="musee.gestionnaire.label" default="Gestionnaire" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="gestionnaire" required="" value="${museeInstance?.gestionnaire}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'horaires', 'error')} required">
	<label for="horaires">
		<g:message code="musee.horaires.label" default="Horaires" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="horaires" required="" value="${museeInstance?.horaires}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="musee.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${museeInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'siteWeb', 'error')} required">
	<label for="siteWeb">
		<g:message code="musee.siteWeb.label" default="Site Web" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="siteWeb" required="" value="${museeInstance?.siteWeb}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: museeInstance, field: 'telephone', 'error')} required">
	<label for="telephone">
		<g:message code="musee.telephone.label" default="Telephone" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="telephone" required="" value="${museeInstance?.telephone}"/>

</div>

