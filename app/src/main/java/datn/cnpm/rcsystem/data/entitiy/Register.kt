package datn.cnpm.rcsystem.data.entitiy

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String,
    val role: String,
)