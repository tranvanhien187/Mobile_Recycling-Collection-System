package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.data.entitiy.GetTPPlaceForUserResponse


data class TradingPlaceForUserEntity(
    val id: String? = null,
    val name: String? = null,
    val rate: Float? = null,
    val bannerUrl: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null,
    val dealerId: String? = null,
    val dealerName: String? = null,
    val totalWeight: Double? = null,
)

fun GetTPPlaceForUserResponse.mapToTPlaceForUserEntity() = TradingPlaceForUserEntity(
    id,
    name,
    rate,
    bannerUrl,
    street,
    district,
    provinceOrCity,
    dealerId,
    dealerName,
    totalWeight
)