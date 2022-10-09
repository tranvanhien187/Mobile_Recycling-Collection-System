package datn.cnpm.rcsystem.feature.history.giftdetail

import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory

data class GiftHistoryDetailState(
    val loading: Boolean = false,
    val giftHistory: GiftExchangeHistory? = null
)