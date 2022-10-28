package datn.cnpm.rcsystem.domain.usecase.statistic

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectWeightByDayEntity
import datn.cnpm.rcsystem.domain.model.statistic.mapToEntity
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetStatisticStaffCollection7DayUseCase {
    data class Parameters(
        val staffId: String
    )

    suspend fun getStatisticsStaffCollectLast7Days(parameters: Parameters): Result<List<StatisticStaffCollectWeightByDayEntity>>
}

class GetStatisticStaffCollection7DayUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetStatisticStaffCollection7DayUseCase.Parameters, List<StatisticStaffCollectWeightByDayEntity>>(
    ioDispatcher
),
    GetStatisticStaffCollection7DayUseCase {

    override suspend fun execute(parameters: GetStatisticStaffCollection7DayUseCase.Parameters): List<StatisticStaffCollectWeightByDayEntity> {
        return CRGSRepository.getStatisticsStaffCollectLast7Days(parameters.staffId)
            .map { it.mapToEntity() }
    }

    override suspend fun getStatisticsStaffCollectLast7Days(parameters: GetStatisticStaffCollection7DayUseCase.Parameters): Result<List<StatisticStaffCollectWeightByDayEntity>> {
        return invoke(parameters)
    }
}
