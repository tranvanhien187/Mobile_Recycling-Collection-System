package datn.cnpm.rcsystem.data.entitiy

data class ValidateOTPRequest(
        val email: String? = null,
        val otp: String? = null
)