package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject


interface CompleteTransportGiftFormUseCase {
    data class Parameters(
        val evidence: File,
        val historyId: String,
    )

    suspend fun completeTransportGarbageForm(parameters: Parameters): Result<String>
}

class CompleteTransportGiftFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<CompleteTransportGiftFormUseCase.Parameters, String>(ioDispatcher),
    CompleteTransportGiftFormUseCase {

    override suspend fun execute(parameters: CompleteTransportGiftFormUseCase.Parameters): String {
        return CRGSRepository.completeTransportGiftForm(
            parameters.evidence,
            parameters.historyId
        )
    }

    override suspend fun completeTransportGarbageForm(parameters: CompleteTransportGiftFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}