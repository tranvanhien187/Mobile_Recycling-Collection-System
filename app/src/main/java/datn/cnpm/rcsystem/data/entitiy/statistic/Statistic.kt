package datn.cnpm.rcsystem.data.entitiy.statistic


data class StatisticStaffCollectWeightByDayResponse(
    val datOfMonth: Int = 0,
    val monthOfYear: Int = 0,
    val year: Int = 0,
    val totalWeight: Double = 0.0,
)

data class StatisticStaffCollectResponse(
    val id: String? = null,
    val name: String? = null,
    val avatarUrl: String? = null,
    val phoneNumber: String? = null,
    val totalWeight: Double = 0.0
)
