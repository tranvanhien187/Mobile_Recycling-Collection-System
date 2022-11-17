package datn.cnpm.rcsystem.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransportFormFirebase(

    val id: String,
    val giftName: String? = null,
    val giftBrand: String? = null,
    val giftUrl: String? = null,
    val description: String? = null,
    val type: String,
    val point: Int = 0,
    val weight: Float = 0f,
    val customerId: String,
    val customerName: String,
    val customerPhoneNumber: String,
    val street: String,
    val district: String,
    val cityOrProvince: String,
    val status: String? = null,
    val createAt: String,
    val receiveAt: String? = null,
    val completeAt: String? = null,
): Parcelable {
    constructor() : this("","","","","","",0,0f,"","","","","","","","","","")
}