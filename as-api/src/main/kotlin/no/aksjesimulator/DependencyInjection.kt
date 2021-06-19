package no.aksjesimulator

import no.aksjesimulator.application.Aksjesimulator
import no.aksjesimulator.application.Auth
import no.aksjesimulator.application.AuthInterface
import no.aksjesimulator.application.AuthStub
import no.aksjesimulator.application.interfaces.IAksjesimRepository
import no.aksjesimulator.infrastructure.repository.stubs.AksjesimRepositoryStub

class DependencyInjection(
    aksjesimRepository: IAksjesimRepository = AksjesimRepositoryStub()
) {
    var aksjesimulator: Aksjesimulator
    var login: AuthInterface

    init {
        // TODO check profile parameter to decide which kinds of dependencies to use
        aksjesimulator = Aksjesimulator(aksjesimRepository)
        login = Auth(aksjesimRepository)
        login = AuthStub()
    }
}
