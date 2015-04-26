package toulousemusee


import grails.test.mixin.*
import spock.lang.*

@TestFor(DemandeVisiteController)
@Mock(DemandeVisite)
class DemandeVisiteControllerSpec extends Specification {
DemandeVisiteService demandeVisiteService


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        Adresse uneAdresse = new Adresse(numero: "4 Bis", rue: "rue de la verge d'or", codePostal: "31000", ville: "Toulouse")
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse")
        params["code"] = "un code"
        params["debutPeriode"] = new Date(2016,10,10)
        params["finPeriode"] = new Date(2016,11,11)
        params["nbPersonnes"] = 4
        params["statut"] = "un statut"
        params["musees"] = [new Musee(nom: "nom", horairesOuverture: "horaire", telephone: "telephone", adresse: uneAdresse, gestionnaire: unGestionnaire)]
    }

    void "Test that the show action returns the correct model"() {
        when: "The show action is executed with a null domain"
        controller.show(null)

        then: "A 404 error is returned"
        response.status == 404

        when: "A domain instance is passed to the show action"
        populateValidParams(params)
        def demandeVisite = new DemandeVisite(params)
        controller.show(demandeVisite)

        then: "A model is populated containing the domain instance"
        model.demandeVisiteInstance == demandeVisite
    }

    void "test that the demande respond a new Demande"() {
        given: "une demande de visite"
        DemandeVisite demandeVisite = new DemandeVisite(params)

        when: "on demande"
        controller.demande()

        then: "une demande est envoyée"
        model.demandeVisiteInstance.code == demandeVisite.code
    }

    void "Test that soumettreDemande correctly persists an instance "() {

        given: "un service"
        demandeVisiteService = new DemandeVisiteService()
        controller.demandeVisiteService = demandeVisiteService

        when: "The soumettreDemande action is executed with an invalid instance"
        request.contentType = FORM_CONTENT_TYPE
        request.method = 'POST'
        def demandeVisite = new DemandeVisite()
        demandeVisite.validate()
        controller.soumettreDemande(demandeVisite)

        then: "The create view is rendered again with the correct model"
        model.demandeVisiteInstance != null
        view == 'demande'

        when: "The save action is executed with a valid instance"
        response.reset()
        populateValidParams(params)
        demandeVisite = new DemandeVisite(params)

        controller.soumettreDemande(demandeVisite)

        then: "A redirect is issued to the soumettreDemande action"
        response.redirectedUrl == '/demandeVisite/soumettreDemande'
        controller.flash.message != null
        DemandeVisite.count() == 1
    }
}
