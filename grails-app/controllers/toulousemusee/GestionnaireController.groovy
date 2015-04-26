package toulousemusee



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GestionnaireController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def show(Gestionnaire gestionnaireInstance) {
        respond gestionnaireInstance
    }
}
