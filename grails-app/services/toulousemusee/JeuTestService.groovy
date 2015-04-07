package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {
    Musee m1
    Gestionnaire g
    Adresse a

    def createJeuTestForMusee() {
        g = new Gestionnaire(nom: "Mairie de Toulouse").save(flush: true)
        a = new Adresse(codePostal: 31000, numero: 1, rue: "Place du Capitole", ville: "Toulouse").save(flush: true)
        a2 = new Adresse(codePostal: 31000, numero: 38, rue: "L'Oeil du Kraken", ville: "Toulouse").save(flush: true)
        if (Musee.count() == 0) {
            m1 = new Musee(
                    adresse: a,
                    nom: "Musee",
                    gestionnaire: g,
                    horairesOuverture: "10",
                    telephone: "0505505050",
                    accesBus: "Par la",
                    accesMetro: "Par ici"
            ).save(flush: true)
            m2 = new Musee(
                    adresse: a,
                    nom: "Jardin des plantes",
                    gestionnaire: g2,
                    horairesOuverture: "10",
                    telephone: "0505505050",
                    accesBus: "Par la",
                    accesMetro: "Par ici"
            ).save(flush: true)
        }

    }
}
