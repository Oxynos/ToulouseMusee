package toulousemusee

import grails.transaction.Transactional

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
                if (name) {
                    like("nom", "%$name%")
                }
            } else if (adress) {
                adresse {
                    like("rue", "%$adress%")
                }
                if (codeP) {
                    adresse {
                        like("codePostal", codeP)
                    }
                }
                if (name) {
                    like("nom", "%$name%")
                }
            }

            //firstResult(o)
            //maxResults(m)
        }
        results
    }

}
