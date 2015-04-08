package toulousemusee

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus
    //String siteWeb

    Adresse adresse

    Gestionnaire gestionnaire

    static constraints = {
        nom blank: false
        horairesOuverture nullable: true
        telephone nullable: true
        accesBus nullable: true
        accesMetro nullable: true
        adresse nullable: true
    }

    @Override
    String toString() {
        return "$nom ($gestionnaire)\n$adresse\n$horairesOuverture\nTéléphone : $telephone \nAccès métro : $accesMetro\nAccès bus : $accesBus"
        //return "$nom ($gestionnaire)\n$siteWeb\n$adresse\n$horairesOuverture\nTéléphone : $telephone \nAccès métro : $accesMetro\nAccès bus : $accesBus"
    }
}
