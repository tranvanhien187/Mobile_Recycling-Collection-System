package datn.cnpm.rcsystem.data.entitiy

import java.io.File
import java.sql.Timestamp

data class UpdateUserInfoRequest(
    val avatar: File,
    val uuid: String,
    val name: String,
    val phoneNumber: String,
    val ICN: String,
    val dOB: String,
    val street: String,
    val district: String,
    val provinceOrCity: String,
)

data class UpdateUserInfoResponse(
    val id: String? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val avatar: String? = null,
    val phoneNumber: String? = null,
    val identityCardNumber: String? = null,
    val dateOfBirth: Timestamp? = null,
    val address: AddressResponse? = null
)

