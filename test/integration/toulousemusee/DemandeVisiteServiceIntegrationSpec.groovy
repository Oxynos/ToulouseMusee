package toulousemusee

import grails.test.spock.IntegrationSpec

class DemandeVisiteServiceIntegrationSpec extends IntegrationSpec {

    Musee unMusee
    Musee unMusee2
    Adresse uneAdresse
    Gestionnaire unGestionnaire

    DemandeVisiteService demandeVisiteService
    MuseeService museeService

    def setup() {
        uneAdresse = new Adresse(numero: "4 Bis", rue: "rue de la verge d'or", codePostal: "31000", ville: "Toulouse").save()
        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse")
        unMusee = new Musee(nom: "Musée de l'informatique",
                horairesOuverture: "OUVERT",
                adresse: uneAdresse,
                telephone: "05 05 05 05 50",
                accesBus: "Par là",
                accesMetro: "Par ici")
        unMusee2 = new Musee(nom: "Musée de la nature",
                horairesOuverture: "OUVERT",
                adresse: uneAdresse,
                telephone: "06 06 06 06 50",
                accesBus: "Par là",
                accesMetro: "Par ici")
        museeService.insertOrUpdateMuseeForGestionnaire(unMusee,unGestionnaire)
        museeService.insertOrUpdateMuseeForGestionnaire(unMusee2,unGestionnaire)
    }

    void "test mise à jour et insertion d'une demande de visite avec un musee"() {

        given: "une demande de visite"
        DemandeVisite uneDemande = new DemandeVisite(code: "un code",
                    debutPeriode: new Date(2016,10,10),
                    finPeriode: new Date(2016,11,11),
                    nbPersonnes: 4,
                    statut: "un statut")

        when: "on tente de répercuter en base la création ou la modification de la demande de visite"
        DemandeVisite resultDemande = demandeVisiteService.insertOrUpdateDemandeVisiteForMusee(uneDemande,unMusee)

        then: "la demande résultante pointe sur la demande initiale"
        resultDemande == uneDemande

        and: "la demande résultante n'a pas d'erreurs"
        !resultDemande.hasErrors()

        and: "le résultat a un id"
        resultDemande.id

        and: "le résultat est bien présent en base"
        DemandeVisite.findById(resultDemande.id) != null

        and: "la demande a bien le musée passé en paramètre"
        resultDemande.musees.getAt(0) == unMusee
    }

    void "test mise à jour et insertion d'une demande de visite avec plusieurs musee"() {

        given: "une demande de visite"
        DemandeVisite uneDemande = new DemandeVisite(code: "un code",
                debutPeriode: new Date(2016,10,10),
                finPeriode: new Date(2016,11,11),
                nbPersonnes: 4,
                statut: "un statut")

        and: "une liste de musées"
        List<Musee> musees = [unMusee,unMusee2]

        when: "on tente de répercuter en base la création ou la modification de la demande de visite"
        DemandeVisite resultDemande = demandeVisiteService.insertOrUpdateDemandeVisiteForMusees(uneDemande,musees)

        then: "la demande résultante pointe sur la demande initiale"
        resultDemande == uneDemande

        and: "la demande résultante n'a pas d'erreurs"
        !resultDemande.hasErrors()

        and: "le résultat a un id"
        resultDemande.id

        and: "le résultat est bien présent en base"
        DemandeVisite.findById(resultDemande.id) != null

        and: "la demande a bien le premier musée passé en paramètre"
        resultDemande.musees.getAt(0) == unMusee

        and: "la demande a bien le second musée passé en paramètre"
        resultDemande.musees.getAt(1) == unMusee2
    }

    void "test suppression d'une demande de visite"() {

        given: "une demande de visite existante en base"
        DemandeVisite demandeVisite = new DemandeVisite(code: "un code",
                debutPeriode: new Date(2016, 10, 10),
                finPeriode: new Date(2016, 11, 11),
                nbPersonnes: 4,
                statut: "un statut")
        demandeVisiteService.insertOrUpdateDemandeVisiteForMusee(demandeVisite, unMusee)

        when: "on déclenche la suppression de la demande"
        demandeVisiteService.deleteDemandeVisite(demandeVisite)

        then: "la demande est supprimée de la base"
        DemandeVisite.findById(demandeVisite.id) == null

        and: "le musée n'est pas supprimé"
        Musee.findById(unMusee.id) != null
    }

    void "test création d'un code pour une demande"() {
        given: "une demande de visite"
        DemandeVisite demandeVisite = new DemandeVisite(code: "un code",
                debutPeriode: new Date(2016, 10, 10),
                finPeriode: new Date(2016, 11, 11),
                nbPersonnes: 4,
                statut: "un statut")

        when: "on change le code de la demande"
        demandeVisiteService.createCodeForDemandeVisite(demandeVisite)

        then: "le code est composé de 8 caractères majuscules et numériques"
        demandeVisite.code.matches("[A-Z0-9]{8}")



    }

}
