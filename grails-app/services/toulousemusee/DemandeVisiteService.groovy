package toulousemusee

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    DemandeVisite insertOrUpdateDemandeVisiteForMusee(DemandeVisite demandeVisite, Musee musee) {
        demandeVisite.addToMusees(musee)
        demandeVisite.save(flush: true)

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
}
