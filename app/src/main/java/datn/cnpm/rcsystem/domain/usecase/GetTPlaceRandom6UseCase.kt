package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity
import datn.cnpm.rcsystem.domain.model.history.mapToTPlaceForUserEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetTPlaceRandom6UseCase {
    class Parameters()

    suspend fun getTPlaceRandom6(parameters: Parameters = Parameters()): Result<List<TradingPlaceForUserEntity>>
}

class GetTPlaceRandom6UseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetTPlaceRandom6UseCase.Parameters, List<TradingPlaceForUserEntity>>(ioDispatcher),
    GetTPlaceRandom6UseCase {

    override suspend fun execute(parameters: GetTPlaceRandom6UseCase.Parameters): List<TradingPlaceForUserEntity> {
        return CRGSRepository.getTPlaceRandom6().map { it.mapToTPlaceForUserEntity() }
    }

    override suspend fun getTPlaceRandom6(parameters: GetTPlaceRandom6UseCase.Parameters): Result<List<TradingPlaceForUserEntity>> {
        return invoke(parameters)
    }
}