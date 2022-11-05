package datn.cnpm.rcsystem.domain.model.customer

import datn.cnpm.rcsystem.SingletonObject
import datn.cnpm.rcsystem.data.entitiy.*
import java.sql.Date
import java.sql.Timestamp

data class CustomerEntity(
    val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val identityCardNumber: String? = null,
    val phoneNumber: String? = null,
    val dOB: Timestamp? = null,
    val address: AddressResponse? = null,
    val garbage: GarbageManagementResponse? = null,
    val point: PointResponse? = null
)

fun GetCustomerInfoResponse.mapToCustomerEntity(): CustomerEntity {
    val entity = CustomerEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        avatar = avatar,
        identityCardNumber = identityCardNumber,
        phoneNumber = phoneNumber,
        address = address,
        dOB = dateOfBirth,
        garbage = garbageManagement,
        point = point
    )
    SingletonObject.customer = entity
    return entity
}

fun UpdateUserInfoResponse.mapToEntity(): CustomerEntity = CustomerEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    avatar = avatar,
    phoneNumber = phoneNumber,
    address = address,
    dOB = dateOfBirth,
)