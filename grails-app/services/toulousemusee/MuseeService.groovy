package toulousemusee

import grails.transaction.Transactional
import org.hibernate.FetchMode

@Transactional
class MuseeService {

    /**
     * Crée un nouveau musée
     * @param unMusee le musée
     * @param unGestionnaire le gestionnaire du musée
     * @return le musée créé
     */
    Musee insertOrUpdateMuseeForGestionnaire(Musee unMusee, Gestionnaire unGestionnaire) {
        unGestionnaire.addToMusees(unMusee)
        unGestionnaire.save(flush: true)
        unMusee
    }

    /**
     * Supprime un musée
     * @param unMusee le musée à supprimer
     */
    void deleteMusee(Musee unMusee) {
        unMusee.gestionnaire.removeFromMusees(unMusee)
        unMusee.delete()
    }

    List<Musee> searchMusee(String name, String codeP, String adress) {
        def results = Musee.createCriteria().list() {
            fetchMode("adresse",FetchMode.JOIN)
            fetchMode("gestionnaire", FetchMode.JOIN)
            if (name) {
                if (codeP) {
                    adresse {
                        like("codePostal", codeP)
                    }
                }
                if (adress) {
                    adresse {
                        like("rue", "%$adress%")
                    }
                }
                like("nom", "%$name%")
            } else if (codeP) {
                adresse {
                    like("codePostal", codeP)
                }
                if (adress) {
                    adresse {
                        like("rue", "%$adress%")
                    }
                }
            } else if (adress) {
                adresse {
                    like("rue", "%$adress%")
                }
            }
        }
        results
    }

}
