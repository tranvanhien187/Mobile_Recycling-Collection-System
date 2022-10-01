package datn.cnpm.rcsystem.feature.dashboard

import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.TradingPlaceForUserEntity
import datn.cnpm.rcsystem.domain.model.UserEntity

data class DashboardState(
    val loading: Boolean = false,
    val userEntity: UserEntity? = null,
    val tPlaceList: List<TradingPlaceForUserEntity>? = null,
    val giftList: List<GiftEntity>? = null
)