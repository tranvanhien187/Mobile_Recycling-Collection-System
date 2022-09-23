package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.data.entitiy.GarbageUserHistoryResponse
import java.util.*


class GarbageUserHistoryEntity(
    val id: Int = 0,
    val dealerId: String? = null,
    val dealerName: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val point: Int = 0,
    val weight: Float = 0f,
    val time: Date? = null,
    val code: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null
)

fun GarbageUserHistoryResponse.mapToEntity(): GarbageUserHistoryEntity {
    return GarbageUserHistoryEntity(
        id,
        dealerId,
        dealerName,
        placeId,
        placeName,
        point,
        weight,
        time,
        code,
        street,
        district,
        provinceOrCity
    )
}