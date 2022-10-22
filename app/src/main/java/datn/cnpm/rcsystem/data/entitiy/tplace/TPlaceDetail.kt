package datn.cnpm.rcsystem.data.entitiy.tplace


data class TPlaceDetailResponse(
    val id: String? = null,
    val name: String? = null,
    val rate: Float = 0f,
    val agentName: String? = null,
    val agentId: String? = null,
    val agentPhoneNumber: String? = null,
    val bannerUrl: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null,
    val totalWeight: Double = 0.0,
    val rank: Int = 0,
    val giftExchange: Long = 0,
)