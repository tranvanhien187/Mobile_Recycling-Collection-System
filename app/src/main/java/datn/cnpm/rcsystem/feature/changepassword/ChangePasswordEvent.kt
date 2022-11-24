package datn.cnpm.rcsystem.feature.changepassword

sealed interface ChangePasswordEvent {
    object ChangePasswordSuccess : ChangePasswordEvent
    data class ChangePasswordFailure( val message: String) : ChangePasswordEvent
}