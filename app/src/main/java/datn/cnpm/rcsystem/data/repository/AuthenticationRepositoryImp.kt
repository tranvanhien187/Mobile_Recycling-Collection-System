package datn.cnpm.rcsystem.data.repository

import datn.cnpm.rcsystem.common.ErrorCode
import datn.cnpm.rcsystem.common.exception.BadRequestException
import datn.cnpm.rcsystem.data.datastore.AuthenticationDataSource
import datn.cnpm.rcsystem.data.entitiy.*
import datn.cnpm.rcsystem.local.sharepreferences.AuthPreference
import javax.inject.Inject

/*
 * Created by ADMIN on 7/24/2022.
 * Project : Base
 */

class AuthenticationRepositoryImp @Inject constructor(private val authenticationDataSource: AuthenticationDataSource, private val authPre : AuthPreference) :
    AuthenticationRepository {

    override suspend fun login(request: LoginRequest, isRemember: Boolean): LoginResponse {
        val response = authenticationDataSource.login(request)
        if (response.isSuccess) {
            authPre.role = response.requireData.role
            authPre.uuid = response.requireData.uuid
            return response.requireData
        } else {
            throw Exception(response.requireError.toString())
        }
    }

    override suspend fun register(request: RegisterRequest): String {
        val response = authenticationDataSource.register(request)
        if (response.isSuccess) {
            return response.requireData
        } else if(response.isFailure) {
            if(response.requireError == ErrorCode.UNKNOWN_ERROR) {
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
}