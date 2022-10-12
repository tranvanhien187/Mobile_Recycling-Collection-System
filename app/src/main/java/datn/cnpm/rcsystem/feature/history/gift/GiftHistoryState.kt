package datn.cnpm.rcsystem.feature.history.gift

import datn.cnpm.rcsystem.domain.model.history.BaseItemHistory

data class GiftHistoryState(
    val loading: Boolean = false,
    val giftHistoryList: List<BaseItemHistory>? = null
)