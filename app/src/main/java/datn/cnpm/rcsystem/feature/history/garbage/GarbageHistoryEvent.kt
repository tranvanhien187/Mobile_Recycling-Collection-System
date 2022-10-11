package datn.cnpm.rcsystem.feature.history.garbage

import datn.cnpm.rcsystem.common.ErrorCode

sealed interface GarbageHistoryEvent {
    object UnKnowError : GarbageHistoryEvent
}