package datn.cnpm.rcsystem.feature.history.gift

sealed interface GiftHistoryEvent {
    object UnKnowError : GiftHistoryEvent
}