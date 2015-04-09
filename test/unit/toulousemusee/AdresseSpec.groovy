package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Adresse)
class AdresseSpec extends Specification {

    void "la validité d'une adresse valide"(String unNumero, String uneRue, String unCode, String uneVille) {
        given: "une adresse avec un numero, une rue, un code postal et une ville correctement initialisé"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCode, ville: uneVille)

        expect: "l'adresse est valide"
        adresse.validate() == true

        where:
        unNumero|uneRue|unCode|uneVille
        "un numero" | "une rue" | "un code postal" | "une ville"
        "un numero" | null | "un code" | "ville"
        "un numero" | "une rue" | null | "une ville"

    }

    void "l'invalidité d'une adresse invalide"() {
        given: "une adresse avec un numero, une rue, un code postal et une ville mal initialisé"
        Adresse adresse = new Adresse(numero: unNumero, rue: uneRue, codePostal: unCode, ville: uneVille)

        expect: "l'adresse est invalide"
        adresse.validate() == false

        where:
        unNumero|uneRue|unCode|uneVille
        "un numero" | "une rue" | "un code postal" | null
    }
}
