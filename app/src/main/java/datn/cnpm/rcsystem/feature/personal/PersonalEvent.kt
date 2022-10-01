package datn.cnpm.rcsystem.feature.personal

sealed interface PersonalEvent {
    object ValidateOTPSuccess : PersonalEvent
    object ValidateOTPFailure : PersonalEvent
    object ReGenOTPSuccess : PersonalEvent
    object ReGenOTPFailure : PersonalEvent
}