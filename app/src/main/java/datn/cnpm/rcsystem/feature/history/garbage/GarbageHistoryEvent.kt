package datn.cnpm.rcsystem.feature.history.garbage

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface GarbageHistoryEvent {
    data class ValidateField(
        val errorUsername: ErrorCode? = null,
        val errorPassword: ErrorCode? = null,
    ) : GarbageHistoryEvent

    object UserLoginSuccess : GarbageHistoryEvent
    data class UserUpdatedYet(val uuid: String) : GarbageHistoryEvent
    object UnKnowError : GarbageHistoryEvent
    data class LoginFailure(val message: String) : GarbageHistoryEvent
}