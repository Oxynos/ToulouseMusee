package toulousemusee

import org.apache.commons.lang.RandomStringUtils

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteController {

    DemandeVisiteService demandeVisiteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(DemandeVisite demandeVisiteInstance) {
        respond demandeVisiteInstance
    }

    def demande() {
        respond new DemandeVisite(params)
    }

    @Transactional
    def soumettreDemande(DemandeVisite demandeVisiteInstance) {


        demandeVisiteService.createCodeForDemandeVisite(demandeVisiteInstance)
        demandeVisiteInstance.statut = "en cours"

        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteInstance.hasErrors()) {
            if(demandeVisiteInstance.finPeriode < demandeVisiteInstance.debutPeriode) {
                flash.message = "demande.invalid.enddate.size.message"
                flash.args = [demandeVisiteInstance.finPeriode.dateString, demandeVisiteInstance.debutPeriode.dateString]
            } else {
                flash.message = "demande.invalid.begindate.size.message"
                flash.args = [demandeVisiteInstance.debutPeriode.dateString, (new Date()).dateString]
            }
            flash.default = "Date invalide"
            respond demandeVisiteInstance.errors, view: 'demande'
            return
        }

        demandeVisiteService.insertOrUpdateDemandeVisiteForMusees(demandeVisiteInstance, session["musees"])

        respond demandeVisiteInstance

    }
}
