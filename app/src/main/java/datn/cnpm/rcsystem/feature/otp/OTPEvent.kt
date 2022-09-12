package datn.cnpm.rcsystem.feature.otp

sealed interface OTPEvent {
    object ValidateOTPSuccess : OTPEvent
    object ValidateOTPFailure : OTPEvent
    object ReGenOTPSuccess : OTPEvent
    object ReGenOTPFailure : OTPEvent
}