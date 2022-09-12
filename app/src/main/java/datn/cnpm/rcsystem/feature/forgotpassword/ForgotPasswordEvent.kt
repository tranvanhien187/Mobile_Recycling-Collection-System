package datn.cnpm.rcsystem.feature.forgotpassword

sealed interface ForgotPasswordEvent {
    object GenOTPSuccess : ForgotPasswordEvent
    object GenOTPFailure : ForgotPasswordEvent
}