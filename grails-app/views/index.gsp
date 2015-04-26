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
				width: 35em;
				margin: 2em auto;
			}
			#museeList {
				margin: 2em;
			}
			
			#favMuseeList {
				float: right;
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
			#buttonLarge {
				width: 100%;
			}

			#buttonMedium {
				width: 60%;
				align-content: center;
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
				<g:formRemote name="form" url="[controller : 'musee', action : 'doResearch']" update="museeList">
					<fieldset class="form">
						<div class="fieldcontain">
							<label for="musee">Nom du musée</label>
							<g:textField name="musee"/>
						</div>
						<div class="fieldcontain">
							<label for="codePostal">Code Postal</label>
							<g:select name="codePostal" from="${[""]+Adresse.list().codePostal.unique().sort()}"/>
						</div>
						<div class="fieldcontain">
							<label for="adresseMusee">Rue</label>
							<g:textField name="adresseMusee"/>
						</div>
					</fieldset>
					<fieldset class="buttons">
						<g:actionSubmit value="Rechercher" id="buttonLarge"/>
					</fieldset>
				</g:formRemote>
			</div>
			<g:if test="${session["musees"]}">
				<div id="favMuseeList">
					<table>
							<tr><th>Vos musées favoris</th><th>Supprimer des favoris</th></tr>
							<g:each var="m" in="${((List<Musee>)session["musees"])?.sort { it.nom }}">
									<tr><td>${m.nom}</td>
										<td>
											<g:form controller="musee">
												<g:hiddenField name="idFavourite" value="${m.id}"/>
												<fieldset class="buttons">
													<g:actionSubmit value=" - " id="buttonLarge" onclick="return confirm(/Voulez vous supprimer ${m.nom} de vos musées préférés ?/)"
													action="removeMusee"/>
												</fieldset>
											</g:form>
									</td></tr>
							</g:each>
					</table>
					<g:form controller="demandeVisite">
						<fieldset class="buttons">
							<g:actionSubmit value="Effectuer une demande de visite" id = "buttonMedium" action="demande"/>
						</fieldset>
					</g:form>
				</div>
			</g:if>
			<div id="museeList">
			</div>
			</div>
		</div>
	</body>
</html>
