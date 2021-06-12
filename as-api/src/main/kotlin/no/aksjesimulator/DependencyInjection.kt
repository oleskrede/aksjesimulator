package no.aksjesimulator

import no.aksjesimulator.application.Aksjesimulator
import no.aksjesimulator.application.Login
import no.aksjesimulator.application.interfaces.AksjesimRepositoryInterface
import no.aksjesimulator.infrastructure.repository.stubs.AksjesimRepositoryStub

class DependencyInjection(
    var aksjesimRepository: AksjesimRepositoryInterface = AksjesimRepositoryStub()
) {
    var aksjesimulator = Aksjesimulator(aksjesimRepository)
    var login = Login(aksjesimRepository)
}
