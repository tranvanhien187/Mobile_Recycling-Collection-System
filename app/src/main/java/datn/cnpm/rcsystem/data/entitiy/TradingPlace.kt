package datn.cnpm.rcsystem.data.entitiy

import java.sql.Timestamp


data class TradingPlaceResponse(
    val id: String? = null,
    val name: String? = null,
    val rate: Float? = null,
    val bannerUrl: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
)