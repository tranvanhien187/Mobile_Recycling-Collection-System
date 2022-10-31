package datn.cnpm.rcsystem.domain.usecase.transport

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface CreateTransportGiftFormUseCase {
    data class Parameters(
        val giftId: String,
        val street: String,
        val district: String,
        val cityOrProvince: String,
    )

    suspend fun createTransportGiftForm(parameters: Parameters): Result<String>
}

class CreateTransportGiftFormUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<CreateTransportGiftFormUseCase.Parameters, String>(
    ioDispatcher
), CreateTransportGiftFormUseCase {

    override suspend fun execute(parameters: CreateTransportGiftFormUseCase.Parameters): String {
        return CRGSRepository.createTransportGiftForm(
            parameters.giftId,
            parameters.street,
            parameters.district,
            parameters.cityOrProvince
        )
    }

    override suspend fun createTransportGiftForm(parameters: CreateTransportGiftFormUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}