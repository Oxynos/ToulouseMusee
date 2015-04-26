package toulousemusee



import grails.test.mixin.*
import spock.lang.*

@TestFor(MuseeController)
@Mock(Musee)
class MuseeControllerSpec extends Specification {

    def myService = new MuseeService()

    def populateValidParams(params) {
        assert params != null
        params["nom"] = "nom"
        params["horairesOuverture"] = "horaires"
        params["telephone"] = "telephone"
        params["accesMetro"] = "accesM"
        params["accesBus"] = "accesBus"
        params["adresse"] = Mock(Adresse)
        params["gestionnaire"] = Mock(Gestionnaire)
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def musee = new Musee(params)
            controller.show(musee)

        then:"A model is populated containing the domain instance"
            model.museeInstance == musee
    }

    void "Test that the doResearch search musee"(){
        setup:
        controller.museeService = myService

        when: "the doResearch action is executed"
            populateValidParams(params)
            def musee = new Musee(params)
            musee.save(flush: true)
            controller.doResearch()

        then:"a museum is returned"
        model.museeInstanceCount == 1
    }

    void "Test that the addMusee add Musee"() {
        when: "the addMusee is executed"
            populateValidParams(params)
            def musee = new Musee(params)
            musee.save(flush: true)
            params.id = musee.id
            controller.addMusee()

        then:"a museum is added to session"
            session["musees"] != null

        and: "there is 1 museum in session"
        session["musees"].size() == 1
    }

    void "Test that the addMusee add Musee in session existing"() {
        when: "the addMusee is executed"
        populateValidParams(params)
        def musee = new Musee(params)
        musee.save(flush: true)
        def musee2 = new Musee(params)
        musee2.save(flush: true)
        session["musees"] = [musee]
        params.id = musee2.id
        controller.addMusee()

        then:"a museum is added to session"
        session["musees"] != null

        and: "there is 2 museums in session"
        session["musees"].size() == 2
    }

    void "Test that the removeMusee remove Musee in session"() {
        when: "the addMusee is executed"
        populateValidParams(params)
        def musee = new Musee(params)
        musee.save(flush: true)
        session["musees"] = [musee]
        params.idFavourite = musee.id
        controller.removeMusee()

        then:"there is 0 museums in session"
        session["musees"].size() == 0
    }
}
