package datn.cnpm.rcsystem.domain.model.gift

import android.os.Parcelable
import datn.cnpm.rcsystem.data.entitiy.gift.GiftDetailResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class GiftDetailEntity(
    val id: String,
    val brand: String? = null,
    val name: String? = null,
    val description: String? = null,
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
) : Parcelable

fun GiftDetailResponse.mapToEntity(): GiftDetailEntity = GiftDetailEntity(
    id,
    brand,
    name,
    description,
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