package datn.cnpm.rcsystem.feature.history.garbage

import datn.cnpm.rcsystem.data.entitiy.GiftExchangeHistory
import datn.cnpm.rcsystem.domain.model.GarbageUserHistoryEntity

data class GarbageHistoryState(
    val loading: Boolean = false,
    val garbageList: List<GiftExchangeHistory>? = null
)