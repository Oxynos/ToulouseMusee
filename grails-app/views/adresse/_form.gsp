<%@ page import="toulousemusee.Adresse" %>



<div class="fieldcontain ${hasErrors(bean: adresseInstance, field: 'numero', 'error')} ">
	<label for="numero">
		<g:message code="adresse.numero.label" default="Numero" />
		
	</label>
	<g:textField name="numero" value="${adresseInstance?.numero}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: adresseInstance, field: 'rue', 'error')} ">
	<label for="rue">
		<g:message code="adresse.rue.label" default="Rue" />
		
	</label>
	<g:textField name="rue" value="${adresseInstance?.rue}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: adresseInstance, field: 'codePostal', 'error')} ">
	<label for="codePostal">
		<g:message code="adresse.codePostal.label" default="Code Postal" />
		
	</label>
	<g:textField name="codePostal" value="${adresseInstance?.codePostal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: adresseInstance, field: 'ville', 'error')} required">
	<label for="ville">
		<g:message code="adresse.ville.label" default="Ville" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ville" required="" value="${adresseInstance?.ville}"/>

</div>

