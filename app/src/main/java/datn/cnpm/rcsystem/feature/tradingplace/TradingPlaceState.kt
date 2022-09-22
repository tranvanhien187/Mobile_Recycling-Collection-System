package datn.cnpm.rcsystem.feature.tradingplace

import datn.cnpm.rcsystem.domain.model.TradingPlaceForUserEntity

data class TradingPlaceState(val loading: Boolean = false, val listPlace: List<TradingPlaceForUserEntity> = emptyList())