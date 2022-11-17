package datn.cnpm.rcsystem.data.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.entitiy.*
import datn.cnpm.rcsystem.data.entitiy.agent.AgentInfoResponse
import datn.cnpm.rcsystem.data.entitiy.gift.GiftDetailResponse
import datn.cnpm.rcsystem.data.entitiy.gift.GiftResponse
import datn.cnpm.rcsystem.data.entitiy.history.GarbageHistoryDetailResponse
import datn.cnpm.rcsystem.data.entitiy.staff.StaffInfoResponse
import datn.cnpm.rcsystem.data.entitiy.statistic.StatisticStaffCollectResponse
import datn.cnpm.rcsystem.data.entitiy.statistic.StatisticStaffCollectWeightByDayResponse
import datn.cnpm.rcsystem.data.entitiy.tplace.TPlaceDetailResponse
import datn.cnpm.rcsystem.data.entitiy.transport.CreateTransportGarbageRequest
import datn.cnpm.rcsystem.data.entitiy.transport.CreateTransportGiftRequest
import datn.cnpm.rcsystem.data.entitiy.transport.ReceiveFormGarbageRequest
import datn.cnpm.rcsystem.data.entitiy.transport.ReceiveFormGiftRequest
import datn.cnpm.rcsystem.domain.model.history.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class CRGSDataSource @Inject constructor(private val authenticationApiService: CRGSApiService) {
    suspend fun login(request: LoginRequest): SBResponse<LoginResponse> {
        return authenticationApiService.login(request)
    }

    suspend fun register(request: RegisterRequest): SBResponse<RegisterResponse> {
        return authenticationApiService.register(request)
    }

    suspend fun changePassword(request: ChangePasswordRequest): SBResponse<String> {
        return authenticationApiService.changePassword(request)
    }

    suspend fun genOTP(request: GenOTPRequest): SBResponse<String> {
        return authenticationApiService.genOTP(request)
    }

    suspend fun validateOTP(request: ValidateOTPRequest): SBResponse<String> {
        return authenticationApiService.validateOTP(request)
    }

    suspend fun updateUserInfo(request: UpdateUserInfoRequest): SBResponse<UpdateUserInfoResponse> {
        val name = request.name.toRequestBody("text/plain".toMediaTypeOrNull())
        val uuid = request.uuid.toRequestBody("text/plain".toMediaTypeOrNull())
        val phoneNumber = request.phoneNumber.toRequestBody("text/plain".toMediaTypeOrNull())
        val dOB = request.dOB.toRequestBody("text/plain".toMediaTypeOrNull())
        val ICN = request.ICN.toRequestBody("text/plain".toMediaTypeOrNull())
        val street = request.street.toRequestBody("text/plain".toMediaTypeOrNull())
        val district = request.district.toRequestBody("text/plain".toMediaTypeOrNull())
        val provinceOrCity = request.provinceOrCity.toRequestBody("text/plain".toMediaTypeOrNull())

        val avatar: MultipartBody.Part = MultipartBody.Part.createFormData(
            "avatar",
            request.avatar.name,
            request.avatar.asRequestBody("image/*".toMediaTypeOrNull())
        )
        return authenticationApiService.updateUserInfo(
            avatar,
            uuid,
            name,
            phoneNumber,
            ICN,
            dOB,
            street,
            district,
            provinceOrCity
        )
    }

    suspend fun getCustomerInfo(uuid: String): SBResponse<GetCustomerInfoResponse> {
        return authenticationApiService.getCustomerInfo(uuid)
    }

    suspend fun getTPlaceForUser(
        uuid: String,
        criteria: String,
    ): SBResponse<List<GetTPPlaceForUserResponse>> {
        return authenticationApiService.getTPlaceForUser(uuid, criteria)
    }

    suspend fun getGiftByCriteria(
        uuid: String,
        criteria: String,
    ): SBResponse<List<GiftResponse>> {
        return authenticationApiService.getGiftByCriteria(uuid, criteria)
    }

    suspend fun getTPlaceRandom6(uuid: String): SBResponse<List<GetTPPlaceForUserResponse>> {
        return authenticationApiService.getTPlaceRandom6(uuid)
    }

    suspend fun getGiftRandom6(uuid: String): SBResponse<List<GiftResponse>> {
        return authenticationApiService.getGiftRandom6(uuid)
    }

    suspend fun createTransportGarbageForm(request: CreateTransportGarbageRequest): SBResponse<String> {
        return authenticationApiService.createTransportGarbageForm(request)
    }

    suspend fun createTransportGiftForm(request: CreateTransportGiftRequest): SBResponse<String> {
        return authenticationApiService.createTransportGiftForm(request)
    }

    suspend fun receiveTransportForm(request: ReceiveFormGarbageRequest): SBResponse<String> {
        return authenticationApiService.receiveTransportForm(request)
    }

    suspend fun receiveTransportGiftForm(request: ReceiveFormGiftRequest): SBResponse<String> {
        return authenticationApiService.receiveTransportGiftForm(request)
    }

    suspend fun completeTransportGarbageForm(evidenceFile: File,
                                             weight: String,
                                             staffId: String,
                                             formId: String): SBResponse<String> {

        val weightParam = weight.toRequestBody("text/plain".toMediaTypeOrNull())
        val staffIdParam = staffId.toRequestBody("text/plain".toMediaTypeOrNull())
        val formIdParam = formId.toRequestBody("text/plain".toMediaTypeOrNull())
        val evidence: MultipartBody.Part = MultipartBody.Part.createFormData(
            "evidence",
            evidenceFile.name,
            evidenceFile.asRequestBody("image/*".toMediaTypeOrNull())
        )
        return authenticationApiService.completeTransportGarbageForm(
            evidence,
            weightParam,
            staffIdParam,
            formIdParam
        )
    }

    suspend fun completeTransportGiftForm(evidenceFile: File,
                                             staffId: String,
                                             formId: String): SBResponse<String> {

        val staffIdParam = staffId.toRequestBody("text/plain".toMediaTypeOrNull())
        val formIdParam = formId.toRequestBody("text/plain".toMediaTypeOrNull())
        val evidence: MultipartBody.Part = MultipartBody.Part.createFormData(
            "evidence",
            evidenceFile.name,
            evidenceFile.asRequestBody("image/*".toMediaTypeOrNull())
        )
        return authenticationApiService.completeTransportGiftForm(
            evidence,
            staffIdParam,
            formIdParam
        )
    }

    suspend fun getStaffInfo(id: String): SBResponse<StaffInfoResponse> {
        return authenticationApiService.getStaffInfo(id)
    }

    suspend fun getAgentInfo(id: String): SBResponse<AgentInfoResponse> {
        return authenticationApiService.getAgentInfo(id)
    }

    suspend fun getListGarbageHistoryByCustomer(id: String): SBResponse<List<ItemGarbageHistoryByCustomerEntity>> {
        return authenticationApiService.getListGarbageHistoryByCustomer(id)
    }

    suspend fun getListGarbageHistoryByStaff(id: String): SBResponse<List<ItemGarbageHistoryByStaffEntity>> {
        return authenticationApiService.getListGarbageHistoryByStaff(id)
    }

    suspend fun getListGiftHistoryByCustomer(id: String): SBResponse<List<ItemGiftHistoryByCustomerEntity>> {
        return authenticationApiService.getListGiftHistoryByCustomer(id)
    }

    suspend fun getListGiftHistoryByStaff(id: String): SBResponse<List<ItemGiftHistoryByStaffEntity>> {
        return authenticationApiService.getListGiftHistoryByStaff(id)
    }

    suspend fun getGiftHistoryDetail(historyId: String): SBResponse<GiftHistoryDetailResponse> {
        return authenticationApiService.getGiftHistoryDetail(historyId)
    }

    suspend fun getGarbageHistoryDetail(historyId: String): SBResponse<GarbageHistoryDetailResponse> {
        return authenticationApiService.getGarbageHistoryDetail(historyId)
    }

    suspend fun getTPlaceDetail(tplaceId: String): SBResponse<TPlaceDetailResponse> {
        return authenticationApiService.getTPlaceDetail(tplaceId)
    }

    suspend fun getGiftDetail(tplaceId: String): SBResponse<GiftDetailResponse> {
        return authenticationApiService.getGiftDetail(tplaceId)
    }

    suspend fun getGiftOwnerByAgent(ownerId: String, criteria: String): SBResponse<List<GiftResponse>> {
        return authenticationApiService.getGiftOwnerByAgent(ownerId, criteria)
    }

    suspend fun getStatisticsStaffCollectLast7Days(staffId: String): SBResponse<List<StatisticStaffCollectWeightByDayResponse>> {
        return authenticationApiService.getStatisticsStaffCollectLast7Days(staffId)
    }

    suspend fun getStatisticTopStaffCollect(): SBResponse<List<StatisticStaffCollectResponse>> {
        return authenticationApiService.getStatisticTopStaffCollect()
    }
}
