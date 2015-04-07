package toulousemusee

class Musee {
    String nom
    Adresse adresse
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus
    Gestionnaire gestionnaire

    static constraints = {
        nom nullable: false, blank: false
        adresse nullable: false, blank: false
        horairesOuverture nullable: false, blank: false
        telephone nullable: false, blank: false
        gestionnaire nullable: false, blank: false
    }


}
