package datn.cnpm.rcsystem.feature.dashboard.staff

import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.StaffInfoEntity
import datn.cnpm.rcsystem.domain.model.TradingPlaceForUserEntity
import datn.cnpm.rcsystem.domain.model.UserEntity
import datn.cnpm.rcsystem.domain.usecase.GetStaffInfoUseCase

data class DashboardStaffState(
    val loading: Boolean = false,
    val staff: StaffInfoEntity? = null,
)