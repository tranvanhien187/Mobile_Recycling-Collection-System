package datn.cnpm.rcsystem.data.repository

import datn.cnpm.rcsystem.data.entitiy.*

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

interface AuthenticationRepository {
    suspend fun login(request: LoginRequest) : LoginResponse
    suspend fun register(request: RegisterRequest): String
    suspend fun changePassword(request: ChangePasswordRequest): String
    suspend fun genOTP(request: GenOTPRequest): String
    suspend fun validateOTP(request: ValidateOTPRequest): String
}