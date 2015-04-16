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
        musees.each {
            demandeVisite.addToMusees(it)
        }
        demandeVisite.save()
        demandeVisite
    }

    void deleteDemandeVisite(DemandeVisite demandeVisite) {
        demandeVisite.musees.each {it.removeFromDemandeVisites(demandeVisite)}
        demandeVisite.delete()
    }
}
