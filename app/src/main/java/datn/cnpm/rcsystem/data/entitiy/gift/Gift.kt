package datn.cnpm.rcsystem.data.entitiy.gift


data class GiftResponse(
    val id: String? = null,
    val name: String? = null,
    val brand: String? = null,
    val contributor: String? = null,
    val redemptionPoint: Int = 0,
    val type: String? = null,
    val imageUrl: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null,
    val placeId: String,
    val placeName: String,
    val agentName: String,
    val agentId: String
)