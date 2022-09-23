package datn.cnpm.rcsystem.data.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.entitiy.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface AuthenticationApiService {
    @POST("/api/v1/login")
    suspend fun login(@Body request: LoginRequest): SBResponse<LoginResponse>

    @POST("/api/v1/register")
    suspend fun register(@Body request: RegisterRequest): SBResponse<RegisterResponse>

    @POST("/api/v1/resetpassword")
    suspend fun changePassword(@Body request: ChangePasswordRequest): SBResponse<String>

    @POST("/api/v1/otp/gen")
    suspend fun genOTP(@Body request: GenOTPRequest): SBResponse<String>

    @POST("/api/v1/otp/validate")
    suspend fun validateOTP(@Body request: ValidateOTPRequest): SBResponse<String>

    @Multipart
    @POST("/api/v1/user/updateInfo")
    suspend fun updateUserInfo(
        @Part avatar: MultipartBody.Part,
        @Part("id") uuid: RequestBody,
        @Part("name") name: RequestBody,
        @Part("phoneNumber") phoneNumber: RequestBody,
        @Part("identityCardNumber") ICN: RequestBody,
        @Part("dayOfBirth") dOB: RequestBody,
        @Part("street") street: RequestBody,
        @Part("district") district: RequestBody,
        @Part("provinceOrCity") provinceOrCity: RequestBody,
    ): SBResponse<UpdateUserInfoResponse>

    @GET("api/v1/user/{id}")
    suspend fun getUserInfo(@Path("id") uuid: String): SBResponse<GetUserInfoResponse>

    @GET("api/v1/tplace/get/{id}")
    suspend fun getTPlaceForUser(@Path("id") id: String, @Query("criteria") criteria: String): SBResponse<List<GetTPPlaceForUserResponse>>

    @GET("api/v1/tplace/get/random/{id}")
    suspend fun getTPlaceRandom6(@Path("id") id: String): SBResponse<List<GetTPPlaceForUserResponse>>

    @GET("/api/v1/gift/userhistory/{id}")
    suspend fun getGiftUserHistory(@Path("id") uuid: String): SBResponse<List<GiftUserHistoryResponse>>

    @GET("/api/v1/garbage/userhistory/{id}")
    suspend fun getGarbageUserHistory(@Path("id") uuid: String): SBResponse<List<GarbageUserHistoryResponse>>
}