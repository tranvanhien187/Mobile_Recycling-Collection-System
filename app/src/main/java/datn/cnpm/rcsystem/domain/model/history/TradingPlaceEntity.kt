package datn.cnpm.rcsystem.domain.model.history

import datn.cnpm.rcsystem.data.entitiy.GetTPPlaceForUserResponse


data class TradingPlaceForUserEntity(
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

fun GetTPPlaceForUserResponse.mapToTPlaceForUserEntity() = TradingPlaceForUserEntity(
    id,
    name,
    rate,
    agentName,
    agentId,
    agentPhoneNumber,
    bannerUrl,
    street,
    district,
    provinceOrCity,
    dealerName,
    totalWeight,
    rank
)