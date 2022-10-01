package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.data.entitiy.*
import java.sql.Date
import java.text.NumberFormat
import java.util.*

data class UserEntity(
    val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val phoneNumber: String? = null,
    val dOB: Date? = null,
    val address: AddressResponse? = null,
    val garbage: GarbageManagementResponse? = null,
    val point: PointResponse? = null
)

fun GetUserInfoResponse.mapToUserEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    avatar = avatar,
    phoneNumber = phoneNumber,
    address = address,
    dOB = dateOfBirth,
    garbage = garbageManagement,
    point = point
)

fun UpdateUserInfoResponse.mapToUserEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    username = username,
    email = email,
    avatar = avatar,
    phoneNumber = phoneNumber,
    address = address,
    dOB = dateOfBirth,
)