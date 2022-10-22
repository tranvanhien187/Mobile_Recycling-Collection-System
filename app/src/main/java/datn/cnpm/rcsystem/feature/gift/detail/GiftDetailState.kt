package datn.cnpm.rcsystem.feature.gift.detail

import datn.cnpm.rcsystem.domain.model.tplace.TradingPlaceEntity

data class GiftDetailState(
    val loading: Boolean = false,
    val tplace: TradingPlaceEntity? = null
)