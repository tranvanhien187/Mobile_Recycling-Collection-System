package datn.cnpm.rcsystem.feature.gift.list

import datn.cnpm.rcsystem.domain.model.history.TradingPlaceForUserEntity

data class GiftState(
    val loading: Boolean = false,
    val listPlace: List<TradingPlaceForUserEntity> = emptyList()
)