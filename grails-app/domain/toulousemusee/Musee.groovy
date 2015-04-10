package toulousemusee

class Musee {

    String nom
    String horairesOuverture
    String telephone
    String accesMetro
    String accesBus

    Adresse adresse

    Gestionnaire gestionnaire

    static constraints = {
        nom blank: false
        horairesOuverture nullable: true
        telephone nullable: true
        accesBus nullable: true
        accesMetro nullable: true
    }

    static mapping = {
        adresse fetch: "join"
        gestionnaire fetch: "join"
    }



    @Override
    String toString() {
        return "$nom ($gestionnaire)\n$adresse\n$horairesOuverture\nTéléphone : $telephone \nAccès métro : $accesMetro\nAccès bus : $accesBus"
    }


    @Override
    boolean equals(Object o) {

        boolean sameSame = false

        if (o != null && o instanceof Musee)
        {
            sameSame = this.id == ((Musee) o).id
        }

        return sameSame;
    }
}
