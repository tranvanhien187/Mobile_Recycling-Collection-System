package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.data.entitiy.GiftResponse


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
    provinceOrCity
)