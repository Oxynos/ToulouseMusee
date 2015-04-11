package toulousemusee

import grails.transaction.Transactional

@Transactional
class JeuTestService {

    Musee unMusee
    Musee musee1
    Musee musee2
    Musee musee3

    Gestionnaire unGestionnaire
    Gestionnaire gestionnaire1
    Gestionnaire gestionnaire2

    Adresse uneAdresse
    Adresse adresse1
    Adresse adresse2
    Adresse adresse3

    MuseeService museeService
    DemandeVisiteService demandeVisiteService

    def createJeuTestForMusee() {
        if (Musee.count() == 0) {
            gestionnaire1 = new Gestionnaire(nom: "Mairie de Toulouse")
            adresse1 = new Adresse(numero: "11B", rue: "rue aaa", ville: "Toulouse", codePostal: "31000").save()
            musee1 = museeService.insertOrUpdateMuseeForGestionnaire(new Musee(nom: "Musée medecine", horairesOuverture: "Ouvert de à", adresse: adresse1), gestionnaire1)

        }
    }

    def createJeuTestForGestionnaires() {
        if (Gestionnaire.count() == 0) {
            gestionnaire2 = new Gestionnaire(nom: "Dupond").save()
        }
    }


    def createJeuTest() {

        def csv = new File("src/main/resources/Musee.csv")

        csv.toCsvReader(['separatorChar':';', 'skipLines':1]).eachLine { tokens ->

            uneAdresse = new Adresse(numero: tokens[7], rue: tokens[8], codePostal: tokens[9], ville: tokens[10]).save()

            unGestionnaire = null
            List<Gestionnaire> listeGestionnaires = Gestionnaire.getAll()
            for (Gestionnaire gestionnaire : listeGestionnaires) {
                if (gestionnaire.nom == tokens[1]) {
                    unGestionnaire = gestionnaire
                }
            }
            if (unGestionnaire == null) unGestionnaire = new Gestionnaire(nom: tokens[1])

            unMusee = new Musee(nom: tokens[0],
                    horairesOuverture: tokens[2],
                    telephone: tokens[4],
                    accesMetro: tokens[5],
                    accesBus: tokens[6],
                    adresse: uneAdresse)

            museeService.insertOrUpdateMuseeForGestionnaire(unMusee, unGestionnaire)
            println(" ")
            //println(unMusee)

            /*println(tokens)
            Adresse a = new Adresse(numero: tokens[7], rue: tokens[8], codePostal: tokens[9], ville: tokens[10],
                    secteur: tokens[11], quartier: tokens[12],
                    x_cc43: tokens[13], y_cc43: tokens[14], x_wgs84: tokens[15], y_wgs84: tokens[16]).save()
            Musee musee = new Musee(nom: tokens[0],
                    gestionnaire: gest,
                    horairesOuverture: tokens[2],
                    siteWeb: tokens[3],
                    telephone: tokens[4],
                    accesMetro: tokens[5],
                    accesBus: tokens[6],
                    adresse: ad)*/
        }
        DemandeVisite demandeVisite = new DemandeVisite(code: "code" , debutPeriode: new Date(2016,10,10), finPeriode: new Date(2016,11,10), nbPersonnes: 6, musees: [Musee.findById(1), Musee.findById(2)],statut: "statut")
        demandeVisiteService.insertOrUpdateDemandeVisiteForMusees(demandeVisite, demandeVisite.musees.asList())
    }
}