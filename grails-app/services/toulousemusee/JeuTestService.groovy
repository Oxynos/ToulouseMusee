package toulousemusee

import grails.transaction.Transactional
import org.grails.plugins.csv.CSVMapReader

@Transactional
class JeuTestService {

    def createJeuTestForMusee() {
        def csv = new File("src/main/resources/Musee.csv")

        csv.toCsvReader(['separatorChar':';', 'skipLines':1]).eachLine { tokens ->
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
            Gestionnaire g = new Gestionnaire(nom: tokens[1]).save()
            Adresse a = new Adresse(numero: tokens[7], rue: tokens[8], codePostal: tokens[9], ville: tokens[10]).save()
            Musee musee = new Musee(nom: tokens[0],
                    gestionnaire: g,
                    horairesOuverture: tokens[2],
                    telephone: tokens[4],
                    accesMetro: tokens[5],
                    accesBus: tokens[6],
                    adresse: a).save()
            println(" ")
            println(musee)
        }
    }
}
