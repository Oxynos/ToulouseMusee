package toulousemusee

class DemandeVisite {

    String code
    Date debutPeriode
    Date finPeriode
    int nbPersonnes
    String statut

    static hasMany = [
            musees: Musee
    ]

    static constraints = {
        code blank: false
        statut blank: false
    }

    static mapping = {
        musees fetch: "join"
    }
}
