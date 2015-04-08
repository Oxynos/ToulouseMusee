package toulousemusee

class Musee {
    String nom
    Adresse adresse
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus
    Gestionnaire gestionnaire
    //String siteWeb

    static constraints = {
        nom nullable: false, blank: false
        adresse nullable: false, blank: false
        horairesOuverture nullable: false, blank: false
        telephone nullable: false, blank: false
        gestionnaire nullable: false, blank: false
    }

    @Override
    String toString() {
        return "$nom ($gestionnaire)\n$adresse\n$horairesOuverture\nTéléphone : $telephone \nAccès métro : $accesMetro\nAccès bus : $accesBus"
        //return "$nom ($gestionnaire)\n$siteWeb\n$adresse\n$horairesOuverture\nTéléphone : $telephone \nAccès métro : $accesMetro\nAccès bus : $accesBus"
    }

}
