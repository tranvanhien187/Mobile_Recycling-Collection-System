package datn.cnpm.rcsystem.feature.login

sealed interface LoginEvent {
    data class ErrorEmail(
        val error: String?
    ) : LoginEvent

    data class ErrorPassword(
        val error: String?
    ) : LoginEvent
}