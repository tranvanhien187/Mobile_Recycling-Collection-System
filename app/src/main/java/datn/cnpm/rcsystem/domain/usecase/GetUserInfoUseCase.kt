package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.domain.model.UserEntity
import datn.cnpm.rcsystem.domain.model.mapToUserEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface GetUserInfoUseCase {
    class Parameters()

    suspend fun getUserInfo(parameters: Parameters = Parameters() ): Result<UserEntity>
}

class GetUserInfoUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<GetUserInfoUseCase.Parameters, UserEntity>(ioDispatcher),
    GetUserInfoUseCase {

    override suspend fun execute(parameters: GetUserInfoUseCase.Parameters): UserEntity {
        return CRGSRepository.getUserInfo().mapToUserEntity()
    }

    override suspend fun getUserInfo(parameters: GetUserInfoUseCase.Parameters): Result<UserEntity> {
        return invoke(parameters)
    }
}