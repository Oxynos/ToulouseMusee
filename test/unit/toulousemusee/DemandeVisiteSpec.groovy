package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DemandeVisite)
class DemandeVisiteSpec extends Specification {

    void "la validité d'une demande de visite valide"(String unCode, Date unDebut
    , Date uneFin, int unNbP, String unStatut) {
        given: "un nom, un début, une fin, un nombre de personnes et un statut correctement initialisés"
        DemandeVisite uneDemande = new DemandeVisite(code: unCode, debutPeriode: unDebut,
                        finPeriode: uneFin, nbPersonnes: unNbP, statut: unStatut)

        expect: "la demande de visite est valide"
        uneDemande.validate() == true

        where:
        unCode|unDebut|uneFin|unNbP|unStatut
        "un code"|new Date(2016,10,20)|new Date(2016,10,30)|5|"un statut"
    }

    void "l'invalidité d'une demande de visite"(String unCode, Date unDebut,
                                                Date uneFin, int unNbP, String unStatut) {
        given: "un nom, un début, une fin, un nombre de personnes et un statut mal initialisés"
        DemandeVisite uneDemande = new DemandeVisite(code: unCode, debutPeriode: unDebut,
                finPeriode: uneFin, nbPersonnes: unNbP, statut: unStatut)

        expect: "la demande de visite est invalide"
        uneDemande.validate() == false

        where:
        unCode|unDebut|uneFin|unNbP|unStatut
        null|new Date()|new Date()|5|"un statut"
        "un code"|null|new Date()|5|"un statut"
        "un code"|new Date()|null|5|"un statut"
        "un code"|new Date()|new Date()|5|null
        "un code"|new Date(2016,10,10)|new Date(2009,9,9)|5|"un statut"
        "un code"|new Date()|new Date()|8|"un statut"
    }
}
