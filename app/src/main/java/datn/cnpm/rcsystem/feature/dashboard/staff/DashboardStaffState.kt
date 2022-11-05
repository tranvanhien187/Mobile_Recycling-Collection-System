package datn.cnpm.rcsystem.feature.dashboard.staff

import datn.cnpm.rcsystem.domain.model.TransportFormFirebase
import datn.cnpm.rcsystem.domain.model.staff.StaffInfoEntity
import datn.cnpm.rcsystem.domain.model.statistic.StatisticStaffCollectWeightByDayEntity

data class DashboardStaffState(
    val loading: Boolean = false,
    val staff: StaffInfoEntity? = null,
    val form: TransportFormFirebase? = null,
    val staffCollection7Day: List<StatisticStaffCollectWeightByDayEntity>? = null
)