package datn.cnpm.rcsystem.domain.model

import datn.cnpm.rcsystem.data.entitiy.TransportFormGarbageDetailResponse


data class TFormGarbageDetailEntity(
    val id: String,
    val name: String,
    val status: String,
    val phoneNumber: String,
    val weight: Float,
    var address: String = "",
    var timeDate: String = "",
)

fun TransportFormGarbageDetailResponse.mapToEntity() = TFormGarbageDetailEntity(
    id,
    name,
    status,
    phoneNumber,
    weight
)
