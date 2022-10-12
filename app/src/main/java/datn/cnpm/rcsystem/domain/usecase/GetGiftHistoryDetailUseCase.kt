package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.history.GiftHistoryDetailEntity
import datn.cnpm.rcsystem.domain.model.history.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetGiftHistoryDetailUseCase {
    data class Parameters(val historyId: String)

    suspend fun getGiftHistoryDetail(parameters: Parameters): Result<GiftHistoryDetailEntity>
}

class GetGiftHistoryDetailUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetGiftHistoryDetailUseCase.Parameters, GiftHistoryDetailEntity>(
    ioDispatcher
),
    GetGiftHistoryDetailUseCase {

    override suspend fun execute(parameters: GetGiftHistoryDetailUseCase.Parameters): GiftHistoryDetailEntity {
        return CRGSRepository.getGiftHistoryDetail(parameters.historyId).mapToEntity()
    }

    override suspend fun getGiftHistoryDetail(parameters: GetGiftHistoryDetailUseCase.Parameters): Result<GiftHistoryDetailEntity> {
        return invoke(parameters)
    }
}