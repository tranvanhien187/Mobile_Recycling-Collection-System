package datn.cnpm.rcsystem.data.entitiy

import java.util.*


data class DealerGarbageHistoryResponse(
    val id: Int = 0,
    val userId: String? = null,
    val userName: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val point: Int = 0,
    val weight: Float = 0f,
    val time: Date? = null,
    val code: String? = null
)
