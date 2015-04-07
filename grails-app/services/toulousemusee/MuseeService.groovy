package toulousemusee

import grails.transaction.Transactional

@Transactional
class MuseeService {

    List<Musee> searchMusee(String name, String codeP, String adress) {
        def m = Musee.createCriteria()
        List<Musee> results = m.list {
            if (name) {
                    like("nom", "%$name%")
            }

            if (codeP) {
                adresse {
                    like("codePostal", codeP.toInteger())
                }
            }

            if (adress) {
                adresse {
                    like("rue", "%$adress%")
                }
            }
        }
        results
    }

}
