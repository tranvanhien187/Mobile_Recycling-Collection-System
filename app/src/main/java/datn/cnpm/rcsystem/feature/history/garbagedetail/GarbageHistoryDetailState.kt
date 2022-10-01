package datn.cnpm.rcsystem.feature.history.garbagedetail

import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity

data class GarbageHistoryDetailState(
    val loading: Boolean = false,
    val garbageHistory: GarbageUserHistoryEntity? = null
)