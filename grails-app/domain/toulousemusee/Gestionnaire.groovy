package toulousemusee

class Gestionnaire {
    String nom

    static hasMany = [musees:Musee]

    static constraints = {
        nom nullable: false, blank: false
    }

    String toString() {
        nom
    }
}
