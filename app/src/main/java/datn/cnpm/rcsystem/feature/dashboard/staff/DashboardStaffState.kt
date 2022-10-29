package datn.cnpm.rcsystem.feature.dashboard.staff

import datn.cnpm.rcsystem.domain.model.StaffInfoEntity
import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectWeightByDayEntity

data class DashboardStaffState(
    val loading: Boolean = false,
    val staff: StaffInfoEntity? = null,
    val staffCollection7Day: List<StatisticStaffCollectWeightByDayEntity>? = null
)