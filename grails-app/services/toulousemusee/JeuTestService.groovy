package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {
    Musee m1
    Gestionnaire g

    def createJeuTestForMusee() {
       g = new Gestionnaire(nom: "Mairie de Toulouse")

        if (Musee.count() == 0) {
            m1 = new Musee()
        }

    }
}
