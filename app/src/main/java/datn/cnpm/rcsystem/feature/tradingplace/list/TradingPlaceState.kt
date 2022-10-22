package datn.cnpm.rcsystem.feature.tradingplace.list

import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity

data class TradingPlaceState(
    val loading: Boolean = false,
    val listPlace: List<TradingPlaceForUserEntity> = emptyList()
)