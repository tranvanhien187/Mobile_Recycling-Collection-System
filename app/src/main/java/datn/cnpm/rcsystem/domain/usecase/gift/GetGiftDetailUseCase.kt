package datn.cnpm.rcsystem.domain.usecase.gift

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.gift.GiftDetailEntity
import datn.cnpm.rcsystem.domain.model.gift.mapToEntity
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetGiftDetailUseCase {
    data class Parameters(
        val giftId: String
    )

    suspend fun getGiftDetail(parameters: Parameters): Result<GiftDetailEntity>
}

class GetGiftDetailUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetGiftDetailUseCase.Parameters, GiftDetailEntity>(ioDispatcher),
    GetGiftDetailUseCase {

    override suspend fun execute(parameters: GetGiftDetailUseCase.Parameters): GiftDetailEntity {
        return CRGSRepository.getGiftDetail(
            parameters.giftId
        ).mapToEntity()
    }

    override suspend fun getGiftDetail(parameters: GetGiftDetailUseCase.Parameters): Result<GiftDetailEntity> {
        return invoke(parameters)
    }
}