package datn.cnpm.rcsystem.data.entitiy

import java.sql.Date

data class GetCustomerInfoResponse(
    val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val identityCardNumber: String? = null,
    val phoneNumber: String? = null,
    val dateOfBirth: Date? = null,
    val address: AddressResponse? = null,
    val garbageManagement: GarbageManagementResponse? = null,
    val point: PointResponse
)