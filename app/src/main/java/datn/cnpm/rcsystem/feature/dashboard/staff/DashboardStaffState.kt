package datn.cnpm.rcsystem.feature.dashboard.staff

import datn.cnpm.rcsystem.domain.model.StaffInfoEntity

data class DashboardStaffState(
    val loading: Boolean = false,
    val staff: StaffInfoEntity? = null,
)