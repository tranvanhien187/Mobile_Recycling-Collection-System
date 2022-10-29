package datn.cnpm.rcsystem.domain.model

import android.os.Parcelable
import datn.cnpm.rcsystem.data.entitiy.history.GarbageUserHistoryResponse
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class GarbageUserHistoryEntity(
    val id: Int = 0,
    val dealerId: String? = null,
    val dealerName: String? = null,
    val placeUrl: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val point: Int = 0,
    val weight: Float = 0f,
    val time: Date? = null,
    val code: String? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null
) : Parcelable

fun GarbageUserHistoryResponse.mapToEntity(): GarbageUserHistoryEntity {
    return GarbageUserHistoryEntity(
        id,
        dealerId,
        dealerName,
        placeUrl,
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