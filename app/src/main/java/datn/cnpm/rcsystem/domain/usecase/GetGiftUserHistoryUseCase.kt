package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity
import datn.cnpm.rcsystem.domain.model.mapToEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GetGiftUserHistoryUseCase {
    class Parameters()

    suspend fun getGiftUserHistory(parameters: Parameters = Parameters()): Result<List<GiftUserHistoryEntity>>
}

class GetGiftUserHistoryUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<GetGiftUserHistoryUseCase.Parameters, List<GiftUserHistoryEntity>>(ioDispatcher),
    GetGiftUserHistoryUseCase {

    override suspend fun execute(parameters: GetGiftUserHistoryUseCase.Parameters): List<GiftUserHistoryEntity> {
        return authenticationRepository.getGiftUserHistory().map { it.mapToEntity() }
    }

    override suspend fun getGiftUserHistory(parameters: GetGiftUserHistoryUseCase.Parameters): Result<List<GiftUserHistoryEntity>> {
        return invoke(parameters)
    }
}