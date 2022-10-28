package datn.cnpm.rcsystem.domain.model.statistic

import datn.cnpm.rcsystem.data.entitiy.statistic.StatisticStaffCollectWeightByDayResponse


data class StatisticStaffCollectWeightByDayEntity(
    val datOfMonth: Int = 0,
    val monthOfYear: Int = 0,
    val year: Int = 0,
    val totalWeight: Double = 0.0,
)

fun StatisticStaffCollectWeightByDayResponse.mapToEntity() =
    StatisticStaffCollectWeightByDayEntity(datOfMonth, monthOfYear, year, totalWeight)