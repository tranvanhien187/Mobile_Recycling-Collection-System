package datn.cnpm.rcsystem.data.datastore

import datn.cnpm.rcsystem.core.SBResponse
import datn.cnpm.rcsystem.data.entitiy.*
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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface CRGSApiService {
    @POST("/api/v1/login")
    suspend fun login(@Body request: LoginRequest): SBResponse<LoginResponse>

    @POST("/api/v1/register")
    suspend fun register(@Body request: RegisterRequest): SBResponse<RegisterResponse>

    @POST("/api/v1/change-password")
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


    /***        CUSTOMER      ***/
    @GET("api/v1/customer/{id}")
    suspend fun getCustomerInfo(@Path("id") uuid: String): SBResponse<GetCustomerInfoResponse>

    @GET("/api/v1/history/garbage/{id}")
    suspend fun getListGarbageHistoryByCustomer(@Path("id") id: String, @Query("page") page: Int = 0): SBResponse<List<ItemGarbageHistoryByCustomerEntity>>

    @GET("/api/v1/history/gift/{id}")
    suspend fun getListGiftHistoryByCustomer(@Path("id") id: String, @Query("page") page: Int = 0): SBResponse<List<ItemGiftHistoryByCustomerEntity>>

    /******/

    @GET("api/v1/tplace/get/{id}")
    suspend fun getTPlaceForUser(
        @Path("id") id: String,
        @Query("criteria") criteria: String
    ): SBResponse<List<GetTPPlaceForUserResponse>>

    /***        GIFT      ***/
    @GET("api/v1/gift/get/{id}")
    suspend fun getGiftByCriteria(
        @Path("id") id: String,
        @Query("criteria") criteria: String
    ): SBResponse<List<GiftResponse>>

    @GET("api/v1/gift/detail/{id}")
    suspend fun getGiftDetail(@Path("id") id: String): SBResponse<GiftDetailResponse>

    /******/

    @GET("api/v1/tplace/get/random/{id}")
    suspend fun getTPlaceRandom6(@Path("id") id: String): SBResponse<List<GetTPPlaceForUserResponse>>


    @GET("api/v1/tplace/detail/{id}")
    suspend fun getTPlaceDetail(@Path("id") id: String): SBResponse<TPlaceDetailResponse>

    @GET("api/v1/gift/get/random/{id}")
    suspend fun getGiftRandom6(@Path("id") id: String): SBResponse<List<GiftResponse>>

    /***        STAFF      ***/

    @GET("/api/v1/staff/{id}")
    suspend fun getStaffInfo(@Path("id") id: String): SBResponse<StaffInfoResponse>

    @GET("/api/v1/history/garbage/{id}")
    suspend fun getListGarbageHistoryByStaff(@Path("id") id: String, @Query("page") page: Int = 0): SBResponse<List<ItemGarbageHistoryByStaffEntity>>

    @GET("/api/v1/history/gift/{id}")
    suspend fun getListGiftHistoryByStaff(@Path("id") id: String, @Query("page") page: Int = 0): SBResponse<List<ItemGiftHistoryByStaffEntity>>

    /******/


    @GET("/api/v1/history/gift/details/{id}")
    suspend fun getGiftHistoryDetail(@Path("id") historyId: String): SBResponse<GiftHistoryDetailResponse>

    @GET("/api/v1/history/garbage/details/{id}")
    suspend fun getGarbageHistoryDetail(@Path("id") historyId: String): SBResponse<GarbageHistoryDetailResponse>

    /***        TRANSPORT FORM      ***/
    @POST("/api/v1/transport/garbage/createForm")
    suspend fun createTransportGarbageForm(@Body request: CreateTransportGarbageRequest): SBResponse<String>

    @POST("/api/v1/transport/garbage/receiveForm")
    suspend fun receiveTransportForm(@Body request: ReceiveFormGarbageRequest): SBResponse<String>

    @Multipart
    @POST("/api/v1/transport/garbage/completeForm")
    suspend fun completeTransportGarbageForm(
        @Part evidence: MultipartBody.Part,
        @Part("weight") weight: RequestBody,
        @Part("staffId") id: RequestBody,
        @Part("formId") formId: RequestBody,
    ): SBResponse<String>

    @POST("/api/v1/transport/gift/createForm")
    suspend fun createTransportGiftForm(@Body request: CreateTransportGiftRequest): SBResponse<String>

    @POST("/api/v1/transport/gift/receiveForm")
    suspend fun receiveTransportGiftForm(@Body request: ReceiveFormGiftRequest): SBResponse<String>

    @Multipart
    @POST("/api/v1/transport/gift/completeForm")
    suspend fun completeTransportGiftForm(
        @Part evidence: MultipartBody.Part,
        @Part("staffId") id: RequestBody,
        @Part("formId") formId: RequestBody,
    ): SBResponse<String>
    /******/



    @GET("/api/v1/gift/get/owner/{ownerId}")
    suspend fun getGiftOwnerByAgent(
        @Path("ownerId") ownerId: String,
        @Query("criteria") criteria: String,
    ): SBResponse<List<GiftResponse>>


    /***        STATISTIC      ***/
    @GET("/api/v1/statistics/totalweight/7days/{id}")
    suspend fun getStatisticsStaffCollectLast7Days(@Path("id") staffId: String): SBResponse<List<StatisticStaffCollectWeightByDayResponse>>

    @GET("/api/v1/statistics/top-staff")
    suspend fun getStatisticTopStaffCollect(): SBResponse<List<StatisticStaffCollectResponse>>
    /******/
}
