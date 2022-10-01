package datn.cnpm.rcsystem.domain.model

import android.os.Parcelable
import datn.cnpm.rcsystem.data.entitiy.GiftUserHistoryResponse
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class GiftUserHistoryEntity(
    val id: Int = 0,
    val name: String? = null,
    val type: String? = null,
    val imageUrl: String? = null,
    val brand: String? = null,
    val contributor: String? = null,
    val redemptionPoint: Int = 0,
    val status: String? = null,
    val dealerId: String? = null,
    val dealerName: String? = null,
    val placeId: String? = null,
    val placeName: String? = null,
    val registerTime: Date? = null,
    val receivedTime: Date? = null,
    val street: String? = null,
    val district: String? = null,
    val provinceOrCity: String? = null,
) : Parcelable

fun GiftUserHistoryResponse.mapToEntity(): GiftUserHistoryEntity {
    return GiftUserHistoryEntity(
        id,
        name,
        type,
        imageUrl,
        brand,
        contributor,
        redemptionPoint,
        status,
        dealerId,
        dealerName,
        placeId,
        placeName,
        registerTime,
        receivedTime,
        street,
        district,
        provinceOrCity
    )
}

enum class GiftStatus {
    AVAILABLE, REGISTER, RECEIVED
}

enum class GiftType {
    Beverage, Electronic, Houseware
}