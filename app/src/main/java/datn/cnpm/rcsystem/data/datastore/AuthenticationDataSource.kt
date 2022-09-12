package datn.cnpm.rcsystem.data.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.entitiy.*
import javax.inject.Inject

class AuthenticationDataSource @Inject constructor(private val authenticationApiService: AuthenticationApiService) {
    suspend fun login(request: LoginRequest) : SBResponse<LoginResponse> {
        return authenticationApiService.login(request)
    }

    suspend fun register(request: RegisterRequest) : SBResponse<String>{
        return authenticationApiService.register(request)
    }

    suspend fun changePassword(request: ChangePasswordRequest) : SBResponse<String>{
        return authenticationApiService.changePassword(request)
    }

    suspend fun genOTP(request: GenOTPRequest) : SBResponse<String>{
        return authenticationApiService.genOTP(request)
    }

    suspend fun validateOTP(request: ValidateOTPRequest) : SBResponse<String>{
        return authenticationApiService.validateOTP(request)
    }
}