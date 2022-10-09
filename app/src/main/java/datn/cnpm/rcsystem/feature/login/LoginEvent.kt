package datn.cnpm.rcsystem.feature.login

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface LoginEvent {
    data class ValidateField(
        val errorUsername: ErrorCode? = null,
        val errorPassword: ErrorCode? = null,
    ) : LoginEvent

    object UserLoginSuccess : LoginEvent
    data class UserUpdatedYet(val uuid: String) : LoginEvent
    object AgentLoginSuccess : LoginEvent
    object StaffLoginSuccess : LoginEvent
    data class LoginFailure(val message: String) : LoginEvent
}