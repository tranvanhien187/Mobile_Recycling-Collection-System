package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface CreateTransportGarbageFormUseCase {
    data class Parameters(
        val exchangeWeight: Float,
        val street: String,
        val district: String,
        val cityOrProvince: String,
    )

    suspend fun createTransportGarbageForm(parameters: Parameters): Result<String>
}

class CreateTransportGarbageFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<CreateTransportGarbageFormUseCase.Parameters, String>(
    ioDispatcher
), CreateTransportGarbageFormUseCase {

    override suspend fun execute(parameters: CreateTransportGarbageFormUseCase.Parameters): String {
        return CRGSRepository.createTransportGarbageForm(
            parameters.exchangeWeight,
            parameters.street,
            parameters.district,
            parameters.cityOrProvince
        )
    }

    override suspend fun createTransportGarbageForm(parameters: CreateTransportGarbageFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}