package datn.cnpm.rcsystem.feature.dashboard.customer

import datn.cnpm.rcsystem.domain.model.customer.CustomerEntity
import datn.cnpm.rcsystem.domain.model.gift.GiftEntity
import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity

data class DashboardCustomerState(
    val loading: Boolean = false,
    val userEntity: CustomerEntity? = null,
    val tPlaceList: List<TradingPlaceForUserEntity>? = null,
    val giftList: List<GiftEntity>? = null
)