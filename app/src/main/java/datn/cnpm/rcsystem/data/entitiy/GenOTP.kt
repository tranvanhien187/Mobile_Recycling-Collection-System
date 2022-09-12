package datn.cnpm.rcsystem.data.entitiy

data class GenOTPRequest(
    val email: String? = null,
    val otpType: Int = 0
)

enum class OTPType(val value: Int) {
    EMAIL(1)
}