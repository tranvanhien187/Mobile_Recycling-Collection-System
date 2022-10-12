package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.CRGSRepository
import datn.cnpm.rcsystem.data.entitiy.ChangePasswordRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface ChangePasswordUseCase {
    data class Parameters(
        val email: String,
        val password: String,
    )

    suspend fun changePassword(parameters: Parameters): Result<String>
}

class ChangePasswordUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val CRGSRepository: CRGSRepository,
) : BaseUseCase<ChangePasswordUseCase.Parameters, String>(ioDispatcher),
    ChangePasswordUseCase {

    override suspend fun execute(parameters: ChangePasswordUseCase.Parameters): String {
        return CRGSRepository.changePassword(
            ChangePasswordRequest(
                parameters.email,
                parameters.password,
            )
        )
    }

    override suspend fun changePassword(parameters: ChangePasswordUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}