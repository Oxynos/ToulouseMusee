package toulousemusee

class Adresse {

    String numero
    String rue
    String codePostal
    String ville

    static constraints = {
        numero nullable: true
        rue nullable: true
        codePostal nullable: true
        ville blank: false
    }

    @Override
    String toString() {
        return "$numero $rue, $codePostal $ville"
    }
}
