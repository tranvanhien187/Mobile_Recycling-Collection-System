package datn.cnpm.rcsystem.domain.usecase.gift

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.gift.GiftEntity
import datn.cnpm.rcsystem.domain.model.gift.mapToEntity
import datn.cnpm.rcsystem.domain.usecase.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetGiftOwnerByAgentUseCase {
    data class Parameters(
        val ownerId: String, val criteria: String,
    )

    suspend fun getGiftOwnerByAgent(parameters: Parameters): Result<List<GiftEntity>>
}

class GetGiftOwnerByAgentUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetGiftOwnerByAgentUseCase.Parameters, List<GiftEntity>>(ioDispatcher),
    GetGiftOwnerByAgentUseCase {

    override suspend fun execute(parameters: GetGiftOwnerByAgentUseCase.Parameters): List<GiftEntity> {
        return CRGSRepository.getGiftOwnerByAgent(parameters.ownerId, parameters.criteria)
            .map { it.mapToEntity() }
    }

    override suspend fun getGiftOwnerByAgent(parameters: GetGiftOwnerByAgentUseCase.Parameters): Result<List<GiftEntity>> {
        return invoke(parameters)
    }
}

