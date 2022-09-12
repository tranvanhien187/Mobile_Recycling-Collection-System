package datn.cnpm.rcsystem.data.entitiy

import java.util.*

data class UserGiftHistoryResponse(
    val id: Int,
    val name: String? = null,
    val brand: String? = null,
    val contributor: String? = null,
    val redemptionPoint: Int,
    val status: String? = null,
    val dealerId: String? = null,
    val dealerName: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val registerTime: Date? = null,
    val receivedTime: Date? = null
)

