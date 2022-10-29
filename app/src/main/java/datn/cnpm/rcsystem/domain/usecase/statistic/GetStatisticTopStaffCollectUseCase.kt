package datn.cnpm.rcsystem.domain.usecase.statistic

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectEntity
import datn.cnpm.rcsystem.domain.model.statistic.mapToEntity
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetStatisticTopStaffCollectUseCase {
    class Parameters()

    suspend fun getStatisticTopStaffCollect(parameters: Parameters): Result<List<StatisticStaffCollectEntity>>
}

class GetStatisticTopStaffCollectUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetStatisticTopStaffCollectUseCase.Parameters, List<StatisticStaffCollectEntity>>(
    ioDispatcher
),
    GetStatisticTopStaffCollectUseCase {

    override suspend fun execute(parameters: GetStatisticTopStaffCollectUseCase.Parameters): List<StatisticStaffCollectEntity> {
        return CRGSRepository.getStatisticTopStaffCollect()
            .map { it.mapToEntity() }
    }

    override suspend fun getStatisticTopStaffCollect(parameters: GetStatisticTopStaffCollectUseCase.Parameters): Result<List<StatisticStaffCollectEntity>> {
        return invoke(parameters)
    }
}