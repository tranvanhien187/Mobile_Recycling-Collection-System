package datn.cnpm.rcsystem.domain.usecase.history

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.history.GarbageHistoryDetailEntity
import datn.cnpm.rcsystem.domain.model.history.mapToEntity
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetGarbageHistoryDetailUseCase {
    data class Parameters(val historyId: String)

    suspend fun getGarbageHistoryDetail(parameters: Parameters): Result<GarbageHistoryDetailEntity>
}

class GetGarbageHistoryDetailUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetGarbageHistoryDetailUseCase.Parameters, GarbageHistoryDetailEntity>(
    ioDispatcher
),
    GetGarbageHistoryDetailUseCase {

    override suspend fun execute(parameters: GetGarbageHistoryDetailUseCase.Parameters): GarbageHistoryDetailEntity {
        return CRGSRepository.getGarbageHistoryDetail(parameters.historyId).mapToEntity()
    }

    override suspend fun getGarbageHistoryDetail(parameters: GetGarbageHistoryDetailUseCase.Parameters): Result<GarbageHistoryDetailEntity> {
        return invoke(parameters)
    }
}