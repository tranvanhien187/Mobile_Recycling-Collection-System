package datn.cnpm.rcsystem.feature.dashboard.staff

import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.TradingPlaceForUserEntity
import datn.cnpm.rcsystem.domain.model.UserEntity

data class DashboardStaffState(
    val loading: Boolean = false,
    val userEntity: UserEntity? = null,
    val tPlaceList: List<TradingPlaceForUserEntity>? = null,
    val giftList: List<GiftEntity>? = null
)