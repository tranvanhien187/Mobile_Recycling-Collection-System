package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject

interface CompleteTransportFormUseCase {
    data class Parameters(
        val evidence: File,
        val weight: String,
        val historyId: String,
        )

    suspend fun completeTransportGarbageForm(parameters: Parameters): Result<String>
}

class CompleteTransportFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<CompleteTransportFormUseCase.Parameters, String>(ioDispatcher),
    CompleteTransportFormUseCase {

    override suspend fun execute(parameters: CompleteTransportFormUseCase.Parameters): String {
        return CRGSRepository.completeTransportGarbageForm(
            parameters.evidence,
            parameters.weight,
            parameters.historyId
        )
    }

    override suspend fun completeTransportGarbageForm(parameters: CompleteTransportFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}