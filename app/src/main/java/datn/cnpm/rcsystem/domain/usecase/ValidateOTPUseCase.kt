package datn.cnpm.rcsystem.domain.usecase

import datn.cnpm.rcsystem.core.Result
import datn.cnpm.rcsystem.core.di.IoDispatcher
import datn.cnpm.rcsystem.data.entitiy.ValidateOTPRequest
import datn.cnpm.rcsystem.data.repository.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


interface ValidateOTPUseCase {
    data class Parameters(
        val email: String, val OTP: String
    )

    suspend fun validateOTP(parameters: Parameters): Result<String>
}

class ValidateOTPUseCaseImpl @Inject constructor(
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val authenticationRepository: AuthenticationRepository,
) : BaseUseCase<ValidateOTPUseCase.Parameters, String>(ioDispatcher),
    ValidateOTPUseCase {

    override suspend fun execute(parameters: ValidateOTPUseCase.Parameters): String {
        return authenticationRepository.validateOTP(
            ValidateOTPRequest(
                parameters.email,
                parameters.OTP
            )
        )
    }

    override suspend fun validateOTP(parameters: ValidateOTPUseCase.Parameters): Result<String> {
        return invoke(parameters)
    }
}