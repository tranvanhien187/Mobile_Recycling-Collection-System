package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.entitiy.GenOTPRequest
import datn.cnpm.rcsystem.data.entitiy.OTPType
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface GenOTPUseCase {
    data class Parameters(
        val email: String, val type: OTPType
    )

    suspend fun genOTP(parameters: Parameters): Result<String>
}

class GenOTPUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<GenOTPUseCase.Parameters, String>(ioDispatcher),
    GenOTPUseCase {

    override suspend fun execute(parameters: GenOTPUseCase.Parameters): String {
        return authenticationRepository.genOTP(
            GenOTPRequest(
                parameters.email,
                parameters.type.value
            )
        )
    }

    override suspend fun genOTP(parameters: GenOTPUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}