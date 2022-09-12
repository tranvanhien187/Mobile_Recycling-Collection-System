package datn.cnpm.rcsystem.data.entitiy

import java.util.*


data class DealerGiftHistoryResponse(
    val id: Int = 0,
    val name: String? = null,
    val brand: String? = null,
    val contributor: String? = null,
    val redemptionPoint: Int = 0,
    val status: String? = null,
    val userId: String? = null,
    val userName: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val registerTime: Date? = null,
    val receivedTime: Date? = null
)