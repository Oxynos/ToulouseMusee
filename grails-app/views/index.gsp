<%@ page import="toulousemusee.Adresse" %>
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
				margin: 0em;
			}

			#recherche {
				width: 30em;
				margin: 2em auto;
			}
			#museeList {
				margin: 2em;
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
			<div id="recherche">
				<form action="musee/doResearch" method="post">
					<fieldset class="form">
						<div class="fieldcontain">
							<label for="musee">Nom du musée</label>
							<g:textField name="musee"/>
						</div>
						<div class="fieldcontain">
							<label for="codePostal">Code Postal</label>
							<g:select name="codePostal" from="${toulousemusee.Adresse.list().codePostal}"  class="many-to-one"/>
						</div>
						<div class="fieldcontain">
							<label for="adresseMusee">Rue</label>
							<g:textField name="adresseMusee"/>
						</div>
					</fieldset>
					<fieldset class="buttons">
						<input type="submit" name="search" value="Rechercher" class="save">
					</fieldset>
				</form>
			</div>

			<div id="list-musee" class="content scaffold-list" role="main">

					<tbody>

					</tbody>
				</table>

			</div>

			<div id="museeList">
					<h1>Musée List</h1>
					<table>
						<thead>
						<tr>
							<th>Nom</th>
							<th>Adresse</th>
							<th>Horaires</th>
							<th>Téléphone</th>
							<th>Gestionnaire</th>
							<th>Accès Bus</th>
							<th>Accès Métro</th>
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


							<td><g:form controller="musee">
								${session["musees"]?.get(0)?.id}
								${museeInstance.id}
								${session["musees"]?.get(0)?.nom}
								${museeInstance.nom}
								${session["musees"]?.get(0)?.telephone}
								${museeInstance.telephone}
								${session["musees"]?.get(0)?.horairesOuverture}
								${museeInstance.horairesOuverture}
								${session["musees"]?.get(0)?.accesBus}
								${museeInstance?.accesBus}
								${session["musees"]?.get(0)?.accesMetro}
								${museeInstance?.accesMetro}
								${Musee.findById(museeInstance.id).id}
								${session?.musees?.contains(museeInstance)}
								<g:set var="j" value="${Musee.findById(museeInstance.id) in session["musees"]}"/>
								<g:hiddenField name="id" value="${museeInstance.id}"/>
								<g:actionSubmit value="Ajouter aux favoris"
												onclick="return confirm(/Voulez vous ajouter ${museeInstance.nom} à vos musées préférés ?/)" action="addMusee"
												disabled="${session["musees"]?.contains(Musee.findById(museeInstance.id))}"/>
								${j}
							</g:form>
							</td>

						</tr>
					</g:each>
					</tbody>
				</table>
				<div class="pagination">
					<g:paginate total="${museeInstanceCount ?: 0}" />
				</div>
			</div>

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
