package datn.cnpm.rcsystem.data.entitiy


data class GetTPPlaceForUserRequest(val uuid: String, val criteria: String)

data class GetTPPlaceForUserResponse(
    val id: String,
    val name: String,
    val rate: Float,
    val bannerUrl: String,
    val street: String,
    val district: String,
    val provinceOrCity: String,
    val dealerId: String,
    val dealerName: String,
    val totalWeight: Double
)

enum class GetTradingPlaceCriteria {
    None, DISTRICT, ProvinceOrCity
}