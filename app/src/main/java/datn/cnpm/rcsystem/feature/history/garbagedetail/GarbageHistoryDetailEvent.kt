package datn.cnpm.rcsystem.feature.history.garbagedetail

sealed interface GarbageHistoryDetailEvent {
    object UnKnowError : GarbageHistoryDetailEvent
}