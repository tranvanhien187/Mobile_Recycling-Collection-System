package datn.cnpm.rcsystem.data.repository

import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.common.exception.BadRequestException
import datn.cnpm.rcsystem.data.datastore.CRGSDataSource
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
import datn.cnpm.rcsystem.data.entitiy.transport.ReceiveFormRequest
import datn.cnpm.rcsystem.domain.model.history.BaseItemHistory
import datn.cnpm.rcsystem.domain.model.history.GiftHistoryDetailResponse
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import java.io.File
import javax.inject.Inject

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

class CRGSRepositoryImp @Inject constructor(
    private val authenticationDataSource: CRGSDataSource,
    private val authPre: AuthPreference
) :
    CRGSRepository {

    override suspend fun login(request: LoginRequest, isRemember: Boolean): LoginResponse {
        val response = authenticationDataSource.login(request)
        if (response.isSuccess) {
            authPre.role = response.requireData.role
            authPre.accessToken = response.requireData.accessToken
            authPre.id = response.requireData.id
            authPre.isRememberMe = isRemember
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw BadRequestException(ErrorCode.UNKNOWN_ERROR)
            } else {
                throw Exception()
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val response = authenticationDataSource.register(request)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun changePassword(request: ChangePasswordRequest): String {
        val response = authenticationDataSource.changePassword(request)
        if (response.isSuccess) {
            return response.requireData
        } else {
            throw Exception(response.requireError.toString())
        }
    }

    override suspend fun updateUserInfo(request: UpdateUserInfoRequest): UpdateUserInfoResponse {
        val response = authenticationDataSource.updateUserInfo(request)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getTPlaceForUser(criteria: String): List<GetTPPlaceForUserResponse> {
        val response = authenticationDataSource.getTPlaceForUser(authPre.id, criteria)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGiftByCriteria(criteria: String): List<GiftResponse> {
        val response = authenticationDataSource.getGiftByCriteria(authPre.id, criteria)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun genOTP(request: GenOTPRequest): String {
        val response = authenticationDataSource.genOTP(request)
        if (response.isSuccess) {
            return response.requireData
        } else {
            throw Exception(response.requireError.toString())
        }
    }

    override suspend fun validateOTP(request: ValidateOTPRequest): String {
        val response = authenticationDataSource.validateOTP(request)
        if (response.isSuccess) {
            return response.requireData
        } else {
            throw Exception(response.requireError.toString())
        }
    }

    override suspend fun getCustomerInfo(): GetCustomerInfoResponse {
        val response = authenticationDataSource.getCustomerInfo(authPre.id)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getTPlaceRandom6(): List<GetTPPlaceForUserResponse> {
        val response = authenticationDataSource.getTPlaceRandom6(authPre.id)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGiftRandom6(): List<GiftResponse> {
        val response = authenticationDataSource.getGiftRandom6(authPre.id)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGiftUserHistory(): List<GiftUserHistoryResponse> {
        val response = authenticationDataSource.getGiftUserHistory(authPre.id)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun createTransportGarbageForm(
        exchangeWeight: Float,
        street: String,
        district: String,
        cityOrProvince: String,
    ): String {
        val response = authenticationDataSource.createTransportGarbageForm(
            CreateTransportGarbageRequest(
                authPre.id, exchangeWeight,
                street,
                district,
                cityOrProvince
            )
        )
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun createTransportGiftForm(
        giftId: String,
        street: String,
        district: String,
        cityOrProvince: String,
    ): String {
        val response = authenticationDataSource.createTransportGiftForm(
            CreateTransportGiftRequest(
                giftId,
                authPre.id,
                street,
                district,
                cityOrProvince
            )
        )
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun receiveTransportForm(
        historyGarbageId: String,
        customerName: String,
        customerPhoneNumber: String
    ): String {
        val response = authenticationDataSource.receiveTransportForm(
            ReceiveFormRequest(
                authPre.id, historyGarbageId,
                customerName,
                customerPhoneNumber
            )
        )
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun completeTransportGarbageForm(
        evidence: File,
        weight: String,
        formId: String
    ): String {
        val response = authenticationDataSource.completeTransportGarbageForm(
            evidence, weight, authPre.id, formId
        )
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getStaffInfo(): StaffInfoResponse {
        val response = authenticationDataSource.getStaffInfo(authPre.id)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getListGarbageHistory(): List<BaseItemHistory> {
        val response = when (authPre.role) {
            Role.CUSTOMER.name -> {
                authenticationDataSource.getListGarbageHistoryByCustomer(authPre.id)
            }
            Role.AGENT.name -> {
                authenticationDataSource.getListGarbageHistoryByAgent(authPre.id)
            }
            Role.STAFF.name -> {
                authenticationDataSource.getListGarbageHistoryByStaff(authPre.id)
            }
            else -> {
                throw BadRequestException(ErrorCode.NOT_FIND_ROLE)
            }
        }

        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getListGiftHistory(): List<BaseItemHistory> {
        val response = when (authPre.role) {
            Role.CUSTOMER.name -> {
                authenticationDataSource.getListGiftHistoryByCustomer(authPre.id)
            }
            Role.AGENT.name -> {
                authenticationDataSource.getListGiftHistoryByAgent(authPre.id)
            }
            Role.STAFF.name -> {
                authenticationDataSource.getListGiftHistoryByStaff(authPre.id)
            }
            else -> {
                throw BadRequestException(ErrorCode.NOT_FIND_ROLE)
            }
        }

        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGiftHistoryDetail(historyId: String): GiftHistoryDetailResponse {
        val response = authenticationDataSource.getGiftHistoryDetail(historyId)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGarbageHistoryDetail(historyId: String): GarbageHistoryDetailResponse {
        val response = authenticationDataSource.getGarbageHistoryDetail(historyId)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getTPlaceDetail(tplaceId: String): TPlaceDetailResponse {
        val response = authenticationDataSource.getTPlaceDetail(tplaceId)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGiftDetail(giftId: String): GiftDetailResponse {
        val response = authenticationDataSource.getGiftDetail(giftId)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getGiftOwnerByAgent(
        ownerId: String,
        criteria: String
    ): List<GiftResponse> {
        val response = authenticationDataSource.getGiftOwnerByAgent(ownerId, criteria)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getStatisticsStaffCollectLast7Days(staffId: String): List<StatisticStaffCollectWeightByDayResponse> {
        val response = authenticationDataSource.getStatisticsStaffCollectLast7Days(staffId)
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }

    override suspend fun getStatisticTopStaffCollect(): List<StatisticStaffCollectResponse> {
        val response = authenticationDataSource.getStatisticTopStaffCollect()
        if (response.isSuccess) {
            return response.requireData
        } else if (response.isFailure) {
            if (response.requireError == ErrorCode.UNKNOWN_ERROR) {
                throw Exception(ErrorCode.UNKNOWN_ERROR.value)
            } else {
                throw BadRequestException(response.requireError)
            }
        } else {
            throw Exception(ErrorCode.UNKNOWN_ERROR.value)
        }
    }
}