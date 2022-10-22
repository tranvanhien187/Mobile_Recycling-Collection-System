package datn.cnpm.rcsystem.data.entitiy


data class GetTPPlaceForUserRequest(val uuid: String, val criteria: String)

data class GetTPPlaceForUserResponse(
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
    val dealerName: String? = null,
    val totalWeight: Double? = null,
    val rank: Int = 0
)

enum class GetTradingPlaceCriteria {
    None, DISTRICT, ProvinceOrCity
}