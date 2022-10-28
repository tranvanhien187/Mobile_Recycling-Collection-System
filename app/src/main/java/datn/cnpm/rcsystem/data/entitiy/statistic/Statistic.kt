package datn.cnpm.rcsystem.data.entitiy.statistic


data class StatisticStaffCollectWeightByDayResponse(
    val datOfMonth: Int = 0,
    val monthOfYear: Int = 0,
    val year: Int = 0,
    val totalWeight: Double = 0.0,
)