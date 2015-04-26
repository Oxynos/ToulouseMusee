package toulousemusee



import grails.test.mixin.*
import spock.lang.*

@TestFor(GestionnaireController)
@Mock(Gestionnaire)
class GestionnaireControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["nom"] = 'someValidName'
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def gestionnaire = new Gestionnaire(params)
            controller.show(gestionnaire)

        then:"A model is populated containing the domain instance"
            model.gestionnaireInstance == gestionnaire
    }
}
