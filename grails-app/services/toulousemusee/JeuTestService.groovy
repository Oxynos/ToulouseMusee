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
            Gestionnaire gest
            List<Gestionnaire> lg = Gestionnaire.getAll()
            for (Gestionnaire g : lg) {
                if (g.nom == tokens[1]) {
                    gest = g
                }
            }
            if (gest == null) gest = new Gestionnaire(nom: tokens[1]).save()

            /*String cp
            List<String> lcp = Adresse.getAll().codePostal
            for (String codeP : lcp) {
                if (codeP == tokens[9]) {
                    cp = codeP
                }
            }
            if (cp == null) cp = tokens[9]
            Adresse adr = new Adresse(numero: tokens[7], rue: tokens[8], codePostal: cp, ville: tokens[10]).save()*/

            Adresse adr
            List<Adresse> la = Adresse.getAll()
            for (Adresse a : la) {
                if (a.numero == tokens[7] && a.rue == tokens[8] && a.codePostal == tokens[9] && a.ville == tokens[10]) {
                    adr = a
                }
            }
            if (adr == null) adr = new Adresse(numero: tokens[7], rue: tokens[8], codePostal: tokens[9], ville: tokens[10]).save()

            Musee musee = new Musee(nom: tokens[0],
                    gestionnaire: gest,
                    horairesOuverture: tokens[2],
                    telephone: tokens[4],
                    accesMetro: tokens[5],
                    accesBus: tokens[6],
                    adresse: adr).save()
            println(" ")
            println(musee)
        }
    }
}
