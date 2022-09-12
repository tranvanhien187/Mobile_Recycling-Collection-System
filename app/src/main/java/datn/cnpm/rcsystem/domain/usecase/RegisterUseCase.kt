package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import datn.cnpm.rcsystem.data.entitiy.RegisterRequest
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface RegisterUseCase {
    data class Parameters(
        val username: String,
        val email: String,
        val password: String,
        val role: String,
    )

    suspend fun register(parameters: Parameters): Result<String>
}

class RegisterUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<RegisterUseCase.Parameters, String>(ioDispatcher),
    RegisterUseCase {

    override suspend fun execute(parameters: RegisterUseCase.Parameters): String {
        return authenticationRepository.register(
            RegisterRequest(
                parameters.username,
                parameters.email,
                parameters.password,
                parameters.role
            )
        )
    }

    override suspend fun register(parameters: RegisterUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}