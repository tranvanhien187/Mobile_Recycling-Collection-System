package datn.cnpm.rcsystem.feature.tradingplace.detail

import datn.cnpm.rcsystem.domain.model.GiftEntity
import datn.cnpm.rcsystem.domain.model.tplace.TradingPlaceEntity

data class TradingPlaceDetailState(
    val loading: Boolean = false,
    val tplace: TradingPlaceEntity? = null,
    val listGiftOwnerByAgent: MutableList<GiftEntity> = mutableListOf(),
)
