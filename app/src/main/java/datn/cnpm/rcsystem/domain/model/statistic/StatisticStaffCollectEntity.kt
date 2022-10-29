package datn.cnpm.rcsystem.domain.model.statistic

import datn.cnpm.rcsystem.data.entitiy.statistic.StatisticStaffCollectResponse


data class StatisticStaffCollectEntity(
    val id: String? = null,
    val name: String? = null,
    val avatarUrl: String? = null,
    val phoneNumber: String? = null,
    val totalWeight: Double = 0.0
)

fun StatisticStaffCollectResponse.mapToEntity() =
    StatisticStaffCollectEntity(id, name, avatarUrl, phoneNumber, totalWeight)