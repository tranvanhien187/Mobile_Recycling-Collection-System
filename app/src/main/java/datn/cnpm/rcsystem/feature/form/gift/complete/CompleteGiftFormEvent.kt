package datn.cnpm.rcsystem.feature.form.gift.complete


sealed interface CompleteGiftFormEvent {
    object CompleteTransportGarbageSuccess : CompleteGiftFormEvent
    object EvidenceEmpty : CompleteGiftFormEvent
}