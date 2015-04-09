package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Gestionnaire)
class GestionnaireSpec extends Specification {


    void "la validité d'un gestionnaire valide"(String unNom, def _) {
        given: "un nom correctement initialisé"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est valide"
        gestionnaire.validate() == true

        where:
        unNom | _
        "un nom" | _
    }

    void "l'invalidité d'un gestionnaire invalide"(String unNom, def _) {
        given: "un nom correctement initialisé"
        Gestionnaire gestionnaire = new Gestionnaire(nom: unNom)

        expect: "le gestionnaire est invalide"
        gestionnaire.validate() == false

        where:
        unNom | _
        null | _
    }
}
