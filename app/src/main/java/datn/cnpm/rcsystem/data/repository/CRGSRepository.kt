package datn.cnpm.rcsystem.data.repository

import datn.cnpm.rcsystem.data.entitiy.*
import datn.cnpm.rcsystem.data.entitiy.gift.GiftDetailResponse
import datn.cnpm.rcsystem.data.entitiy.gift.GiftResponse
import datn.cnpm.rcsystem.data.entitiy.staff.StaffInfoResponse
import datn.cnpm.rcsystem.data.entitiy.tplace.TPlaceDetailResponse
import datn.cnpm.rcsystem.domain.model.history.BaseItemHistory
import datn.cnpm.rcsystem.domain.model.history.GiftHistoryDetailResponse

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

interface CRGSRepository {
    suspend fun login(request: LoginRequest, isRemember: Boolean): LoginResponse
    suspend fun register(request: RegisterRequest): RegisterResponse
    suspend fun changePassword(request: ChangePasswordRequest): String
    suspend fun updateUserInfo(request: UpdateUserInfoRequest): UpdateUserInfoResponse
    suspend fun getTPlaceForUser(criteria: String): List<GetTPPlaceForUserResponse>
    suspend fun genOTP(request: GenOTPRequest): String
    suspend fun validateOTP(request: ValidateOTPRequest): String
    suspend fun getCustomerInfo(): GetCustomerInfoResponse
    suspend fun getTPlaceRandom6(): List<GetTPPlaceForUserResponse>
    suspend fun getGiftRandom6(): List<GiftResponse>
    suspend fun getGiftUserHistory(): List<GiftUserHistoryResponse>
    // STAFF
    suspend fun receiveTransportForm(
        historyGarbageId: String,
        customerName: String,
        customerPhoneNumber: String
    ): String

    suspend fun getStaffInfo(): StaffInfoResponse

    suspend fun getListGarbageHistory(): List<BaseItemHistory>
    suspend fun getListGiftHistory(): List<BaseItemHistory>
    suspend fun getGiftHistoryDetail(historyId: String): GiftHistoryDetailResponse
    suspend fun getGarbageHistoryDetail(historyId: String): GarbageHistoryDetailResponse
    suspend fun getTPlaceDetail(tplaceId: String): TPlaceDetailResponse

    suspend fun getGiftByCriteria(criteria: String): List<GiftResponse>
    suspend fun getGiftDetail(giftId: String): GiftDetailResponse

    /*** TRANSPORT FORM  ***/
    suspend fun createTransportGarbageForm(
        exchangeWeight: Float,
        street: String,
        district: String,
        cityOrProvince: String,
    ): String
}