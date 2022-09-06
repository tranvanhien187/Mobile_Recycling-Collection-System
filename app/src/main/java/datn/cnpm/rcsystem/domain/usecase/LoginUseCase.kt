package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.authentication.repository.AuthenticationRepository
import datn.cnpm.rcsystem.domain.model.Product
import datn.cnpm.rcsystem.domain.model.toProduct
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

interface LoginUseCase {
    data class Parameters(
        val email: String, val password: String
    )

    suspend fun login(parameters: Parameters): Result<List<Product>>
}

class LoginUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<LoginUseCase.Parameters, List<Product>>(ioDispatcher),
    LoginUseCase {

    override suspend fun execute(parameters: LoginUseCase.Parameters): List<Product> {
        return authenticationRepository.login().map { it.toProduct() }
    }

    override suspend fun login(parameters: LoginUseCase.Parameters): Result<List<Product>> {
        return invoke(parameters)
    }
}