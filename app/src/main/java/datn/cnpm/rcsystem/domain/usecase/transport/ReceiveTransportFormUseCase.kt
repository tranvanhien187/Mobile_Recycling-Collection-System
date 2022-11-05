package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface ReceiveTransportFormUseCase {
    data class Parameters(
        val historyGarbageId: String,
        val customerName: String,
        val customerPhoneNumber: String,
    )

    suspend fun receiveTransportGarbageForm(parameters: Parameters): Result<String>
}

class ReceiveTransportFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<ReceiveTransportFormUseCase.Parameters, String>(
    ioDispatcher
), ReceiveTransportFormUseCase {

    override suspend fun execute(parameters: ReceiveTransportFormUseCase.Parameters): String {
        return CRGSRepository.receiveTransportGarbageForm(
            parameters.historyGarbageId,
            parameters.customerName,
            parameters.customerPhoneNumber
        )

    }

    override suspend fun receiveTransportGarbageForm(parameters: ReceiveTransportFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}