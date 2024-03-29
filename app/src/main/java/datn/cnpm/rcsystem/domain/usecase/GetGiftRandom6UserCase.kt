package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetGiftRandom6UserCase {
    class Parameters()

    suspend fun getGiftRandom6(parameters: Parameters = Parameters()): Result<List<GiftEntity>>
}

class GetGiftRandom6UserCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<GetGiftRandom6UserCase.Parameters, List<GiftEntity>>(ioDispatcher),
    GetGiftRandom6UserCase {

    override suspend fun execute(parameters: GetGiftRandom6UserCase.Parameters): List<GiftEntity> {
        return authenticationRepository.getGiftRandom6().map { it.mapToEntity() }
    }

    override suspend fun getGiftRandom6(parameters: GetGiftRandom6UserCase.Parameters): Result<List<GiftEntity>> {
        return invoke(parameters)
    }
}