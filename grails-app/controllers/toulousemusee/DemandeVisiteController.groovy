package toulousemusee

import org.apache.commons.lang.RandomStringUtils

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DemandeVisiteController {

    DemandeVisiteService demandeVisiteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond DemandeVisite.list(params), model: [demandeVisiteInstanceCount: DemandeVisite.count()]
    }

    def show(DemandeVisite demandeVisiteInstance) {
        respond demandeVisiteInstance
    }

    def create() {
        respond new DemandeVisite(params)
    }

    @Transactional
    def save(DemandeVisite demandeVisiteInstance) {
        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        demandeVisiteInstance.statut

        if (demandeVisiteInstance.hasErrors()) {
            respond demandeVisiteInstance.errors, view: 'create'
            return
        }

        //demandeVisiteInstance.save flush: true
        demandeVisiteService.insertOrUpdateDemandeVisiteForMusees(demandeVisiteInstance, demandeVisiteInstance.musees.asList())

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'demandeVisite.label', default: 'DemandeVisite'), demandeVisiteInstance.id])
                redirect demandeVisiteInstance
            }
            '*' { respond demandeVisiteInstance, [status: CREATED] }
        }
    }

    def edit(DemandeVisite demandeVisiteInstance) {
        respond demandeVisiteInstance
    }

    @Transactional
    def update(DemandeVisite demandeVisiteInstance) {
        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        if (demandeVisiteInstance.hasErrors()) {
            respond demandeVisiteInstance.errors, view: 'edit'
            return
        }

        //demandeVisiteInstance.save flush: true
        demandeVisiteService.insertOrUpdateDemandeVisiteForMusees(demandeVisiteInstance,demandeVisiteInstance.musees.asList())

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'DemandeVisite.label', default: 'DemandeVisite'), demandeVisiteInstance.id])
                redirect demandeVisiteInstance
            }
            '*' { respond demandeVisiteInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(DemandeVisite demandeVisiteInstance) {

        if (demandeVisiteInstance == null) {
            notFound()
            return
        }

        //demandeVisiteInstance.delete flush: true
        demandeVisiteService.deleteDemandeVisite(demandeVisiteInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'DemandeVisite.label', default: 'DemandeVisite'), demandeVisiteInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'demandeVisite.label', default: 'DemandeVisite'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
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
