package toulousemusee

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Musee)
class MuseeSpec extends Specification {

    void"la validité d'un musée valide"(String unNom, String desHoraires, String unTelephone,
                                        String unAccesM, String unAccesB, Adresse uneAdresse, Gestionnaire unGestionnaire) {

        given: "un nom, des horaires, un telephone, un acces metro et bus, une adresse et un gestionnaire initialisés correctement"
        Musee musee = new Musee(nom: unNom, horairesOuverture: desHoraires, telephone: unTelephone,
                                accesMetro: unAccesM, accesBus: unAccesB, adresse: uneAdresse, gestionnaire: unGestionnaire)

        expect: "le musée est valide"
        musee.validate() == true

        where:
        unNom|desHoraires|unTelephone|unAccesM|unAccesB|uneAdresse|unGestionnaire
        "un nom"|"horaires"|"un telephone"|"acces metro"|"acces bus"|Mock(Adresse)|Mock(Gestionnaire)
        "un nom"|null|"un telephone"|"acces metro"|"acces bus"|Mock(Adresse)|Mock(Gestionnaire)
        "un nom"|"horaires"|null|"acces metro"|"acces bus"|Mock(Adresse)|Mock(Gestionnaire)
        "un nom"|"horaires"|"un telephone"|null|"acces bus"|Mock(Adresse)|Mock(Gestionnaire)
        "un nom"|"horaires"|"un telephone"|"acces metro"|null|Mock(Adresse)|Mock(Gestionnaire)

    }

    void "l'invalidité d'un musée invalide"(String unNom, String desHoraires, String unTelephone,
                                                                                       String unAccesM, String unAccesB, Adresse uneAdresse, Gestionnaire unGestionnaire) {

        given: "un nom, des horaires, un telephone, un acces metro et bus, une adresse et un gestionnaire mal initialisés"
        Musee musee = new Musee(nom: unNom, horairesOuverture: desHoraires, telephone: unTelephone,
                accesMetro: unAccesM, accesBus: unAccesB, adresse: uneAdresse, gestionnaire: unGestionnaire)

        expect: "le musée est invalide"
        musee.validate() == false

        where:
        unNom|desHoraires|unTelephone|unAccesM|unAccesB|uneAdresse|unGestionnaire
        null|"horaires"|"un telephone"|"acces metro"|"acces bus"|Mock(Adresse)|Mock(Gestionnaire)
        "un nom"|"horaires"|"un telephone"|"acces metro"|"acces bus"|null|Mock(Gestionnaire)
        "un nom"|"horaires"|"un telephone"|"acces metro"|"acces bus"|Mock(Adresse)|null
    }
}
