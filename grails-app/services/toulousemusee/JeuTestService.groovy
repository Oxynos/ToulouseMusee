package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {

    def createJeuTestForMusee() {
        def g = new Gestionnaire(nom: "Mairie de Toulouse").save(flush: true)
        def a = new Adresse(codePostal: 31000, numero: 1, rue: "Place du Capitole", ville: "Toulouse").save(flush: true)
        def a2 = new Adresse(codePostal: 31000, numero: 38, rue: "L'Oeil du Kraken", ville: "Toulouse").save(flush: true)
        if (Musee.count() == 0) {
            def m1 = new Musee(
                    adresse: a,
                    nom: "Musee",
                    gestionnaire: g,
                    horairesOuverture: "10",
                    telephone: "0505505050",
                    accesBus: "Par la",
                    accesMetro: "Par ici"
            ).save(flush: true)
            def m2 = new Musee(
                    adresse: a,
                    nom: "Jardin des plantes",
                    gestionnaire: g,
                    horairesOuverture: "10",
                    telephone: "0505505050",
                    accesBus: "Par la",
                    accesMetro: "Par ici"
            ).save(flush: true)
        }

    }
}
