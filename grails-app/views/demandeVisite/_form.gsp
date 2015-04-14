<%@ page import="toulousemusee.DemandeVisite" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'code', 'error')} required">
	<g:hiddenField name="code" required="" value="NULL"/>
</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'statut', 'error')} required">
	<g:hiddenField name="statut" value="NULL"/>
</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'debutPeriode', 'error')} required">
	<label for="debutPeriode">
		<g:message code="demandeVisite.debutPeriode.label" default="Debut Periode" />
	</label>
	<g:datePicker name="debutPeriode" precision="day"  value="${demandeVisiteInstance?.debutPeriode}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'finPeriode', 'error')} required">
	<label for="finPeriode">
		<g:message code="demandeVisite.finPeriode.label" default="Fin Periode" />
	</label>
	<g:datePicker name="finPeriode" precision="day"  value="${demandeVisiteInstance?.finPeriode}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteInstance, field: 'nbPersonnes', 'error')} required">
	<label for="nbPersonnes">
		<g:message code="demandeVisite.nbPersonnes.label" default="Nb Personnes" />
	</label>
	<g:field name="nbPersonnes" type="number" min="1" max="6" value="${demandeVisiteInstance.nbPersonnes}" required=""/>

</div>

