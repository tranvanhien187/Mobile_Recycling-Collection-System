package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.entitiy.ReceiveFormRequest
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface ReceiveTransportFormUseCase {
    data class Parameters(
        val historyGarbageId: String,
        val customerName: String,
        val customerPhoneNumber: String,
    )

    suspend fun receiveTransportForm(parameters: Parameters): Result<String>
}

class ReceiveTransportFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<ReceiveTransportFormUseCase.Parameters, String>(
    ioDispatcher
), ReceiveTransportFormUseCase {

    override suspend fun execute(parameters: ReceiveTransportFormUseCase.Parameters): String {
        return authenticationRepository.receiveTransportForm(
            parameters.historyGarbageId,
            parameters.customerName,
            parameters.customerPhoneNumber
        )

    }

    override suspend fun receiveTransportForm(parameters: ReceiveTransportFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}