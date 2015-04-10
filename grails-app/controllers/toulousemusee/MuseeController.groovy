package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseeService museeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = 5
        respond Musee.list(params), model: [museeInstanceCount: Musee.count()]
    }

    def show(Musee museeInstance) {
        respond museeInstance
    }

    def create() {
        respond new Musee(params)
    }

    @Transactional
    def save(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view: 'create'
            return
        }

        museeService.insertOrUpdateMuseeForGestionnaire(museeInstance, museeInstance.gestionnaire)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'musee.label', default: 'Musee'), museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: CREATED] }
        }
    }

    def edit(Musee museeInstance) {
        respond museeInstance
    }

    @Transactional
    def update(Musee museeInstance) {
        if (museeInstance == null) {
            notFound()
            return
        }

        if (museeInstance.hasErrors()) {
            respond museeInstance.errors, view: 'edit'
            return
        }

        //museeInstance.save flush: true
        museeService.insertOrUpdateMuseeForGestionnaire(museeInstance, museeInstance.gestionnaire)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Musee.label', default: 'Musee'), museeInstance.id])
                redirect museeInstance
            }
            '*' { respond museeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Musee museeInstance) {

        if (museeInstance == null) {
            notFound()
            return
        }

        museeService.deleteMusee(museeInstance)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Musee.label', default: 'Musee'), museeInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'musee.label', default: 'Musee'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }

    def doResearch() {
        List<Musee> musees = museeService.searchMusee(params.musee, params.codePostal, params.adresseMusee)
        //render(view: '../index', model: [museeInstanceList: musees, museeInstanceCount: musees.size()])
        respond musees
    }

    def addMusee() {
        List<Musee> musees = session["musees"]
        if (musees){
            musees.add(Musee.findById(params.id))
        }else{
            session["musees"] = new ArrayList<Musee>()
            session["musees"].add(Musee.findById(params.id))
        }

        println session["musees"]?.get(0).nom + Musee.findById(1)
        println session["musees"].contains(Musee.findById(1))
        println session["musees"].contains(Musee.findById(params.id))
        redirect(uri: '/')
    }
}
