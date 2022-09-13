package datn.cnpm.rcsystem.core

import datn.cnpm.rcsystem.common.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
data class SBResponse<T>(
    val message: String? = null,
    val data: T? = null,
    val error: ErrorCode? = null,
) {
    val requireData: T
        get() = data!!

    val isSuccess: Boolean
        get() = this.data != null

    val isFailure: Boolean
        get() = this.error != null

    val requireError: ErrorCode
        get() = this.error!!
}


fun <T, R> SBResponse<T>.map(transform: (T?) -> R?): SBResponse<R> {
    return SBResponse(
        this.message,
        transform.invoke(this.data),
        this.error
    )
}