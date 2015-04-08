package toulousemusee

import grails.test.spock.IntegrationSpec
import spock.lang.*

class MuseeServiceIntegrationTestIntegrationSpec extends IntegrationSpec {

    MuseeService museeService

    @Unroll
    void "test mise a jour et l'insertion d'un musee"() {

        given: "un gestionnaire"
        Gestionnaire unGestionnaire = new Gestionnaire(nom: "M. Gestion")

        and: "un musee"
        Musee unMusee = new Musee(nom: "Musee",
                adresse: "8, rue Machin",
                telephone: "0505050550",
                gestionnaire: unGestionnaire,
                accesBus: "Par là",
                accesMetro: "Par ici")

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

    @Unroll
    void "test du moteur de recherche sur les musées"() {


    }
}
