package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.data.entitiy.AddressResponse
import datn.cnpm.rcsystem.data.entitiy.GetCustomerInfoResponse
import datn.cnpm.rcsystem.data.entitiy.staff.StaffInfoResponse
import java.util.*


data class StaffInfoEntity(
    val id: String,
    val name: String,
    val agentName: String,
    val agentId: String,
    val username: String,
    val email: String,
    val avatar: String,
    val phoneNumber: String,
    val identityCardNumber: String,
    val dayOfBirth: Date,
    val address: AddressResponse,
    val weightTotal: Double = 0.0,
    val giftCount: Long = 0
)

fun StaffInfoResponse.mapToEntity(): StaffInfoEntity {
    val entity = StaffInfoEntity(
        id,
        name,
        agentName,
        agentId,
        username,
        email,
        avatar,
        phoneNumber,
        identityCardNumber,
        dayOfBirth,
        address,
        String.format("%.1f", weightTotal).replace(",",".").toDouble(),
        giftCount
    )
    SingletonObject.staff = entity
    return entity
}