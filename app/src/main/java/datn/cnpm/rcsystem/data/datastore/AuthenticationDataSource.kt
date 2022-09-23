package datn.cnpm.rcsystem.data.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.entitiy.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

class AuthenticationDataSource @Inject constructor(private val authenticationApiService: AuthenticationApiService) {
    suspend fun login(request: LoginRequest) : SBResponse<LoginResponse> {
        return authenticationApiService.login(request)
    }

    suspend fun register(request: RegisterRequest) : SBResponse<RegisterResponse>{
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

    suspend fun updateUserInfo(request: UpdateUserInfoRequest) : SBResponse<UpdateUserInfoResponse>{
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
        return authenticationApiService.updateUserInfo(avatar, uuid, name,phoneNumber,ICN,dOB,street,district, provinceOrCity)
    }

    suspend fun getUserInfo(uuid: String): SBResponse<GetUserInfoResponse> {
        return authenticationApiService.getUserInfo(uuid)
    }

    suspend fun getTPlaceForUser(uuid: String, criteria: String): SBResponse<List<GetTPPlaceForUserResponse>> {
        return authenticationApiService.getTPlaceForUser(uuid, criteria)
    }

    suspend fun getTPlaceRandom6(uuid: String): SBResponse<List<GetTPPlaceForUserResponse>> {
        return authenticationApiService.getTPlaceRandom6(uuid)
    }

    suspend fun getGiftUserHistory(uuid: String): SBResponse<List<GiftUserHistoryResponse>> {
        return authenticationApiService.getGiftUserHistory(uuid)
    }

    suspend fun getGarbageUserHistory(uuid: String): SBResponse<List<GarbageUserHistoryResponse>> {
        return authenticationApiService.getGarbageUserHistory(uuid)
    }
}