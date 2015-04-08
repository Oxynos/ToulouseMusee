<%@ page import="toulousemusee.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Bienvenue sur Toulouse-Musées</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<div id="page-body" role="main">
			<table>
				<tbody>
				<g:each in="${toulousemusee.Musee.list().getAt(0..2)}" status="i" var="museeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

						<td>${fieldValue(bean: museeInstance, field: "nom")}</td>

						<td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

						<td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

						<td>${fieldValue(bean: museeInstance, field: "telephone")}</td>

						<td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

						<td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>

						<td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>


						<td><g:form controller="musee">
							<g:hiddenField name="id" value="${museeInstance.id}"/>
							<g:actionSubmit value="Ajouter aux favoris"
											onclick="return confirm(/Voulez vous ajouter ${museeInstance.nom} à vos musées préférés ?/)" action="addMusee"/></g:form></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<g:form name="searchMusee" action="doResearch" method="post" controller="musee">
			<table>
				<tr><td>Nom du Musée (ou une partie)</td>
					<td><g:textField name="musee"/></td></tr>
				<tr><td>Code Postal</td>
					<td><g:select name="codePostal" from="${toulousemusee.Musee.list()*.adresse.codePostal}"  class="many-to-one"/></td></tr>
				<tr><td>Adresse du musée (ou une partie)</td>
					<td><g:textField name="adresseMusee"/></td></tr>
				<tr><td><g:submitButton name="rechercher" value="Rechercher"/></td></tr>
			</table>
			</g:form>

			<div id="controller-list" role="navigation">
				<h2>Available Controllers:</h2>
				<ul>
					<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
					</g:each>
				</ul>
			</div>
			</div>
		</div>
	</body>
</html>
