package datn.cnpm.rcsystem.feature.register

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface RegisterEvent {
    data class ValidateField(
        val errorEmail: ErrorCode? = null,
        val errorUsername: ErrorCode? = null,
        val errorPassword: ErrorCode? = null,
        val errorRetypePassword: ErrorCode? = null
    ) : RegisterEvent
    data class RegisterFailure(
        val error: String
    ) : RegisterEvent
    data class RegisterSuccess(
        val uuid: String
    )  : RegisterEvent
}


