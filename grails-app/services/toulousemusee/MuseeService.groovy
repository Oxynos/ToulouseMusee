package toulousemusee

import org.springframework.transaction.annotation.Transactional


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
        // note
        // le flush: true n'est pas nécessaire pour que le test d'intégration passe mais est
        // nécessaire pour que l'appel venant du contrôleur fonctionne comme attendu
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
        def m = Musee.createCriteria()
        List<Musee> results = m.list {
            if (name) {
                if (codeP) {
                    adresse {
                        like("codePostal", "$codeP")
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
                    like("codePostal", "$codeP")
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
                        like("codePostal", "$codeP")
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
