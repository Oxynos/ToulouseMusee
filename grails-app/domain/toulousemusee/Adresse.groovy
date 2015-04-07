package toulousemusee

class Adresse {
    int numero
    String rue
    int codePostal
    String ville

    static constraints = {
        numero nullable: false
        rue nullable: false, blank: false
        codePostal nullable: false
        ville nullable: false, blank: false
    }

    @Override
    String toString() {
        return "$numero $rue, $codePostal $ville"
    }
}
