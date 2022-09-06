package datn.cnpm.rcsystem.core

import kotlinx.serialization.Serializable

@Serializable
data class SBResponse<T>(
    val message: String? = null,
    val data: T? = null,
    val errors: List<String>? = null,
) {
    val requireData: T
        get() = data!!

    val isSuccess: Boolean
        get() = this.data != null

    val requireError: String
        get() = this.errors!!.joinToString(",")
}


fun <T, R> SBResponse<T>.map(transform: (T?) -> R?): SBResponse<R> {
    return SBResponse(
        this.message,
        transform.invoke(this.data),
        this.errors
    )
}