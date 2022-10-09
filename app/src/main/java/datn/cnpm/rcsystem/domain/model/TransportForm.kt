package datn.cnpm.rcsystem.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TransportForm(
    val id: String,
    val weight: Float,
    val customerId: String,
    val customerName: String,
    val customerPhoneNumber: String,
    val street: String,
    val district: String,
    val cityOrProvince: String,
    val status: String,
    val createAt: String,
    val receiveAt: String,
    val completeAt: String

) : Parcelable {
    constructor() : this("", 0f, "", "", "", "", "", "", "", "", "", "")
}