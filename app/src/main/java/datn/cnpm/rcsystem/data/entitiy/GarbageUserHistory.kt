package datn.cnpm.rcsystem.data.entitiy

import java.util.*


data class GarbageUserHistoryResponse(
    val id: Int = 0,
    val dealerId: String? = null,
    val dealerName: String? = null,
    val placeUrl: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val point: Int = 0,
    val weight: Float = 0f,
    val time: Date? = null,
    val code: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null
)