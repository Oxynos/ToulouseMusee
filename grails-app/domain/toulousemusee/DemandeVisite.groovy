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
        musees nullable: true
        debutPeriode min: new Date(), nullable: false, validator: {value, reference ->
            if (reference.finPeriode == null)
                return false
            else
                return value.before(reference.finPeriode)}
        finPeriode nullable: false, validator: {value, reference ->
            if (reference.debutPeriode == null)
                return false
            else
                return value.after(reference.debutPeriode)}
        nbPersonnes max: 6, min: 1
    }

    static mapping = {
        musees fetch: "join"
    }
}
