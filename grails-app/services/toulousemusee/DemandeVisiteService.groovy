package toulousemusee

import grails.transaction.Transactional

@Transactional
class DemandeVisiteService {

    DemandeVisite insertOrUpdateDemandeVisiteForMusee(DemandeVisite demandeVisite, Musee musee) {
        musee.addToDemandeVisites(demandeVisite)
        musee.save(flush: true)

        demandeVisite
    }

    DemandeVisite insertOrUpdateDemandeVisiteForMusees(DemandeVisite demandeVisite, List<Musee> musees) {
        println "MUSEES!"
        println(musees?.get(0))
        println "size " + Integer.toString(musees.size())
        println demandeVisite.code
        musees.each {it.addToDemandeVisites(demandeVisite)
            it.save(flush: true)}
        demandeVisite
    }

    void deleteDemandeVisite(DemandeVisite demandeVisite) {
        demandeVisite.musees.each {it.removeFromDemandeVisites(demandeVisite)}
        demandeVisite.delete()
    }
}
