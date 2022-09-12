package datn.cnpm.rcsystem.data.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.entitiy.*
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApiService {
    @POST("/api/v1/login")
    suspend fun login(@Body request: LoginRequest) : SBResponse<LoginResponse>

    @POST("/api/v1/register")
    suspend fun register(@Body request: RegisterRequest) : SBResponse<String>

    @POST("/api/v1/resetpassword")
    suspend fun changePassword(@Body request: ChangePasswordRequest) : SBResponse<String>

    @POST("/api/v1/otp/gen")
    suspend fun genOTP(@Body request: GenOTPRequest) : SBResponse<String>

    @POST("/api/v1/otp/validate")
    suspend fun validateOTP(@Body request: ValidateOTPRequest) : SBResponse<String>
}