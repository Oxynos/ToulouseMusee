import toulousemusee.JeuTestService

class BootStrap {

    JeuTestService jeuTestService

    def init = { servletContext ->
        jeuTestService.createJeuTest()
        /*
        jeuTestService.createJeuTestForGestionnaires()
        jeuTestService.createJeuTestForMusee()
        */
    }
    def destroy = {
    }
}
