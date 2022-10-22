package datn.cnpm.rcsystem.feature.dashboard.customer

import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.UserEntity
import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity

data class DashboardCustomerState(
    val loading: Boolean = false,
    val userEntity: UserEntity? = null,
    val tPlaceList: List<TradingPlaceForUserEntity>? = null,
    val giftList: List<GiftEntity>? = null
)