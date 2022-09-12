package datn.cnpm.rcsystem.data.entitiy


data class ChangePasswordRequest(
    val email: String? = null,
    val password: String? = null
)