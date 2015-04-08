package toulousemusee

import grails.test.spock.IntegrationSpec
import spock.lang.*

class MuseeServiceIntegrationTestIntegrationSpec extends Specification {

    Adresse uneAdresse
    Gestionnaire unGestionnaire

    MuseeService museeService

    def setup() {
        uneAdresse = new Adresse(numero: "4 Bis", rue: "rue de la verge d'or", codePostal: "31000", ville: "Toulouse").save()
        unGestionnaire = new Gestionnaire(nom: "Mairie de Toulouse")
    }

    void "test mise a jour et l'insertion d'un musee"() {

        given: "un musee"
        Musee unMusee = new Musee(nom: "Musée de l'informatique",
                horairesOuverture: "OUVERT",
                adresse: uneAdresse,
                telephone: "05 05 05 05 50",
                accesBus: "Par là",
                accesMetro: "Par ici")

        and: "un gestionnaire"

        when: "on tente de répercuter en base la création ou la modification du musée"
        Musee resultMusee = museeService.insertOrUpdateMuseeForGestionnaire(unMusee, unGestionnaire)

        then: "le musée résultant pointe sur le musee initale"
        resultMusee == unMusee

        and: "le musée résultant n'a pas d'erreurs"
        !resultMusee.hasErrors()

        and: "le résultat a un id"
        resultMusee.id

        and: "le musée est bien présent en base"
        Musee.findById(resultMusee.id) != null

        and: "le musée a bien le gestionnaire passé en paramètre"
        resultMusee.gestionnaire == unGestionnaire
    }

    void "test du moteur de recherche sur les musées"() {


    }
}
