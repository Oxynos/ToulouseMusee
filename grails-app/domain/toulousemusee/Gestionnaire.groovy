package toulousemusee

class Gestionnaire {

    String nom

    static hasMany = [musees:Musee]

    static constraints = {
        nom blank: false
    }

    @Override
    String toString() {
        nom
    }
}
