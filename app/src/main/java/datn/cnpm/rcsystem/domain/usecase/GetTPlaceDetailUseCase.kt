package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.tplace.TradingPlaceEntity
import datn.cnpm.rcsystem.domain.model.tplace.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetTPlaceDetailUseCase {
    data class Parameters(
        val tplaceId: String
    )

    suspend fun getTPlaceDetail(parameters: Parameters): Result<TradingPlaceEntity>
}

class GetTPlaceDetailUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetTPlaceDetailUseCase.Parameters, TradingPlaceEntity>(ioDispatcher),
    GetTPlaceDetailUseCase {

    override suspend fun execute(parameters: GetTPlaceDetailUseCase.Parameters): TradingPlaceEntity {
        return CRGSRepository.getTPlaceDetail(
            parameters.tplaceId
        ).mapToEntity()
    }

    override suspend fun getTPlaceDetail(parameters: GetTPlaceDetailUseCase.Parameters): Result<TradingPlaceEntity> {
        return invoke(parameters)
    }
}