package datn.cnpm.rcsystem.data.repository

import datn.cnpm.rcsystem.data.entitiy.*

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

interface AuthenticationRepository {
    suspend fun login(request: LoginRequest, isRemember: Boolean): LoginResponse
    suspend fun register(request: RegisterRequest): RegisterResponse
    suspend fun changePassword(request: ChangePasswordRequest): String
    suspend fun updateUserInfo(request: UpdateUserInfoRequest): UpdateUserInfoResponse
    suspend fun getTPlaceForUser(criteria: String): List<GetTPPlaceForUserResponse>
    suspend fun genOTP(request: GenOTPRequest): String
    suspend fun validateOTP(request: ValidateOTPRequest): String
    suspend fun getUserInfo(): GetUserInfoResponse
    suspend fun getTPlaceRandom6(): List<GetTPPlaceForUserResponse>
    suspend fun getGiftRandom6(): List<GiftResponse>
    suspend fun getGiftUserHistory(): List<GiftUserHistoryResponse>
    suspend fun getGarbageUserHistory(): List<GarbageUserHistoryResponse>
    suspend fun receiveTransportForm(
        historyGarbageId: String,
        customerName: String,
        customerPhoneNumber: String
    ): String
}