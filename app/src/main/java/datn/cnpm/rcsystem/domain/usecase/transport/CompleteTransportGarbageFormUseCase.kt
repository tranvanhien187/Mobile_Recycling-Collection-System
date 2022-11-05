package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject

interface CompleteTransportGarbageFormUseCase {
    data class Parameters(
        val evidence: File,
        val weight: String,
        val historyId: String,
        )

    suspend fun completeTransportGarbageForm(parameters: Parameters): Result<String>
}

class CompleteTransportGarbageFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<CompleteTransportGarbageFormUseCase.Parameters, String>(ioDispatcher),
    CompleteTransportGarbageFormUseCase {

    override suspend fun execute(parameters: CompleteTransportGarbageFormUseCase.Parameters): String {
        return CRGSRepository.completeTransportGarbageForm(
            parameters.evidence,
            parameters.weight,
            parameters.historyId
        )
    }

    override suspend fun completeTransportGarbageForm(parameters: CompleteTransportGarbageFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}