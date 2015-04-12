package toulousemusee

import grails.test.spock.IntegrationSpec
import spock.lang.*

class MuseeServiceIntegrationTestIntegrationSpec extends Specification {

    Adresse uneAdresse
    Gestionnaire unGestionnaire

    MuseeService museeService
    JeuTestService jeuTestService

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

        given: "les musées, les gestionnaires, les adresses donnés par le jeu de test"
        jeuTestService

        when: "on cherche les musées dont le code postal est 31300"
        List<Musee> res = museeService.searchMusee(null, "31300", null)

        then:"on récupère 2 musées dont le code postal est 31300"
        res.size() == 2
        res.contains(Musee.findByTelephone("05 61 77 84 25"))
        res.contains(Musee.findByTelephone("05 61 77 82 72"))

        when: "on cherche les musées dont le nom contient JAC"
        res = museeService.searchMusee("JAC",null,null)

        then: "on récupère uniquement le musée ensemble conventuel des Jacobins"
        res.size() == 1
        res.contains(Musee.findByNom("ENSEMBLE CONVENTUEL DES JACOBINS"))

        when: "on récupère les musées avec l'adresse contenant PANT"
        res = museeService.searchMusee(null,null,"PANT")

        then: "on récupère uniquement le centre méridional de l'architecture"
        res.size() == 1
        res.contains(Musee.findByNom("CMAV - CENTRE MERIDIONAL DE L'ARCHITECTURE DE LA VILLE"))
    }

    void "test suppression d'un musée"() {

        given: "un musée existant en base"
        Musee unMusee = new Musee(nom: "Musée de l'informatique",
                horairesOuverture: "OUVERT",
                adresse: uneAdresse,
                telephone: "05 05 05 05 50",
                accesBus: "Par là",
                accesMetro: "Par ici")
        museeService.insertOrUpdateMuseeForGestionnaire(unMusee,unGestionnaire)

        when: "on déclenche la suppression du musée"
        museeService.deleteMusee(unMusee)

        then: "le musée est supprimé en base"
        Musee.findById(unMusee.id) == null

        and: "le gestionnaire et l'adresse ne sont pas supprimés"
        Adresse.findById(uneAdresse.id) != null
        Gestionnaire.findById(unGestionnaire.id) != null
    }
}
