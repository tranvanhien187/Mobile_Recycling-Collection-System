package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.entitiy.GetTradingPlaceCriteria
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity
import datn.cnpm.rcsystem.domain.model.history.mapToTPlaceForUserEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetTPlaceForUserUseCase {
    data class Parameters(
        val criteria: GetTradingPlaceCriteria = GetTradingPlaceCriteria.None
    )

    suspend fun getTPlaceForUser(parameters: Parameters): Result<List<TradingPlaceForUserEntity>>
}

class GetTPlaceForUserUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetTPlaceForUserUseCase.Parameters, List<TradingPlaceForUserEntity>>(ioDispatcher),
    GetTPlaceForUserUseCase {

    override suspend fun execute(parameters: GetTPlaceForUserUseCase.Parameters): List<TradingPlaceForUserEntity> {
        return CRGSRepository.getTPlaceForUser(
            parameters.criteria.toString()

        ).map { it.mapToTPlaceForUserEntity() }
    }

    override suspend fun getTPlaceForUser(parameters: GetTPlaceForUserUseCase.Parameters): Result<List<TradingPlaceForUserEntity>> {
        return invoke(parameters)
    }
}