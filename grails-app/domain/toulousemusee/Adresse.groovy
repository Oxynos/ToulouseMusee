package toulousemusee

class Adresse {

    String numero
    String rue
    String codePostal
    String ville
    /*String secteur
    String quartier
    double x_cc43
    double y_cc43
    double x_wgs84
    double y_wgs84*/

    static constraints = {
        numero nullable: true
        rue nullable: true
        codePostal nullable: true
        ville blank: false
    }

    @Override
    String toString() {
        return "$numero $rue, $codePostal $ville"
        //return "$numero $rue, $codePostal $ville (Secteur $secteur, Quartier $quartier)"
    }
}
