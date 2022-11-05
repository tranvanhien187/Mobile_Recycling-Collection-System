package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.entitiy.GetTradingPlaceCriteria
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.gift.GiftEntity
import datn.cnpm.rcsystem.domain.model.gift.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetGiftByCriteriaUseCase {
    data class Parameters(
        val criteria: GetTradingPlaceCriteria = GetTradingPlaceCriteria.None
    )

    suspend fun getGiftByCriteria(parameters: Parameters = Parameters()): Result<List<GiftEntity>>
}

class GetGiftByCriteriaUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetGiftByCriteriaUseCase.Parameters, List<GiftEntity>>(ioDispatcher),
    GetGiftByCriteriaUseCase {

    override suspend fun execute(parameters: GetGiftByCriteriaUseCase.Parameters): List<GiftEntity> {
        return CRGSRepository.getGiftByCriteria(parameters.criteria.name).map { it.mapToEntity() }
    }

    override suspend fun getGiftByCriteria(parameters: GetGiftByCriteriaUseCase.Parameters): Result<List<GiftEntity>> {
        return invoke(parameters)
    }
}