package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity
import datn.cnpm.rcsystem.domain.model.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetGarbageUserHistoryUseCase {
    class Parameters()

    suspend fun getGarbageUserHistory(parameters: Parameters = Parameters()): Result<List<GarbageUserHistoryEntity>>
}

class GetGarbageUserHistoryUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetGarbageUserHistoryUseCase.Parameters, List<GarbageUserHistoryEntity>>(
    ioDispatcher
),
    GetGarbageUserHistoryUseCase {

    override suspend fun execute(parameters: GetGarbageUserHistoryUseCase.Parameters): List<GarbageUserHistoryEntity> {
        return CRGSRepository.getGarbageUserHistory().map { it.mapToEntity() }
    }

    override suspend fun getGarbageUserHistory(parameters: GetGarbageUserHistoryUseCase.Parameters): Result<List<GarbageUserHistoryEntity>> {
        return invoke(parameters)
    }
}