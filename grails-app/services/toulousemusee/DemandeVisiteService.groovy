package toulousemusee

import grails.transaction.Transactional
import org.apache.commons.lang.RandomStringUtils

@Transactional
class DemandeVisiteService {

    DemandeVisite insertOrUpdateDemandeVisiteForMusee(DemandeVisite demandeVisite, Musee musee) {
        musee.addToDemandeVisites(demandeVisite)
        musee.save(flush: true)

        demandeVisite
    }

    DemandeVisite insertOrUpdateDemandeVisiteForMusees(DemandeVisite demandeVisite, List<Musee> musees) {
        musees.each {it.addToDemandeVisites(demandeVisite)
            it.save(flush: true)}
        demandeVisite
    }

    void deleteDemandeVisite(DemandeVisite demandeVisite) {
        demandeVisite.musees.each {it.removeFromDemandeVisites(demandeVisite)}
        demandeVisite.delete()
    }

    def createCodeForDemandeVisite(DemandeVisite demandeVisite) {
        String charset = (('A'..'Z') + ('0'..'9')).join()
        String code = RandomStringUtils.random(8, charset)
        demandeVisite.code = code
    }
}
