package datn.cnpm.rcsystem.data.entitiy

data class LoginResponse(
    val tokenType: String = "Bearer",
    val accessToken: String,
    val uuid: String,
    val role: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

enum class Role {
    ADMIN,
    USER,
    DEALER
}