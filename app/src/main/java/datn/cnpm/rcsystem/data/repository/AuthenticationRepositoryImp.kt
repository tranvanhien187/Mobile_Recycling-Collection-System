package datn.cnpm.rcsystem.data.repository

import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.common.exception.BadRequestException
import datn.cnpm.rcsystem.data.datastore.AuthenticationDataSource
import datn.cnpm.rcsystem.data.entitiy.*
import datn.cnpm.rcsystem.data.entitiy.staff.StaffInfoResponse
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

class AuthenticationRepositoryImp @Inject constructor(
    private val authenticationDataSource: AuthenticationDataSource,
    private val authPre: AuthPreference
) :
    AuthenticationRepository {

    override suspend fun login(request: LoginRequest, isRemember: Boolean): LoginResponse {
        val response = authenticationDataSource.login(request)
        if (response.isSuccess) {
            authPre.role = response.requireData.role
            authPre.accessToken = response.requireData.accessToken
            authPre.uuid = response.requireData.uuid
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
        val response = authenticationDataSource.getTPlaceForUser(authPre.uuid, criteria)
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

    override suspend fun getUserInfo(): GetUserInfoResponse {
        val response = authenticationDataSource.getUserInfo(authPre.uuid)
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
        val response = authenticationDataSource.getTPlaceRandom6(authPre.uuid)
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
        val response = authenticationDataSource.getGiftRandom6(authPre.uuid)
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
        val response = authenticationDataSource.getGiftUserHistory(authPre.uuid)
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

    override suspend fun getGarbageUserHistory(): List<GarbageUserHistoryResponse> {
        val response = authenticationDataSource.getGarbageUserHistory(authPre.uuid)
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
                authPre.uuid, historyGarbageId,
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

    override suspend fun getStaffInfo(): StaffInfoResponse {
        val response = authenticationDataSource.getStaffInfo(authPre.uuid)
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