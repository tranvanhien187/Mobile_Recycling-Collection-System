package datn.cnpm.rcsystem.feature.history.garbagedetail

import datn.cnpm.rcsystem.domain.model.history.GarbageHistoryDetailEntity

data class GarbageHistoryDetailState(
    val loading: Boolean = false,
    val garbageHistoryDetail: GarbageHistoryDetailEntity? = null
)