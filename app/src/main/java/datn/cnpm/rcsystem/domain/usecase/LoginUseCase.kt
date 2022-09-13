package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import datn.cnpm.rcsystem.data.entitiy.LoginRequest
import datn.cnpm.rcsystem.data.entitiy.LoginResponse
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

interface LoginUseCase {
    data class Parameters(
        val email: String, val password: String, val isRemember: Boolean
    )

    suspend fun login(parameters: Parameters): Result<LoginResponse>
}

class LoginUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<LoginUseCase.Parameters, LoginResponse>(ioDispatcher),
    LoginUseCase {

    override suspend fun execute(parameters: LoginUseCase.Parameters): LoginResponse {
        return authenticationRepository.login(LoginRequest(parameters.email, parameters.password), parameters.isRemember)
    }

    override suspend fun login(parameters: LoginUseCase.Parameters): Result<LoginResponse> {
        return invoke(parameters)
    }
}