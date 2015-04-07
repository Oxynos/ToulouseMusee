package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {
    Musee m1
    Gestionnaire g
    Adresse a

    def createJeuTestForMusee() {
        g = new Gestionnaire(nom: "Mairie de Toulouse").save()
        a = new Adresse(codePostal: 31000, numero: 1, rue: "Place du Capitole", ville: "Toulouse").save(

        )
        if (Musee.count() == 0) {
            m1 = new Musee()
        }

    }
}
