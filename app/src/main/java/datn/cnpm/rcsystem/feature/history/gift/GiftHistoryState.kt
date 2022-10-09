package datn.cnpm.rcsystem.feature.history.gift

import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory

data class GiftHistoryState(
    val loading: Boolean = false,
    val giftList: List<GiftExchangeHistory>? = null
)