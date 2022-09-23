package datn.cnpm.rcsystem.feature.history.gift

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface GiftHistoryEvent {
    data class ValidateField(
        val errorUsername: ErrorCode? = null,
        val errorPassword: ErrorCode? = null,
    ) : GiftHistoryEvent

    object UserLoginSuccess : GiftHistoryEvent
    data class UserUpdatedYet(val uuid: String) : GiftHistoryEvent
    object UnKnowError : GiftHistoryEvent
    data class LoginFailure(val message: String) : GiftHistoryEvent
}