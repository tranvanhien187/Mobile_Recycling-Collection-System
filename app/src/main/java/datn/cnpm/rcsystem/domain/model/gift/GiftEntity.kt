package datn.cnpm.rcsystem.domain.model.gift

import datn.cnpm.rcsystem.data.entitiy.gift.GiftResponse


data class GiftEntity(
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
    val placeId: String? = null,
    val placeName: String? = null,
    val agentName: String? = null,
    val agentId: String? = null
)

fun GiftResponse.mapToEntity() = GiftEntity(
    id,
    name,
    brand,
    contributor,
    redemptionPoint,
    type,
    imageUrl,
    street,
    district,
    provinceOrCity,
    placeId,
    placeName,
    agentName,
    agentId
)

enum class GiftStatus {
    AVAILABLE, REGISTER, RECEIVED
}

enum class GiftType {
    Beverage, Electronic, Houseware
}