package datn.cnpm.rcsystem.domain.model.gift

import datn.cnpm.rcsystem.data.entitiy.gift.GiftDetailResponse


data class GiftDetailEntity(
    val id: String? = null,
    val brand: String? = null,
    val name: String? = null,
    val redemptionPoint: Int = 0,
    val status: String? = null,
    val agentId: String? = null,
    val agentName: String? = null,
    val agentPhone: String? = null,
    val placeName: String? = null,
    val placeId: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null,
    val url: String? = null,
    val type: String? = null,
)

fun GiftDetailResponse.mapToEntity(): GiftDetailEntity = GiftDetailEntity(
    id,
    brand,
    name,
    redemptionPoint,
    status,
    agentId,
    agentName,
    agentPhone,
    placeName,
    placeId,
    street,
    district,
    provinceOrCity,
    url,
    type
)