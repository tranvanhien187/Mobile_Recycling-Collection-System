package datn.cnpm.rcsystem.feature.history.giftdetail

sealed interface GiftHistoryDetailEvent {
    object UnKnowError : GiftHistoryDetailEvent
}