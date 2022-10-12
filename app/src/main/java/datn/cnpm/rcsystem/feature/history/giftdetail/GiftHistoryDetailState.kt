package datn.cnpm.rcsystem.feature.history.giftdetail

import datn.cnpm.rcsystem.domain.model.history.GiftHistoryDetailEntity

data class GiftHistoryDetailState(
    val loading: Boolean = false,
    val giftHistoryDetail: GiftHistoryDetailEntity? = null
)