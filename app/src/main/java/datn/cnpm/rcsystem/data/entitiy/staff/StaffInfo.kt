package datn.cnpm.rcsystem.data.entitiy.staff

import datn.cnpm.rcsystem.data.entitiy.AddressResponse
import java.util.*


data class StaffInfoResponse(
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