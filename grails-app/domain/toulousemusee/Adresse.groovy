package toulousemusee

class Adresse {
    int numero
    String rue
    int codePostal
    String ville

    static constraints = {
    }

    @Override
    String toString() {
        return "$numero $rue, $codePostal $ville"
    }
}
