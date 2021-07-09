package no.aksjesimulator

import no.aksjesimulator.application.Aksjesimulator
import no.aksjesimulator.application.AuthInterface
import no.aksjesimulator.application.AuthStub
import no.aksjesimulator.application.interfaces.IAksjesimRepository
import no.aksjesimulator.infrastructure.repository.stubs.AksjesimRepositoryStub

internal object Dependencies {
    val aksjesimRepository: IAksjesimRepository = AksjesimRepositoryStub()
    val aksjesimulator: Aksjesimulator = Aksjesimulator(aksjesimRepository)
//    login = Auth(aksjesimRepository)
    val login: AuthInterface = AuthStub()

//    init { }
}
