package datn.cnpm.rcsystem.feature.history.giftdetail

import datn.cnpm.rcsystem.domain.model.GiftUserHistoryEntity

data class GiftHistoryDetailState(
    val loading: Boolean = false,
    val giftHistory: GiftUserHistoryEntity? = null
)