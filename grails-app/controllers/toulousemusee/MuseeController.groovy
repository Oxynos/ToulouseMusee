package toulousemusee


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class MuseeController {

    MuseeService museeService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Musee museeInstance) {
        respond museeInstance
    }

    def doResearch() {
        List<Musee> musees = museeService.searchMusee(params.musee, params.codePostal, params.adresseMusee)
        respond musees, model: [museeInstanceCount: musees.size()]
    }

    def addMusee() {
        List<Musee> musees = session["musees"]
        if (musees){
            musees.add(Musee.findById(params.id))
        }else{
            session["musees"] = new ArrayList<Musee>()
            session["musees"].add(Musee.findById(params.id))
        }
        redirect(uri: '/')
    }

    def removeMusee() {
        List<Musee> musees = session["musees"]

        musees.remove(Musee.findById(params.idFavourite))
        redirect(uri: '/')
    }
}
