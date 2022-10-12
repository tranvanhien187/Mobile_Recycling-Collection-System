package datn.cnpm.rcsystem.feature.history.garbage

import datn.cnpm.rcsystem.domain.model.history.BaseItemHistory

data class GarbageHistoryState(
    val loading: Boolean = false,
    val garbageHistoryList: List<BaseItemHistory>? = null
)