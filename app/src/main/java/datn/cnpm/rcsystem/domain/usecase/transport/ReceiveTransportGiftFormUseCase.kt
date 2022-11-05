package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface ReceiveTransportGiftFormUseCase {
    data class Parameters(
        val historyGiftId: String,
        val customerName: String,
        val customerPhoneNumber: String,
    )

    suspend fun receiveTransportGiftForm(parameters: Parameters): Result<String>
}

class ReceiveTransportGiftFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<ReceiveTransportGiftFormUseCase.Parameters, String>(
    ioDispatcher
), ReceiveTransportGiftFormUseCase {

    override suspend fun execute(parameters: ReceiveTransportGiftFormUseCase.Parameters): String {
        return CRGSRepository.receiveTransportGiftForm(
            parameters.historyGiftId,
            parameters.customerName,
            parameters.customerPhoneNumber
        )

    }

    override suspend fun receiveTransportGiftForm(parameters: ReceiveTransportGiftFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}