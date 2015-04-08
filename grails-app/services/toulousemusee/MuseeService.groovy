package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {
    /**
     * Cree un nouveau musee
     * @param unMusee le musee
     * @param unGestionnaire le gestionnaire du musee
     * @return le musee créé
     */
    Musee insertOrUpdateMuseeForGestionnaire(Musee unMusee, Gestionnaire unGestionnaire) {
        unGestionnaire.addToMusees(unMusee)
        println(unMusee.nom +" " +  unGestionnaire.nom)
        unGestionnaire.save(flush: true)
        unMusee
    }

    /**
     * Supprime un musee
     * @param unMusee le musee à supprimer
     */
    void deleteMusee(Musee unMusee) {
        unMusee.gestionnaire.removeFromMusees(unMusee)
        unMusee.delete()
    }

    List<Musee> searchMusee(String name, String codeP, String adress) {
        def m = Musee.createCriteria()
        List<Musee> results = m.list {
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
            }

            if (codeP) {
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
            }

            if (adress) {
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
        }
        results
    }

}
