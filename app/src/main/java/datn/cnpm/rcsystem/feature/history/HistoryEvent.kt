package datn.cnpm.rcsystem.feature.history

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface HistoryEvent {
    data class ValidateField(
        val errorUsername: ErrorCode? = null,
        val errorPassword: ErrorCode? = null,
    ) : HistoryEvent

    object UserLoginSuccess : HistoryEvent
    data class UserUpdatedYet(val uuid: String) : HistoryEvent
    object DealerLoginSuccess : HistoryEvent
    data class LoginFailure(val message: String) : HistoryEvent
}