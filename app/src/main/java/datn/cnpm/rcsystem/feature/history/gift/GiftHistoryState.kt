package datn.cnpm.rcsystem.feature.history.gift

import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity

data class GiftHistoryState(
    val loading: Boolean = false,
    val giftList: List<GiftUserHistoryEntity>? = null
)